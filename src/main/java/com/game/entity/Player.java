package com.game.entity;
import javax.persistence.*;
import java.util.Date;




@Entity
@Table(name = "player")
public class Player {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //ID игрока
    @Column(name = "name")
    private String name; //Имя персонажа (до 12 знаков включительно)
    @Column(name = "title")
    private String title; //Титул персонажа (до 30 знаков включительно)
    @Column(name = "race")
    @Enumerated(EnumType.STRING)
    private Race race; //Расса персонажа
    @Column(name = "profession")
    @Enumerated(EnumType.STRING)
    private Profession profession; //Профессия персонажа
    @Column(name = "experience")
    private Integer experience; //Опыт персонажа. Диапазон значений 0..10,000,000
    @Column(name = "level")
    private Integer level; //Уровень персонажа
    @Column(name = "untilNextLevel")
    private Integer untilNextLevel; //Остаток опыта до следующего уровня
    @Column(name = "birthday")
    private Date birthday; //Дата регистрации Диапазон значений года 2000..3000 включительно
    @Column(name = "banned")
    private Boolean banned; //Забанен / не забанен

    public Player() {
    }

    public Player(Long id, String name, String title, Race race, Profession profession, Integer experience, Integer level, Integer untilNextLevel, Date birthday, Boolean banned) {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }
}
