package com.game.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;



@Data
@JsonIgnoreProperties
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

    @JsonProperty
    private Long id; //ID игрока

    @JsonProperty
    private String name; //Имя персонажа (до 12 знаков включительно)

    @JsonProperty
    private String title; //Титул персонажа (до 30 знаков включительно)

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private Race race; //Расса персонажа

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private Profession profession; //Профессия персонажа

    @JsonProperty
    private Integer experience; //Опыт персонажа. Диапазон значений 0..10,000,000

    @JsonProperty
    private Integer level; //Уровень персонажа

    @JsonProperty
    private Integer untilNextLevel; //Остаток опыта до следующего уровня

    @JsonProperty
    private Long birthday; //Дата регистрации Диапазон значений года 2000..3000 включительно

    @JsonProperty
    private Boolean banned; //Забанен / не забанен

    public PlayerDto(Player player) {
        if (player != null) {
            this.id = player.getId();
            this.name = player.getName();
            this.title = player.getTitle();
            this.race = player.getRace();
            this.profession = player.getProfession();
            this.experience = player.getExperience();
            this.level = player.getLevel();
            this.untilNextLevel = player.getUntilNextLevel();
            this.birthday = player.getBirthday().getTime();
            this.banned = player.getBanned();
        }
    }
}
