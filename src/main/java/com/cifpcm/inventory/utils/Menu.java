package com.cifpcm.inventory.utils;

import com.cifpcm.inventory.models.marcaje.TipoMarcaje;

import java.util.Scanner;

public class Menu {
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce una opción: ");
        option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public static int getInt(String message) {
        int option = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public static String getString(String message) {
        String option = "";
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        option = scanner.nextLine();
        return option;
    }

    public static TipoMarcaje getTipoMarcaje() {
        int tipoMarcajeInt = -1;
        TipoMarcaje tipoMarcaje = null;
        while (tipoMarcaje == null) {
            System.out.println("Tipo de marcaje: ");
            System.out.println("1 - Entrada");
            System.out.println("2 - Salida");
            tipoMarcajeInt = Menu.getInt("Introduce el tipo de marcaje: ");
            switch (tipoMarcajeInt) {
                case 1 -> tipoMarcaje = TipoMarcaje.ENTRADA;
                case 2 -> tipoMarcaje = TipoMarcaje.SALIDA;
                default -> System.out.println("Opción inválida. Por favor, introduce 1 para Entrada o 2 para Salida.");
            }
        }
        return tipoMarcaje;
    }
}

