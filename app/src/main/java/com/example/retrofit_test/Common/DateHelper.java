package com.example.retrofit_test.Common;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class DateHelper {

    @NonNull
    public static String getCreationDate(Integer creationDate) {
        LocalDateTime localDateTime = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        LocalDateTime localDateTime1 = localDateTime.plusSeconds(creationDate);
        LocalDateTime now = LocalDateTime.now();
        if (now.getYear() == localDateTime1.getYear()) {
            if (now.getMonth() == localDateTime1.getMonth()) {
                if (now.getDayOfMonth() == localDateTime1.getDayOfMonth()) {
                    return "Today";
                } else {
                    return (now.getDayOfMonth() - localDateTime1.getDayOfMonth()) + " Days ago";
                }
            } else {
                return (now.getMonth().getValue() - localDateTime1.getMonth().getValue()) + " Months ago";
            }
        } else {
            return (now.getYear() - localDateTime1.getYear()) + " Years ago";
        }
    }

}
