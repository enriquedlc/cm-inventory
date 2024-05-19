package com.cifpcm.inventory;

public class Color {
    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[34m";
    public static final String BOLD = "\033[1m";

    public static String blueBold(String text) {
        return BLUE + BOLD + text + RESET;
    }
}

