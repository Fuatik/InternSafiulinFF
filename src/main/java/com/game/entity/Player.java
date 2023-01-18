package com.game.entity;
import javax.persistence.*;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
