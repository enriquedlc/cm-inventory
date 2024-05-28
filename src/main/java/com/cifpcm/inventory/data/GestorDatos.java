package com.cifpcm.inventory.data;

import com.cifpcm.inventory.mediator.Mediator;
import com.cifpcm.inventory.mediator.MediatorInterface;
import com.cifpcm.inventory.models.aula.AulaManager;
import com.cifpcm.inventory.utils.Menu;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author tecen
 */
public class GestorDatos {
    private static final String AULA_FILE = "aulas.txt";
    private static final String PRODUCTO_FILE = "productos.txt";
    private static final String MARCAJE_FILE = "marcajes.txt";


    public static void manageData(Mediator mediator, Scanner scanner, Datos datos) throws IOException, ClassNotFoundException, ParseException {
    AulaManager aulaManager = new AulaManager(mediator); // Crear instancia de AulaManager

    while (true) {
        System.out.println(Menu.showData());
        System.out.print("Introduce opción: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> datos.cargarDatosDeArchivos(AULA_FILE, PRODUCTO_FILE, MARCAJE_FILE, aulaManager, datos); // Pasar la instancia de AulaManager
            case 2 -> datos.guardarDatosEnArchivos(AULA_FILE, PRODUCTO_FILE,MARCAJE_FILE);
            case 3 -> cargarDatosBaseDeDatos(datos);
            case 4 -> guardarDatosBaseDeDatos(datos);
            case 0 -> {
                return; // Volver al menú principal
            }
            default -> System.out.println("Opción no válida.");
        }
    }
}

    private static void cargarDatosBaseDeDatos(Datos datos) {
        // Implementa aquí la lógica para cargar datos de la base de datos
        System.out.println("Cargando datos de la base de datos...");
    }

    private static void guardarDatosBaseDeDatos(Datos datos) {
        // Implementa aquí la lógica para guardar datos a la base de datos
        System.out.println("Guardando datos a la base de datos...");
    }
}
