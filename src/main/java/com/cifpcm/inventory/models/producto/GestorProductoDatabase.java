/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cifpcm.inventory.models.producto;

import com.cifpcm.inventory.mediator.MediatorInterface;
import com.cifpcm.inventory.models.marcaje.Marcaje;
import com.cifpcm.inventory.utils.Confirm;
import static com.cifpcm.inventory.utils.Confirm.getConfirmation;
import com.cifpcm.inventory.utils.Menu;

/**
 *
 * @author tecen
 */
public class GestorProducto {

    public GestorProducto(MediatorInterface mediator) {

    }

    public static void showMenuProductos() {
        Producto producto = new Producto();
        int option;
        do {
            System.out.println(Menu.showSpecific("Producto"));
            option = Menu.getInt();
            switch (option) {
                case 1 -> {
                    String ean = Menu.getString("Introduce el EAN del producto: ");
                    String descripcion = Menu.getString("Introduce la descripción del producto: ");
                    String keyRFID = Menu.getString("Introduce la clave RFID del producto: ");
                    producto.insertProducto(new Producto(descripcion, ean, keyRFID));
                    producto.selectAllProductos().forEach(System.out::println);
                }
                case 2 ->
                    producto.selectAllProductos().forEach(System.out::println);
                case 3 -> {
                    int idProducto = Menu.getInt("Introduce el id del producto a eliminar: ");
                    boolean confirm = getConfirmation("¿Estás seguro de que deseas eliminar el producto con ID " + idProducto + "? (s/n): ");
                    if (confirm) {
                        // Primero, eliminar todos los marcajes asociados al producto
                        Marcaje marcaje = new Marcaje();
                        boolean marcajesEliminados = marcaje.deleteMarcajesByProducto(idProducto);
                        if (marcajesEliminados) {
                            System.out.println("Marcajes asociados eliminados.");
                        } else {
                            System.out.println("No se encontraron marcajes asociados o no se pudieron eliminar.");
                        }

                        // Luego, eliminar el producto
                        boolean productoEliminado = producto.deleteProducto(idProducto);
                        if (productoEliminado) {
                            System.out.println("Producto eliminado.");
                        } else {
                            System.out.println("No se pudo eliminar el producto.");
                        }
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                    producto.selectAllProductos().forEach(System.out::println);

                }
                case 4 -> {
                    int idProducto = Menu.getInt("Introduce el id del producto a modificar: ");
                    String ean = Menu.getString("Introduce el EAN del producto: ");
                    String descripcion = Menu.getString("Introduce la descripción del producto: ");
                    String keyRFID = Menu.getString("Introduce la clave RFID del producto: ");
                    producto.updateProducto(new Producto(idProducto, descripcion, ean, keyRFID));
                    producto.selectAllProductos().forEach(System.out::println);
                }
                case 0 ->
                    System.out.println("Back");
                default ->
                    System.out.println("Opción inválida. Porfavor intenta de nuevo.");
            }
        } while (option != 0);
    }

}
