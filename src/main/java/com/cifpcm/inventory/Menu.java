package com.cifpcm.inventory;

public class Menu {
    public static String showMain() {
        return Color.blueBold("1") + " - Gestión Aulas\n" +
                Color.blueBold("2") + " - Gestión Productos\n" +
                Color.blueBold("3") + " - Gestión Marcajes\n" +
                Color.blueBold("4") + " - Informes\n" +
                Color.blueBold("5") + " - Datos\n" +
                Color.blueBold("0") + " - Salir\n";
    }

    public static String showSpecific(String toShow) {
        return Color.blueBold("1") + " - Crear " + toShow + "\n" +
                Color.blueBold("2") + " - Listar " + toShow + "s\n" +
                Color.blueBold("3") + " - Eliminar " + toShow + "\n" +
                Color.blueBold("4") + " - Modificar " + toShow + "\n" +
                Color.blueBold("0") + " - Volver\n";
    }
}

