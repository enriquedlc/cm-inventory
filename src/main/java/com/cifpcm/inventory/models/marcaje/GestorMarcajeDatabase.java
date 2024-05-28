/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cifpcm.inventory.models.marcaje;

import com.cifpcm.inventory.mediator.MediatorInterface;
import com.cifpcm.inventory.models.aula.AulaDatabase;
import com.cifpcm.inventory.models.aula.AulaInterface;
import com.cifpcm.inventory.models.marcaje.enums.TipoMarcaje;
import com.cifpcm.inventory.models.producto.ProductoDatabase;
import com.cifpcm.inventory.models.producto.ProductoInterface;
import com.cifpcm.inventory.utils.Confirm;
import com.cifpcm.inventory.utils.Menu;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author tecen
 */
public class GestorMarcajeDatabase {

    public GestorMarcajeDatabase(MediatorInterface mediator) {

    }

    public static void showMenuMarcajes() {
        MarcajeDatabase marcaje = new MarcajeDatabase();
        AulaDatabase aulaDatabase = new AulaDatabase();
        ProductoDatabase producto = new ProductoDatabase();
        int option;
        do {
            System.out.println(Menu.showSpecific("Marcaje"));
            option = Menu.getInt();
            switch (option) {
                case 1 -> {
                    List<Integer> aulasDisponibles = aulaDatabase.selectAllAulas().stream().map(AulaInterface::getIdAula).toList();
                    List<Integer> productosDisponibles = producto.selectAllProductos().stream().map(ProductoInterface::getIdProducto).toList();

                    System.out.print("Aulas: ");
                    aulasDisponibles.forEach(id -> System.out.print(id + " "));
                    System.out.println();

                    int idAula = getValidId("Introduce el id del aula: ", aulasDisponibles);

                    System.out.print("Productos: ");
                    productosDisponibles.forEach(id -> System.out.print(id + " "));
                    System.out.println();

                    int idProducto = getValidId("Introduce el id del producto: ", productosDisponibles);

                    Date newFecha = getValidDate();

                    TipoMarcaje tipoMarcaje = Menu.getTipoMarcaje();

                    marcaje.insertMarcaje(new MarcajeDatabase(idProducto, idAula, newFecha, tipoMarcaje));
                }
                case 2 -> marcaje.selectAllMarcajes().forEach(System.out::println);

                case 3 -> {
                    int idMarcaje = Menu.getInt("Introduce el id del marcaje a eliminar: ");
                    boolean confirm = Confirm.getConfirmation("¿Estás seguro de que deseas eliminar el marcaje con ID " + idMarcaje + "? (s/n): ");
                    if (confirm) {
                        marcaje.deleteMarcaje(idMarcaje);
                        System.out.println("Marcaje eliminado.");
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                    marcaje.selectAllMarcajes().forEach(System.out::println);
                }
                case 4 -> {
                    int idMarcaje = Menu.getInt("Introduce el id del marcaje a modificar: ");
                    List<Integer> aulasDisponibles = aulaDatabase.selectAllAulas().stream().map(AulaInterface::getIdAula).toList();
                    List<Integer> productosDisponibles = producto.selectAllProductos().stream().map(ProductoInterface::getIdProducto).toList();

                    System.out.print("Aulas: ");
                    aulasDisponibles.forEach(id -> System.out.print(id + " "));
                    System.out.println();

                    int idAula = getValidId("Introduce el id del aula: ", aulasDisponibles);

                    System.out.print("Productos: ");
                    productosDisponibles.forEach(id -> System.out.print(id + " "));
                    System.out.println();

                    int idProducto = getValidId("Introduce el id del producto: ", productosDisponibles);

                    Date newFecha = getValidDate();

                    TipoMarcaje tipoMarcaje = Menu.getTipoMarcaje();

                    marcaje.updateMarcaje(new MarcajeDatabase(idMarcaje, idProducto, idAula, newFecha, tipoMarcaje));
                }
                case 0 -> System.out.println("Back");
                default -> System.out.println("Opción inválida. Porfavor intenta de nuevo.");
            }
        } while (option != 0);
    }

    private static int getValidId(String prompt, List<Integer> validIds) {
        int id;
        do {
            id = Menu.getInt(prompt);
            if (!validIds.contains(id)) {
                System.out.println("ID inválido. Por favor, introduce un ID de la lista.");
            }
        } while (!validIds.contains(id));
        return id;
    }

    private static Date getValidDate() {
        String dateString;
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        boolean valid = false;
        while (!valid) {
            dateString = Menu.getString("Introduce la fecha del marcaje (yyyy-MM-dd): ");
            try {
                date = new Date(format.parse(dateString).getTime());
                valid = true;
            } catch (ParseException e) {
                System.out.println("Fecha inválida. Por favor, introduce una fecha en el formato yyyy-MM-dd.");
            }
        }
        return date;
    }
}
