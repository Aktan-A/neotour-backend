package com.neobis.neotour.util;

import com.neobis.neotour.enums.Season;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Component
public class SeasonUtil {

    public Season getCurrentSeason() {
        Month currentMonth = LocalDate.now().getMonth();

        if (Arrays.asList(Month.DECEMBER, Month.JANUARY, Month.FEBRUARY).contains(currentMonth)) {
            return Season.WINTER;
        } else if (Arrays.asList(Month.MARCH, Month.APRIL, Month.MAY).contains(currentMonth)) {
            return Season.SPRING;
        } else if (Arrays.asList(Month.JUNE, Month.JULY, Month.AUGUST).contains(currentMonth)) {
            return Season.SUMMER;
        } else {
            return Season.AUTUMN;
        }

    }

}
