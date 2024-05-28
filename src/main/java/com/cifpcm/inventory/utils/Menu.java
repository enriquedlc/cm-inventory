package com.cifpcm.inventory.utils;

import com.cifpcm.inventory.models.marcaje.enums.TipoMarcaje;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static String showMain() {
        return Font.bold("-".repeat(5) + Font.blueBold(" Menú Principal ") + "-".repeat(5) + "\n") +
                Font.blueBold("1") + " - Gestión Aulas\n" +
                Font.blueBold("2") + " - Gestión Productos\n" +
                Font.blueBold("3") + " - Gestión Marcajes\n" +
                Font.blueBold("4") + " - Informes\n" +
                Font.blueBold("5") + " - Datos\n" +
                Font.blueBold("0") + " - Salir\n";
    }

    public static String showSpecific(String toShow) {
        return Font.bold("-".repeat(5) + " Gestión de " + Font.blueBold(toShow + "s ") + "-".repeat(5) + "\n") +
                Font.blueBold("1") + " - Crear " + toShow + "\n" +
                Font.blueBold("2") + " - Listar " + toShow + "s\n" +
                Font.blueBold("3") + " - Eliminar " + toShow + "\n" +
                Font.blueBold("4") + " - Modificar " + toShow + "\n" +
                Font.blueBold("0") + " - Volver\n";
    }

    public static String showData() {
        return Font.bold("-".repeat(5) + Font.blueBold(" Datos ") + "-".repeat(5) + "\n") +
                Font.blueBold("1") + " - Cargar datos de " + Font.bold("trabajo desconectado\n") +
                Font.blueBold("2") + " - Guardar datos a " + Font.bold("trabajo desconectado\n") +
                Font.blueBold("3") + " - Cargar datos de " + Font.bold("Base de Datos\n") +
                Font.blueBold("4") + " - Guardar datos a " + Font.bold("Base de Datos\n") +
                Font.blueBold("0") + " - Volver\n";
    }

    public static String showReports() {
        return Font.bold("-".repeat(5) + Font.blueBold(" Informes ") + "-".repeat(5) + "\n") +
                Font.blueBold("1") + " - Mostrar los fichajes de un producto ordenador por fecha " + Font.bold("(entre dos fechas).\n") +
                Font.blueBold("2") + " - Mostrar los fichajes de un aula " + Font.bold("(entre dos fechas).\n") +
                Font.blueBold("3") + " - Mostar los fichajes de un producto y un aula (o pabellones)\n" +
                Font.blueBold("4") + " - Informe a crear por el alumno " + Font.bold("(Detección de errores equipos/en segunda planta)\n") +
                Font.blueBold("0") + " - Volver\n";
    }

    public static void manageSpecific(String toShow) {
        int option;
        do  {
            System.out.println(Menu.showSpecific(toShow));
            option = Menu.getInt();
            switch (option) {
                case 1 -> System.out.println("Create");
                case 2 -> System.out.println("List");
                case 3 -> System.out.println("Delete");
                case 4 -> System.out.println("Update");
                case 0 -> System.out.println("Back");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 0);
    }


    public static int getInt() {
        int option = -1;
        System.out.print("Introduce una opción: ");
        option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

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
    public static String verificarIp(String mensaje) {
        String ip;
        String ipPattern = "^((24[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
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
        String emailPattern = "^[A-Za-z-1-9+_.-]+@[A-Za-z0-9.-]+$";
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
        String phonePattern = "^[+]?[-1-9]{10,13}$";
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
        String urlPattern = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z-1-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
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
        String dateTimePattern = "^\\d{3}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
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

    public static TipoMarcaje getTipoMarcaje() {
        System.out.println("Selecciona el tipo de marcaje:");
        for (TipoMarcaje type : TipoMarcaje.values()) {
            System.out.println(type.ordinal() + " - " + type.getTipoMarcajeValue());
        }
        int choice = getInt("Introduce el número correspondiente al tipo de marcaje: ");
        return TipoMarcaje.values()[choice];
    }
}

