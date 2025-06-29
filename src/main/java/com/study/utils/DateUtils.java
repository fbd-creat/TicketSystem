package com.study.utils;

import java.time.LocalDate;

/**
 * @author 朱天乐
 */
public class DateUtils {

    public static String getDate() {

        LocalDate date = LocalDate.now();

        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();

        return year + "/" + month + "/" + dayOfMonth;
    }

}
