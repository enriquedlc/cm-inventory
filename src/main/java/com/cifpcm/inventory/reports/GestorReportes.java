package com.cifpcm.inventory.reports;

import com.cifpcm.inventory.mediator.MediatorInterface;
import com.cifpcm.inventory.models.marcaje.Marcaje;
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
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al generar los informes.");
        }
    }

    private static Date parseFecha(String fechaStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.parse(fechaStr);
    }

    private static void mostrarFichajesProductoPorFecha(MediatorInterface mediator, Scanner scanner) throws ParseException {
        System.out.print("Introduce el ID del producto: ");
        int idProducto = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Introduce la fecha de inicio (yyyy-MM-dd HH:mm:ss): ");
        Date fechaInicio = parseFecha(scanner.nextLine());

        System.out.print("Introduce la fecha de fin (yyyy-MM-dd HH:mm:ss): ");
        Date fechaFin = parseFecha(scanner.nextLine());

        List<Marcaje> fichajes = mediator.getMarcajesByProducto(idProducto, fechaInicio, fechaFin);
        mostrarInforme(fichajes, "InformeMarcajesProducto.pdf", "Informe de Fichajes del Producto");
    }

    private static void mostrarFichajesAulaPorFecha(MediatorInterface mediator, Scanner scanner) throws ParseException {
        System.out.print("Introduce el ID del aula: ");
        int idAula = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Introduce la fecha de inicio (yyyy-MM-dd HH:mm:ss): ");
        Date fechaInicio = parseFecha(scanner.nextLine());

        System.out.print("Introduce la fecha de fin (yyyy-MM-dd HH:mm:ss): ");
        Date fechaFin = parseFecha(scanner.nextLine());

        List<Marcaje> fichajes = mediator.getMarcajesByAula(idAula, fechaInicio, fechaFin);
        mostrarInforme(fichajes, "InformeMarcajesAula.pdf", "Informe de Fichajes de Productos de un Aula");
    }

    private static void mostrarFichajesProductoYAula(MediatorInterface mediator, Scanner scanner) {
        System.out.print("Introduce el ID del producto: ");
        int idProducto = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Introduce el ID del aula: ");
        int idAula = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        List<Marcaje> fichajes = mediator.getMarcajesByProductoYAula(idProducto, idAula);
        mostrarInforme(fichajes, "InformeMarcajesProductoYAula.pdf", "Informe de Fichajes de un Producto y un Aula");
    }

    private static void informeDeteccionErrores(MediatorInterface mediator, Scanner scanner) throws ParseException {
        System.out.print("Introduce la fecha de inicio (yyyy-MM-dd HH:mm:ss): ");
        Date fechaInicio = parseFecha(scanner.nextLine());

        System.out.print("Introduce la fecha de fin (yyyy-MM-dd HH:mm:ss): ");
        Date fechaFin = parseFecha(scanner.nextLine());

        List<Marcaje> marcajesConErrores = mediator.obtenerMarcajesConErrores(fechaInicio, fechaFin);
        mostrarInforme(marcajesConErrores, "InformeErrores.pdf", "Informe de Detección de Errores");

    }

    private static void mostrarInforme(List<Marcaje> fichajes, String nombreArchivo, String titulo) {
        if (fichajes.isEmpty()) {
            System.out.println("No se encontraron fichajes en el rango de fechas especificado");
        } else {
            System.out.println("Generando informe PDF...");

            try {
                GeneradorInformes generador = new GeneradorInformes();
                generador.generarInformePDF(fichajes, nombreArchivo, titulo);
                System.out.println("Informe generado: " + nombreArchivo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
