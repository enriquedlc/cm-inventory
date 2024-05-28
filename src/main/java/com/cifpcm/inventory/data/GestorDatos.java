package com.cifpcm.inventory.data;

import com.cifpcm.inventory.mediator.Mediator;
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
    private static Datos datos = null;

    public GestorDatos(Mediator mediator) {
        datos = new Datos(mediator);
    }

    public void manageData(Scanner scanner) throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("Datos");
            System.out.println("1 - Cargar datos de trabajo desconectado");
            System.out.println("2 - Guardar datos a trabajo desconectado");
            System.out.println("3 - Cargar datos de Base de Datos");
            System.out.println("4 - Guardar datos a Base de Datos");
            System.out.println("0 - Volver");
            System.out.print("Introduzca opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    datos.cargarDatosTrabajoDesconectado(AULA_FILE, PRODUCTO_FILE, MARCAJE_FILE);
                    System.out.println("Datos cargados correctamente.");
                }
                case 2 -> {
                    datos.guardarDatosTrabajoDesconectado(AULA_FILE, PRODUCTO_FILE, MARCAJE_FILE);
                    System.out.println("Datos guardados correctamente.");
                }
                case 3 -> {
                    // Implementa la carga de datos desde la Base de Datos
                    System.out.println("Funcionalidad de carga de datos desde la BD no implementada.");
                }
                case 4 -> {
                    // Implementa el guardado de datos en la Base de Datos
                    System.out.println("Funcionalidad de guardado de datos en la BD no implementada.");
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
