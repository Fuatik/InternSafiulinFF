package com.game.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;



import javax.persistence.EnumType;
import javax.persistence.Enumerated;




@JsonIgnoreProperties
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

    public PlayerDto(Long id, String name, String title, Race race, Profession profession, Integer experience, Integer level, Integer untilNextLevel, Long birthday, Boolean banned) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
        this.birthday = birthday;
        this.banned = banned;
    }

    public PlayerDto() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }
}
