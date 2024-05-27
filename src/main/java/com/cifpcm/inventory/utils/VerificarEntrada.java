
package com.cifpcm.inventory.utils;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author tecen
 */
public class VerificarEntrada {
    private static Scanner scanner = new Scanner(System.in);

    // Método para verificar una entrada no vacía
    public static String getString(String mensaje) {
        String entrada;
        do {
            System.out.print(mensaje);
            entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("La entrada no puede estar vacía. Por favor, inténtalo de nuevo.");
            }
        } while (entrada.isEmpty());
        return entrada;
    }

    // Método para verificar una entrada numérica
    public static int getInt(String mensaje) {
        int numero;
        while (true) {
            System.out.print(mensaje);
            try {
                numero = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
            }
        }
        return numero;
    }

    // Método para verificar una dirección IP válida
    public static String getIp(String mensaje) {
        String ip;
        String ipPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        do {
            System.out.print(mensaje);
            ip = scanner.nextLine().trim();
            if (!Pattern.matches(ipPattern, ip)) {
                System.out.println("Por favor, introduce una dirección IP válida.");
            }
        } while (!Pattern.matches(ipPattern, ip));
        return ip;
    }

    // Método para verificar una dirección de correo electrónico válida
    public static String getEmail(String mensaje) {
        String email;
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        do {
            System.out.print(mensaje);
            email = scanner.nextLine().trim();
            if (!Pattern.matches(emailPattern, email)) {
                System.out.println("Por favor, introduce una dirección de correo electrónico válida.");
            }
        } while (!Pattern.matches(emailPattern, email));
        return email;
    }

    // Método para verificar un número de teléfono válido
    public static String getPhoneNumber(String mensaje) {
        String phoneNumber;
        String phonePattern = "^[+]?[0-9]{10,13}$";
        do {
            System.out.print(mensaje);
            phoneNumber = scanner.nextLine().trim();
            if (!Pattern.matches(phonePattern, phoneNumber)) {
                System.out.println("Por favor, introduce un número de teléfono válido.");
            }
        } while (!Pattern.matches(phonePattern, phoneNumber));
        return phoneNumber;
    }

    // Método para verificar una URL válida
    public static String getUrl(String mensaje) {
        String url;
        String urlPattern = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
        do {
            System.out.print(mensaje);
            url = scanner.nextLine().trim();
            if (!Pattern.matches(urlPattern, url)) {
                System.out.println("Por favor, introduce una URL válida.");
            }
        } while (!Pattern.matches(urlPattern, url));
        return url;
    }

    // Método para verificar una fecha y hora en formato "yyyy-MM-dd HH:mm:ss"
    public static String getDateTime(String mensaje) {
        String dateTime;
        String dateTimePattern = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
        do {
            System.out.print(mensaje);
            dateTime = scanner.nextLine().trim();
            if (!Pattern.matches(dateTimePattern, dateTime)) {
                System.out.println("Por favor, introduce una fecha y hora válida en formato yyyy-MM-dd HH:mm:ss.");
            }
        } while (!Pattern.matches(dateTimePattern, dateTime));
        return dateTime;
    }

    // Método para verificar una opción de menú específica
    public static int getMenuOption(String mensaje, int min, int max) {
        int option;
        do {
            option = getInt(mensaje);
            if (option < min || option > max) {
                System.out.println("Por favor, introduce una opción válida entre " + min + " y " + max + ".");
            }
        } while (option < min || option > max);
        return option;
    }
}
