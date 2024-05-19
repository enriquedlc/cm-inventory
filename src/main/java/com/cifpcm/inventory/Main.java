package com.cifpcm.inventory;

public class Main {
    public static void main(String[] args) {
        System.out.println(Menu.showMain());

        System.out.println(Menu.showSpecific("Aula"));
        System.out.println(Menu.showSpecific("Producto"));
        System.out.println(Menu.showSpecific("Marcaje"));
    }
}
