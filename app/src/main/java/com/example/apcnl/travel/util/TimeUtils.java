package com.example.apcnl.travel.util;

public class TimeUtils {
    public static final String TAG = "TimeUtils";

    public static final int HOUR = 60 * 60 * 1000;
    public static final int MINUTE = 60 * 1000;
    public static final int SECOND = 1000;
    public static final int HOUR2 = 60 * 60;
    public static final int MINUTE2 = 60;
    public static final int SECOND2 = 1;

    public static String parseTime4Mills(int time) {
        int hour = time / HOUR;
        int minute = time % HOUR / MINUTE;
        int second = time % HOUR % MINUTE / SECOND;
        String result = "";
        if (hour > 0) {
            result = String.format("%02d:%02d:%02d", hour, minute, second);
        } else {
            result = String.format("%02d:%02d", minute, second);
        }
        return result;
    }
    public static String parseTime4Second(int time) {
        int hour = time / HOUR2;
        int minute = time % HOUR2 / MINUTE2;
        int second = time % HOUR2 % MINUTE2 / SECOND2;
        String result = "";
        if (hour > 0) {
            result = String.format("%02d:%02d:%02d", hour, minute, second);
        } else {
            result = String.format("%02d:%02d", minute, second);
        }
        return result;
    }
}
