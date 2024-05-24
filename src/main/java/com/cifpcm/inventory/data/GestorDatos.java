package com.cifpcm.inventory.data;

import com.cifpcm.inventory.mediator.MediatorInterface;
import com.cifpcm.inventory.utils.Menu;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author tecen
 */
public class GestorDatos {
    private static final String AULA_FILE = "aulas.txt";
    private static final String PRODUCTO_FILE = "productos.txt";
    private static final String MARCAJE_FILE = "marcajes.txt";

    public static void manageData(MediatorInterface mediator, Scanner scanner, Datos datos) throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println(Menu.showData());
            System.out.print("Introduce opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> datos.cargarDatosTrabajoDesconectado(AULA_FILE, PRODUCTO_FILE, MARCAJE_FILE);
                case 2 -> datos.guardarDatosTrabajoDesconectado(AULA_FILE, PRODUCTO_FILE, MARCAJE_FILE);
                case 3 -> {
                    // Lógica para cargar datos de Base de Datos
                }
                case 4 -> {
                    // Lógica para guardar datos a Base de Datos
                }
                case 0 -> {
                    return; // Volver al menú principal
                }
                default ->
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void cargarDatosBaseDeDatos() {
        // Implementa aquí la lógica para cargar datos de la base de datos
        System.out.println("Cargando datos de la base de datos...");
    }

    private static void guardarDatosBaseDeDatos() {
        // Implementa aquí la lógica para guardar datos a la base de datos
        System.out.println("Guardando datos a la base de datos...");
    }
}
