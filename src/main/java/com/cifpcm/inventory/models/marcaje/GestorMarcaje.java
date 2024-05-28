package com.cifpcm.inventory.models.marcaje;

import com.cifpcm.inventory.mediator.Mediator;
import com.cifpcm.inventory.mediator.MediatorInterface;
import com.cifpcm.inventory.models.aula.Aula;
import com.cifpcm.inventory.models.aula.AulaManager;
import com.cifpcm.inventory.models.aula.AulaManagerInterface;
import com.cifpcm.inventory.models.producto.Producto;
import com.cifpcm.inventory.models.producto.ProductoManager;
import com.cifpcm.inventory.models.producto.ProductoManagerInterface;
import com.cifpcm.inventory.utils.Menu;
import com.cifpcm.inventory.utils.VerificarEntrada;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tecen
 */
public class GestorMarcaje {

    MarcajeManagerInterface marcajeManager;
    AulaManagerInterface aulaManager;
    ProductoManagerInterface productoManager;

    public GestorMarcaje(Mediator mediator) {
    }

    public void showMenuMarcajes() {
        int option;

        do {
            System.out.println(Menu.showSpecific("Marcaje"));
            option = VerificarEntrada.getInt("Selecciona una opción: ");

            switch (option) {
                case 1 -> {
                    List<Integer> aulasDisponibles = aulaManager.selectAllAulas().stream().map(Aula::getIdAula).toList();
                    List<Integer> productosDisponibles = productoManager.selectAllProductos().stream().map(Producto::getIdProducto).toList();

                    System.out.print("Aulas disponibles: ");
                    aulasDisponibles.forEach(id -> System.out.print(id + " "));
                    System.out.println();

                    int idAula = getValidId("Introduce el id del aula: ", aulasDisponibles);

                    System.out.print("Productos disponibles: ");
                    productosDisponibles.forEach(id -> System.out.print(id + " "));
                    System.out.println();

                    int idProducto = getValidId("Introduce el id del producto: ", productosDisponibles);

                    Date newFecha = getValidDate();

                    TipoMarcaje tipoMarcaje = Menu.getTipoMarcaje();

                    Marcaje marcaje = new Marcaje(idProducto, idAula, newFecha, tipoMarcaje);
                    marcajeManager.insertMarcaje(marcaje);
                }
                case 2 -> marcajeManager.getAllMarcajes().forEach(System.out::println);

                case 3 -> {
                    int idMarcaje = VerificarEntrada.getInt("Introduce el id del marcaje a eliminar: ");
                    boolean confirm = getConfirmation("¿Estás seguro de que deseas eliminar el marcaje con ID " + idMarcaje + "? (s/n): ");
                    if (confirm) {
                        marcajeManager.deleteMarcaje(idMarcaje);
                        System.out.println("Marcaje eliminado.");
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                    marcajeManager.getAllMarcajes().forEach(System.out::println);
                }
                case 4 -> {
                    int idMarcaje = VerificarEntrada.getInt("Introduce el id del marcaje a modificar: ");
                    List<Integer> aulasDisponibles = aulaManager.selectAllAulas().stream().map(Aula::getIdAula).toList();
                    List<Integer> productosDisponibles = productoManager.selectAllProductos().stream().map(Producto::getIdProducto).toList();

                    System.out.print("Aulas disponibles: ");
                    aulasDisponibles.forEach(id -> System.out.print(id + " "));
                    System.out.println();

                    int idAula = getValidId("Introduce el id del aula: ", aulasDisponibles);

                    System.out.print("Productos disponibles: ");
                    productosDisponibles.forEach(id -> System.out.print(id + " "));
                    System.out.println();

                    int idProducto = getValidId("Introduce el id del producto: ", productosDisponibles);

                    Date newFecha = getValidDate();

                    TipoMarcaje tipoMarcaje = Menu.getTipoMarcaje();

                    Marcaje marcaje = new Marcaje(idMarcaje, idProducto, idAula, newFecha, tipoMarcaje);
                    marcajeManager.updateMarcaje(marcaje);
                }
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida. Por favor, inténtalo de nuevo.");
            }
        } while (option != 0);
    }

    private int getValidId(String prompt, List<Integer> validIds) {
        int id;
        do {
            id = VerificarEntrada.getInt(prompt);
            if (!validIds.contains(id)) {
                System.out.println("ID inválido. Por favor, introduce un ID de la lista.");
            }
        } while (!validIds.contains(id));
        return id;
    }

    private Date getValidDate() {
        String dateString;
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setLenient(false);
        boolean valid = false;
        while (!valid) {
            dateString = VerificarEntrada.getString("Introduce la fecha del marcaje (yyyy-MM-dd HH:mm:ss): ");
            try {
                date = (Date) format.parse(dateString);
                valid = true;
            } catch (ParseException e) {
                System.out.println("Fecha inválida. Por favor, introduce una fecha en el formato yyyy-MM-dd HH:mm:ss.");
            }
        }
        return date;
    }

    private boolean getConfirmation(String prompt) {
        String input;
        do {
            input = VerificarEntrada.getString(prompt).toLowerCase();
            if (!input.equals("s") && !input.equals("n")) {
                System.out.println("Por favor, introduce 's' para sí o 'n' para no.");
            }
        } while (!input.equals("s") && !input.equals("n"));
        return input.equals("s");
    }

}
