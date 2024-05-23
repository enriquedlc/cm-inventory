package com.cifpcm.inventory.utils;

import java.util.Scanner;

/**
 *
 * @author tecen
 */
public class Confirm {
    public static boolean getConfirmation(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("s");
    }
}
