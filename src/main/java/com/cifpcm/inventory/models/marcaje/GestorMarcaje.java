/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cifpcm.inventory.models.marcaje;

import com.cifpcm.inventory.mediator.MediatorInterface;
import com.cifpcm.inventory.models.aula.Aula;
import com.cifpcm.inventory.models.producto.Producto;
import com.cifpcm.inventory.utils.Menu;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author tecen
 */
public class GestorMarcaje {

    public GestorMarcaje(MediatorInterface mediator) {

    }

    public static void showMenuMarcajes() {
        Marcaje marcaje = new Marcaje();
        Aula aula = new Aula();
        Producto producto = new Producto();
        int option;
        do {
            System.out.println(Menu.showSpecific("Marcaje"));
            option = Menu.getInt();
            switch (option) {
                case 1 -> {
                    List<Integer> aulasDisponibles = aula.selectAllAulas().stream().map(Aula::getIdAula).toList();
                    List<Integer> productosDisponibles = producto.selectAllProductos().stream().map(Producto::getIdProducto).toList();

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

                    marcaje.insertMarcaje(new Marcaje(idProducto, idAula, newFecha, tipoMarcaje));
                }
                case 2 -> marcaje.selectAllMarcajes().forEach(System.out::println);
                case 3 -> {
                    int idMarcaje = Menu.getInt("Introduce el id del marcaje a eliminar: ");
                    marcaje.deleteMarcaje(idMarcaje);
                }
                case 4 -> {
                    int idMarcaje = Menu.getInt("Introduce el id del marcaje a modificar: ");
                    List<Integer> aulasDisponibles = aula.selectAllAulas().stream().map(Aula::getIdAula).toList();
                    List<Integer> productosDisponibles = producto.selectAllProductos().stream().map(Producto::getIdProducto).toList();

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

                    marcaje.updateMarcaje(new Marcaje(idMarcaje, idProducto, idAula, newFecha, tipoMarcaje));
                }
                case 0 -> System.out.println("Back");
                default -> System.out.println("Invalid option. Please try again.");
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
