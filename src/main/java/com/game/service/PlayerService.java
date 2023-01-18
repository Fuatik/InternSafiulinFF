package com.game.service;

import com.game.dto.PlayerFilterDto;
import com.game.dto.PlayerDto;


import java.util.List;

public interface PlayerService {
    /**
     * Создает нового клиента
     * @param player - клиент для создания
     */
    PlayerDto create(PlayerDto player);

    /**
     * Возвращает список всех имеющихся клиентов
     * @return список клиентов
     */
    List<PlayerDto> readAll(PlayerFilterDto filter);

    /**
     * Возвращает клиента по его ID
     *
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    PlayerDto read(Long id);

    /**
     * Возвращает количество имеющихся клиентов
     * @return число
     */
    Integer getCount(PlayerFilterDto filter);

    /**
     * Обновляет клиента с заданным ID,
     * в соответствии с переданным клиентом
     * @param player - клиент в соответсвии с которым нужно обновить данные
     * @param id - id клиента которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    PlayerDto update(PlayerDto existedPlayer, PlayerDto player);

    /**
     * Удаляет клиента с заданным ID
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(Long id);
}
