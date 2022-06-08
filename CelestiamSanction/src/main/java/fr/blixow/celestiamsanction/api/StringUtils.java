package fr.blixow.celestiamsanction.api;

import org.bukkit.Bukkit;

import java.util.regex.Pattern;

public class StringUtils {

    /*
     text: 1y 3mo1d1m1s || str = y
     1 y = 31557600s
     1 mo = 2629800s
     1 d = 86400s
     1 h = 3600s
     1 m = 60s
     */

    public static int countMatches(String text, String str) {
        String[] split = text.split(str);
        if (text.contains(str) && split[0].equalsIgnoreCase(text.substring(0, text.length() - 1))) {
            return 2;
        }
        return split.length;
    }

    public static boolean isValid(String date){
        if(date.contains("y") && countMatches(date, "y") != 2){
            Bukkit.getConsoleSender().sendMessage("y");
            return false;
        }
        date = date.replaceAll("y", "");

        if(date.contains("mo") && countMatches(date, "mo") != 2){
            Bukkit.getConsoleSender().sendMessage(String.valueOf(countMatches(date, "mo")));
            return false;
        }
        date = date.replaceAll("mo", "");

        if(date.contains("d") && countMatches(date, "d") != 2){
            Bukkit.getConsoleSender().sendMessage("d");
            return false;
        }
        date = date.replaceAll("d", "");

        if(date.contains("h") && countMatches(date, "h") != 2){
            Bukkit.getConsoleSender().sendMessage("h");
            return false;
        }
        date = date.replaceAll("h", "");

        if(date.contains("m") && countMatches(date, "m") != 2){
            Bukkit.getConsoleSender().sendMessage("m");
            return false;
        }
        date = date.replaceAll("m", "");

        if(date.contains("s") && countMatches(date, "s") != 2){
            Bukkit.getConsoleSender().sendMessage("s");
            return false;
        }
        date = date.replaceAll("s", "");

        return !containsLetter(date);
    }

    public static boolean containsLetter(String str){
        return Pattern.matches("[a-zA-Z]+", str);
    }

    // 1y3mo1d1m1s
    public static long stringToUnixInteger(String date){
        long val = 0;
        boolean valid = isValid(date);
        if(!valid){ return -1; }
        date = date.toLowerCase();
        if (date.contains("y") && countMatches(date, "y") == 2){
            String year_str = date.split("y")[0];
            if(CheckUtils.isLong(year_str)){
                val += Long.parseLong(year_str) * 31557600;
            }
            date = date.split("y")[1];
        } else if (date.contains("mo") && countMatches(date, "mo") == 2) {
            String month_str =  date.split("mo")[0];
            if (CheckUtils.isLong(month_str)) {
                val += Long.parseLong(month_str) * 2629800 ;
            }
            date = date.split("d")[1];
        } else if (date.contains("d") && countMatches(date, "d") == 2) {
            String day_str = date.split("d")[0];
            if (CheckUtils.isLong(day_str)) {
                val += Long.parseLong(day_str) * 86400;
            }
            date = date.split("h")[1];
        } else if (date.contains("h") && countMatches(date, "h") == 2) {
            String hours_str = date.split("h")[0];
            if (CheckUtils.isLong(hours_str)) {
                val += Long.parseLong(hours_str) * 3600;
            }
            date = date.split("m")[1];
        } else if (date.contains("m") && countMatches(date, "m") == 2) {
            String minutes_str = date.split("m")[0];
            if (CheckUtils.isLong(minutes_str)) {
                val += Long.parseLong(minutes_str) * 60;
            }
            date = date.split("m")[1];
        } else if (date.contains("s") && countMatches(date, "s") == 2) {
            String sec_str = date.split("s")[0];
            if (CheckUtils.isLong(sec_str)) {
                val += Long.parseLong(sec_str);
            }
            date = date.split("s")[1];
        }
        return val;
    }

}
