package com.game.service;



import com.game.dto.PlayerFilterDto;
import com.game.dto.PlayerDto;
import com.game.entity.Player;

import com.game.repository.PlayerRepository;

import com.game.utils.PlayerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class PlayerServiceImp implements PlayerService{
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImp(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    public PlayerDto create(PlayerDto player) {
        try {
            if (player.getName().trim().isEmpty() || player.getName().trim().length() > 12) return null;
            if (player.getTitle().trim().isEmpty() || player.getTitle().trim().length() > 30) return null;
            if (player.getExperience() > 10000000 || player.getExperience() < 0) return null;
            if (player.getBirthday() > PlayerUtils.END_DATE || player.getBirthday() < PlayerUtils.START_DATE) return null;
            if (player.getProfession() == null || player.getRace() == null) return null;
                Integer lvl = PlayerUtils.calculateLevel(player.getExperience());
                player.setLevel(lvl);
                player.setUntilNextLevel(PlayerUtils.calculateUntilNextLevel(player.getExperience(), lvl));
                Player resultPlayer = new Player(null, player.getName(), player.getTitle(), player.getRace(), player.getProfession(),
                        player.getExperience(), player.getLevel(), player.getUntilNextLevel(), new Date(player.getBirthday()), player.getBanned());
                Player createdPlayer = playerRepository.save(resultPlayer);
                player.setId(createdPlayer.getId());
                return player;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PlayerDto>  readAll(PlayerFilterDto filter) {
        try {

            Pageable pageable = PageRequest.of(filter.getPageNumber(), filter.getPageSize(), PlayerUtils.getSort(filter));
            PlayerUtils.setDefaultValues(filter);
           return playerRepository.findPlayersByNameContainingAndRaceInAndTitleContainingAndBannedInAndProfessionInAndBirthdayBetweenAndExperienceBetweenAndLevelBetween(
                    filter.getName(), PlayerUtils.getRaces(filter), filter.getTitle(), PlayerUtils.getBanned(filter), PlayerUtils.getProfessions(filter), new Date(filter.getAfter()), new Date(filter.getBefore()),
                    filter.getMinExperience(), filter.getMaxExperience(), filter.getMinLevel(), filter.getMaxLevel(), pageable).stream().map(PlayerDto::new).collect(Collectors.toList());

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
    @Override
    public PlayerDto read(Long id) {
         return playerRepository.findById(id).map(PlayerDto::new).orElse(null);
    }

    @Override
    public Integer getCount(PlayerFilterDto filter) {
        try {
            PlayerUtils.setDefaultValues(filter);
            return playerRepository.countPlayersByNameContainingAndRaceInAndTitleContainingAndBannedInAndProfessionInAndBirthdayBetweenAndExperienceBetweenAndLevelBetween(
                    filter.getName(), PlayerUtils.getRaces(filter), filter.getTitle(), PlayerUtils.getBanned(filter), PlayerUtils.getProfessions(filter), new Date(filter.getAfter()), new Date(filter.getBefore()),
                    filter.getMinExperience(), filter.getMaxExperience(), filter.getMinLevel(), filter.getMaxLevel(), PlayerUtils.getSort(filter));
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public PlayerDto update(PlayerDto existedPlayer, PlayerDto player) {
        if (player.getName() != null && !player.getName().trim().isEmpty()) existedPlayer.setName(player.getName());
        if (player.getTitle() != null && !player.getTitle().trim().isEmpty()) existedPlayer.setTitle(player.getTitle());
        if (player.getBanned() != null) existedPlayer.setBanned(player.getBanned());
        Integer exp = player.getExperience();
        if (exp != null) {
            existedPlayer.setExperience(player.getExperience());
            if (exp < 0 || exp > 10000000) return null;
            Integer lvl = PlayerUtils.calculateLevel(exp);
            existedPlayer.setLevel(lvl);
            existedPlayer.setUntilNextLevel(PlayerUtils.calculateUntilNextLevel(player.getExperience(), lvl));
        }
        if (player.getProfession() != null) existedPlayer.setProfession(player.getProfession());
        if (player.getRace() != null) existedPlayer.setRace(player.getRace());
        if (player.getBirthday() != null) {
            if (player.getBirthday() > PlayerUtils.END_DATE || player.getBirthday() < PlayerUtils.START_DATE) return null;
            existedPlayer.setBirthday(player.getBirthday());
        }
        if (existedPlayer.equals(read(existedPlayer.getId()))) return existedPlayer;
        Player resultPlayer = new Player(existedPlayer.getId(), existedPlayer.getName(), existedPlayer.getTitle(), existedPlayer.getRace(), existedPlayer.getProfession(),
                existedPlayer.getExperience(), existedPlayer.getLevel(), existedPlayer.getUntilNextLevel(), new Date(existedPlayer.getBirthday()), existedPlayer.getBanned());
        return new PlayerDto(playerRepository.save(resultPlayer));
    }
    @Override
    public boolean delete(Long id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
