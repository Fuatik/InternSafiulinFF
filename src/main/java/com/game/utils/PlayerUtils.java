package com.game.utils;

import com.game.controller.PlayerOrder;
import com.game.dto.PlayerFilterDto;
import com.game.entity.Profession;
import com.game.entity.Race;

import org.springframework.data.domain.Sort;



import java.util.Arrays;
import java.util.Collections;



import java.util.List;

public class PlayerUtils {
    private PlayerUtils() {
    }

    public static final long START_DATE = 946684800000L;
    public static final long END_DATE = 32535215999000L;
    public static List<Race> getRaces(PlayerFilterDto filter) {
        List<Race> races = Arrays.asList(Race.values());
        if (filter.getRace() != null) races = Collections.singletonList(filter.getRace());
        return races;
    }

    public static List<Profession> getProfessions(PlayerFilterDto filter) {
        List<Profession> professions = Arrays.asList(Profession.values());
        if (filter.getProfession() != null) professions = Collections.singletonList(filter.getProfession());
        return professions;
    }

    public static List<Boolean> getBanned(PlayerFilterDto filter) {
        List<Boolean> banned = Arrays.asList(true, false);
        if (filter.getBanned() != null) banned = Collections.singletonList(filter.getBanned());
        return banned;
    }
    public static void setDefaultValues(PlayerFilterDto filter) throws Exception {

        if (filter.getBefore() == null) filter.setBefore(END_DATE);
        if (filter.getAfter() == null) filter.setAfter(START_DATE);
    }

    public static Sort getSort(PlayerFilterDto filter) {

        PlayerOrder playerOrder = filter.getOrder();

            switch (playerOrder) {
                case NAME:
                    return Sort.by(Sort.Direction.ASC, "name");

                case EXPERIENCE:
                    return Sort.by(Sort.Direction.ASC, "experience");

                case BIRTHDAY:
                    return Sort.by(Sort.Direction.ASC, "birthday");

                case LEVEL:
                    return Sort.by(Sort.Direction.ASC, "level");

                default:
                    return Sort.by(Sort.Direction.ASC, "id");
            }

    }
    public static Integer calculateLevel(Integer exp) {
        return (int) ((Math.sqrt(2500 + 200 * exp) - 50) * 0.01);
    }

    public static Integer calculateUntilNextLevel (Integer exp, Integer lvl) {
        return 50 * (lvl + 1) * (lvl + 2) - exp;
    }

}
