package com.corbandalas.domain.utils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private final static String NON_THIN = "[^iIl1\\.,']";
    private static final Pattern REMOVE_TAGS = Pattern.compile("<.+?>|&nbsp;");

    private static int textWidth(String str) {
        return (int) (str.length() - str.replaceAll(NON_THIN, "").length() / 2);
    }

    public static String abbreviate(String text, int max) {

        if (textWidth(text) <= max)
            return text;

        // Start by chopping off at the word before max
        // This is an over-approximation due to thin-characters...
        int end = text.lastIndexOf(' ', max - 3);

        // Just one long word. Chop it off.
        if (end == -1)
            return text.substring(0, max - 3) + "...";

        // Step forward as long as textWidth allows.
        int newEnd = end;
        do {
            end = newEnd;
            newEnd = text.indexOf(' ', end + 1);

            // No more spaces.
            if (newEnd == -1)
                newEnd = text.length();

        } while (textWidth(text.substring(0, newEnd) + "...") < max);

        return text.substring(0, end) + "...";
    }

    public static Date dateFromSeconds(long seconds) {
        long millis = seconds * 1000;

        return new Date(millis);
    }


    public static String removeTags(String text) {
        if (text == null || text.length() == 0) {
            return text;
        }

        Matcher m = REMOVE_TAGS.matcher(text);
        return m.replaceAll(" ");
    }
}
