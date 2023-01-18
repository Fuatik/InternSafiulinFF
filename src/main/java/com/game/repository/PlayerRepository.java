package com.game.repository;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findPlayersByNameContainingAndRaceInAndTitleContainingAndBannedInAndProfessionInAndBirthdayBetweenAndExperienceBetweenAndLevelBetween
            (String name, List<Race> races, String title, List<Boolean> banned, List<Profession> professions,
             Date after, Date before, Integer minExperience, Integer maxExperience, Integer minLevel, Integer maxLevel, Pageable pageable);
    Integer countPlayersByNameContainingAndRaceInAndTitleContainingAndBannedInAndProfessionInAndBirthdayBetweenAndExperienceBetweenAndLevelBetween
            (String name, List<Race> races, String title, List<Boolean> banned, List<Profession> professions,
             Date after, Date before, Integer minExperience, Integer maxExperience, Integer minLevel, Integer maxLevel, Sort sort);


}
