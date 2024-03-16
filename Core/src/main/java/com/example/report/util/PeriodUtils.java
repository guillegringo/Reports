package com.example.report.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PeriodUtils {


    public static Date getStartOfToday() {
        LocalDate localDate = LocalDate.now();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    public static Date getEndOfTomorrow() {
        LocalDate localDate = LocalDate.now().plusDays(1);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusHours(23).plusMinutes(59).plusSeconds(59).toInstant());
    }

    public static Date getLastDayOfThisMonth() {
        LocalDate localDate = LocalDate.now();
        LocalDate lastDayOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth());
        return Date.from(lastDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    public static Date getLastDayOfThisWeek() {
        LocalDate localDate = LocalDate.now();
        LocalDate lastDayOfWeek = localDate.with(localDate.getDayOfWeek()).plusDays(6);
        return Date.from(lastDayOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date getStartOfDay(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    public static Date getEndOfNextDay(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusHours(23).plusMinutes(59).plusSeconds(59).toInstant());
    }

    public static Date getLastDayOfMonth(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate lastDayOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth());
        return Date.from(lastDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date getLastDayOfWeek(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate lastDayOfWeek = localDate.with(localDate.getDayOfWeek()).plusDays(6);
        return Date.from(lastDayOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}

