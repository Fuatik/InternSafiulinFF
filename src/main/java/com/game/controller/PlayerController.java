package com.game.controller;

import com.game.dto.PlayerFilterDto;
import com.game.dto.PlayerDto;

import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping(value = "/rest/players")
    public ResponseEntity<?> create(@RequestBody PlayerDto player) {
        PlayerDto createdPlayer = playerService.create(player);
        return createdPlayer != null
                ? new ResponseEntity<>(createdPlayer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping(value = "/rest/players")
    public ResponseEntity<List<PlayerDto>> read(PlayerFilterDto filter) {
        final List<PlayerDto> player = playerService.readAll(filter);

        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @GetMapping(value = "/rest/players/{id}")
    public ResponseEntity<PlayerDto> read(@PathVariable(name = "id") String id) {
        try {
            long newId = Long.parseLong(id);
            if (newId <= 0L) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            final PlayerDto player = playerService.read(newId);
            return player != null
                    ? new ResponseEntity<>(player, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }



    }

    @GetMapping(value = "/rest/players/count")
    public ResponseEntity<?> getCount(PlayerFilterDto filter) {
        long playersCount = playerService.getCount(filter);

        return playersCount >= 0L
                ? new ResponseEntity<>(playersCount, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/rest/players/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") String id, @RequestBody PlayerDto player) {
        try {
            long newId = Long.parseLong(id);
            if ( newId <= 0L) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            final PlayerDto existedPlayer = playerService.read(newId);
            if (existedPlayer == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            final PlayerDto updated = playerService.update(existedPlayer, player);

            return updated != null
                    ? new ResponseEntity<>(updated, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/rest/players/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id) {
        try {
            long newId = Long.parseLong(id);
            if (newId <= 0L) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            final boolean deleted = playerService.delete(newId);

            return deleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
