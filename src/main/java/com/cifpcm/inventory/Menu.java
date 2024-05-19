package com.cifpcm.inventory;

public class Menu {
    public static String showMain() {
         return "1 - Gestión Aulas\n" +
                "2 - Gestión Productos\n" +
                "3 - Gestión Marcajes\n" +
                "4 - Informes\n" +
                "5 - Datos\n" +
                "0 - Salir\n";
    }

    public static String showSpecific(String toShow) {
        return "1 - Crear " + toShow + "\n" +
                "2 - Listar " + toShow + "s\n" +
                "3 - Eliminar " + toShow + "\n" +
                "4 - Modificar " + toShow + "\n" +
                "0 - Volver\n";
    }
}
