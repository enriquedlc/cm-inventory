package com.cifpcm.inventory;

import com.cifpcm.inventory.utils.Menu;

import static com.cifpcm.inventory.utils.Menu.manageSpecific;

public class Main {
    public static void main(String[] args) {
        int option = 1;
        while (option != 0) {

            System.out.println(Menu.showMain());
            option = Menu.getOption();

            switch (option) {
                case 1 -> manageSpecific("Aula");
                case 2 -> manageSpecific("Producto");
                case 3 -> manageSpecific("Marcaje");
                case 4 -> System.out.println(Menu.showReports());
                case 5 -> System.out.println(Menu.showData());
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        }
    }
}
