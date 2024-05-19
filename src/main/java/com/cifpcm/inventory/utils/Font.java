package com.cifpcm.inventory.utils;

public class Font {
    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[34m";
    public static final String BOLD = "\033[1m";

    public static String blueBold(String text) {
        return BLUE + BOLD + text + RESET;
    }

    public static String bold(String text) {
        return BOLD + text + RESET;
    }
}
