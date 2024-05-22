package com.cifpcm.inventory;

import com.cifpcm.inventory.mediator.Mediator;

import java.util.Scanner;
import com.cifpcm.inventory.mediator.MediatorInterface;
import com.cifpcm.inventory.models.aula.GestorAula;
import com.cifpcm.inventory.models.marcaje.GestorMarcaje;
import com.cifpcm.inventory.models.producto.GestorProducto;

public class Main {
    public static void main(String[] args) {
        MediatorInterface mediator = new Mediator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu");
            System.out.println("1 - Gestión Aulas.");
            System.out.println("2 - Gestión Productos.");
            System.out.println("3 - Gestión Marcajes.");
            System.out.println("4 - Informes.");
            System.out.println("5 – Datos.");
            System.out.println("0 - Salir");
            System.out.print("Introduzca opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> GestorAula.showMenuAulas();
                case 2 -> GestorProducto.showMenuProductos();
                case 3 -> GestorMarcaje.showMenuMarcajes();
                case 4 -> generateReports(mediator, scanner);
                case 5 -> manageData(mediator, scanner);
                case 0 -> System.exit(0);
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private static void manageAulas(MediatorInterface mediator, Scanner scanner) {
        // Implementar el submenú de gestión de aulas
    }

    private static void manageProductos(MediatorInterface mediator, Scanner scanner) {
        // Implementar el submenú de gestión de productos
    }

    private static void manageMarcajes(MediatorInterface mediator, Scanner scanner) {
        // Implementar el submenú de gestión de marcajes
    }

    private static void generateReports(MediatorInterface mediator, Scanner scanner) {
        // Implementar el submenú de generación de informes
    }

    private static void manageData(MediatorInterface mediator, Scanner scanner) {
        // Implementar el submenú de gestión de datos
    }
}
