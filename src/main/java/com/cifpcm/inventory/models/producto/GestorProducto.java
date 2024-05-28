package com.cifpcm.inventory.models.producto;

import com.cifpcm.inventory.mediator.Mediator;
import com.cifpcm.inventory.models.marcaje.MarcajeManager;

import static com.cifpcm.inventory.utils.Confirm.getConfirmation;

import com.cifpcm.inventory.utils.Menu;
import com.cifpcm.inventory.utils.VerificarEntrada;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;

/**
 * @author tecen
 */
public class GestorProducto {

    private final ProductoManagerInterface productoManager;

    Mediator mediator;

    public GestorProducto(Mediator mediator) {
        this.productoManager = new ProductoManager(mediator);
        this.mediator = mediator;
    }

    public void showMenuProductos() {
        int option;
        do {
            System.out.println(Menu.showSpecific("Producto"));
            option = Menu.getInt();
            switch (option) {
                case 1 -> {
                    String ean = VerificarEntrada.getString("Introduce el EAN del producto: ");
                    String descripcion = VerificarEntrada.getString("Introduce la descripción del producto: ");
                    String keyRFID = VerificarEntrada.getString("Introduce la clave RFID del producto: ");
                    Producto producto = new Producto(descripcion, ean, keyRFID);
                    if (productoManager.insertProducto(producto)) {
                        System.out.println("Producto insertado correctamente.");
                    } else {
                        System.out.println("Error al insertar el producto.");
                    }
                    productoManager.selectAllProductos().forEach(System.out::println);
                }
                case 2 -> productoManager.selectAllProductos().forEach(System.out::println);
                case 3 -> {
                    int idProducto = VerificarEntrada.getInt("Introduce el id del producto a eliminar: ");
                    boolean confirm = getConfirmation("¿Estás seguro de que deseas eliminar el producto con ID " + idProducto + "? (s/n): ");
                    if (confirm) {
                        // Primero, eliminar todos los marcajes asociados al producto
                        MarcajeManager marcajemanager = new MarcajeManager(this.mediator);
                        boolean marcajesEliminados = marcajemanager.deleteMarcajesByProducto(idProducto);
                        if (marcajesEliminados) {
                            System.out.println("Marcajes asociados eliminados.");
                        } else {
                            System.out.println("No se encontraron marcajes asociados o no se pudieron eliminar.");
                        }

                        // Luego, eliminar el producto
                        if (productoManager.deleteProducto(idProducto)) {
                            System.out.println("Producto eliminado.");
                        } else {
                            System.out.println("No se pudo eliminar el producto.");
                        }
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                    productoManager.selectAllProductos().forEach(System.out::println);

                }
                case 4 -> {
                    int idProducto = VerificarEntrada.getInt("Introduce el id del producto a modificar: ");
                    String ean = VerificarEntrada.getString("Introduce el EAN del producto: ");
                    String descripcion = VerificarEntrada.getString("Introduce la descripción del producto: ");
                    String keyRFID = VerificarEntrada.getString("Introduce la clave RFID del producto: ");
                    Producto producto = new Producto(idProducto, descripcion, ean, keyRFID);
                    if (productoManager.updateProducto(producto)) {
                        System.out.println("Producto actualizado correctamente.");
                    } else {
                        System.out.println("Error al actualizar el producto.");
                    }
                    productoManager.selectAllProductos().forEach(System.out::println);
                }
                case 0 -> System.out.println("Back");
                default -> System.out.println("Opción inválida. Porfavor intenta de nuevo.");
            }
        } while (option != 0);
    }
}
