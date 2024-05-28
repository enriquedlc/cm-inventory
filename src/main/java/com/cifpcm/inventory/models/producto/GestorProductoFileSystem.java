package com.cifpcm.inventory.models.producto;

import com.cifpcm.inventory.mediator.Mediator;
import com.cifpcm.inventory.utils.Menu;

public class GestorProductoFileSystem {
    private static Mediator mediator = null;
    public GestorProductoFileSystem(Mediator mediator) {
        GestorProductoFileSystem.mediator = mediator;
    }

    public void showMenu() {
        ProductoFileSystem producto = new ProductoFileSystem(mediator);
        int option;

        do {
            System.out.println(Menu.showSpecific("Producto"));
            option = Menu.getInt();
            switch (option) {
                case 1 -> {
                    String descripcion = Menu.getString("Introduce la descripción del producto: ");
                    String EAN13 = Menu.getString("Introduce el EAN13 del producto: ");
                    String keyRFID = Menu.getString("Introduce la clave RFID del producto: ");
                    producto.insertProducto(new ProductoFileSystem(descripcion, EAN13, keyRFID));
                    producto.selectAllProductos().forEach(System.out::println);
                }
                case 2 -> producto.selectAllProductos().forEach(System.out::println);
                case 3 -> {
                    producto.selectAllProductos().forEach(System.out::println);
                    int idProducto = Menu.getInt("Introduce el id del producto a eliminar: ");
                    boolean deleted = producto.deleteProducto(idProducto);
                    if (deleted) {
                        System.out.println("Producto eliminado.");
                    } else {
                        System.out.println("No se pudo eliminar el producto.");
                    }
                    producto.selectAllProductos().forEach(System.out::println);
                }
                case 4 -> {
                    producto.selectAllProductos().forEach(System.out::println);
                    int idProducto = Menu.getInt("Introduce el id del producto a modificar: ");
                    String descripcion = Menu.getString("Introduce la descripción del producto: ");
                    String EAN13 = Menu.getString("Introduce el EAN13 del producto: ");
                    String keyRFID = Menu.getString("Introduce la clave RFID del producto: ");
                    producto.updateProducto(new ProductoFileSystem(idProducto, descripcion, EAN13, keyRFID));
                    producto.selectAllProductos().forEach(System.out::println);
                }
                case 0 -> System.out.println("Back");
                default -> System.out.println("Opción inválida. Porfavor intenta de nuevo.");
            }
        } while (option != 0);
    }
}
