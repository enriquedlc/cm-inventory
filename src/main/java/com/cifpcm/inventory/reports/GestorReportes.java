package com.cifpcm.inventory.reports;

import com.cifpcm.inventory.mediator.MediatorInterface;
import com.cifpcm.inventory.models.marcaje.MarcajeInterface;
import com.cifpcm.inventory.utils.Menu;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author tecen
 */
public class GestorReportes {

    public static void generateReports(MediatorInterface mediator, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.print(Menu.showReports());
            System.out.print("Introduce opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (option) {
                case 1:
                    mostrarFichajesProductoPorFecha(mediator, scanner);
                    break;
                case 2:
                    mostrarFichajesAulaPorFecha(mediator, scanner);
                    break;
                case 3:
                    mostrarFichajesProductoYAula(mediator, scanner);
                    break;
                case 4:
                    informeDeteccionErrores(mediator, scanner);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    
    private static Date parseFecha(String fechaStr) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
        return dateFormat.parse(fechaStr);
    } catch (ParseException e) {
        System.out.println("Error al parsear la fecha: " + e.getMessage());
        return null;
    }
}

    private static void mostrarFichajesProductoPorFecha(MediatorInterface mediator, Scanner scanner) {
    // Solicitar al usuario el ID del producto y las fechas
    System.out.print("Introduce el ID del producto: ");
    int idProducto = scanner.nextInt();
    scanner.nextLine(); // Consumir el salto de línea
    
    System.out.print("Introduce la fecha de inicio (yyyy-MM-dd HH:mm:ss): ");
    String fechaInicioStr = scanner.nextLine();
    Date fechaInicio = parseFecha(fechaInicioStr);
    
    System.out.print("Introduce la fecha de fin (yyyy-MM-dd HH:mm:ss): ");
    String fechaFinStr = scanner.nextLine();
    Date fechaFin = parseFecha(fechaFinStr);

    // Obtener los fichajes del producto entre las fechas especificadas
    List<MarcajeInterface> fichajes = mediator.getMarcajesByProducto(idProducto, fechaInicio, fechaFin);

    // Mostrar los fichajes
    if (fichajes.isEmpty()) {
        System.out.println("No se encontraron fichajes para el producto en el rango de fechas especificado.");
    } else {
        System.out.println("Fichajes del producto ordenados por fecha:");
        for (MarcajeInterface fichaje : fichajes) {
            System.out.println(fichaje);
        }
    }
}


    private static void mostrarFichajesAulaPorFecha(MediatorInterface mediator, Scanner scanner) {
        // Implementa aquí la lógica para mostrar los fichajes de un aula (entre dos fechas)
        System.out.println("Mostrando fichajes de un aula (entre dos fechas)...");
    }

    private static void mostrarFichajesProductoYAula(MediatorInterface mediator, Scanner scanner) {
        // Implementa aquí la lógica para mostrar los fichajes de un producto y un aula (o pabellones)
        System.out.println("Mostrando fichajes de un producto y un aula (o pabellones)...");
    }

    private static void informeDeteccionErrores(MediatorInterface mediator, Scanner scanner) {
        // Implementa aquí la lógica para el informe a crear por el alumno (Detección de errores equipos/en segunda planta)
        System.out.println("Generando informe de detección de errores (equipos/en segunda planta)...");
    }
}
