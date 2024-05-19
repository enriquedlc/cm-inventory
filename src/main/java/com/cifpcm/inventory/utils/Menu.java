package com.cifpcm.inventory.utils;

public class Menu {
    public static String showMain() {
        return Font.blueBold("1") + " - Gestión Aulas\n" +
                Font.blueBold("2") + " - Gestión Productos\n" +
                Font.blueBold("3") + " - Gestión Marcajes\n" +
                Font.blueBold("4") + " - Informes\n" +
                Font.blueBold("5") + " - Datos\n" +
                Font.blueBold("0") + " - Salir\n";
    }

    public static String showSpecific(String toShow) {
        return Font.blueBold("1") + " - Crear " + toShow + "\n" +
                Font.blueBold("2") + " - Listar " + toShow + "s\n" +
                Font.blueBold("3") + " - Eliminar " + toShow + "\n" +
                Font.blueBold("4") + " - Modificar " + toShow + "\n" +
                Font.blueBold("0") + " - Volver\n";
    }

    public static String showData() {
        return Font.blueBold("1") + " - Cargar datos de " + Font.bold("trabajo desconectado\n") +
                Font.blueBold("2") + " - Guardar datos a " + Font.bold("trabajo desconectado\n") +
                Font.blueBold("3") + " - Cargar datos de " + Font.bold("Base de Datos\n") +
                Font.blueBold("4") + " - Guardar datos a " + Font.bold("Base de Datos\n") +
                Font.blueBold("0") + " - Volver\n";
    }

    public static String showReports() {
        return Font.blueBold("1") + " - Mostrar los fichajes de un producto ordenador por fecha " + Font.bold("(entre dos fechas).\n") +
                Font.blueBold("2") + " - Mostrar los fichajes de un aula " + Font.bold("(entre dos fechas).\n") +
                Font.blueBold("3") + " - Mostar los fichajes de un producto y un aula (o pabellones)\n" +
                Font.blueBold("4") + " - Informe a crear por el alumno " + Font.bold("(Detección de errores equipos/en segunda planta)\n") +
                Font.blueBold("0") + " - Volver\n";
    }
}

