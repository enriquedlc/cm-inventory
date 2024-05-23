/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cifpcm.inventory.models.producto;

import com.cifpcm.inventory.mediator.MediatorInterface;
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
                }
                case 2 -> producto.selectAllProductos().forEach(System.out::println);
                case 3 -> {
                    int idProducto = Menu.getInt("Introduce el id del producto a eliminar: ");
                    producto.deleteProducto(idProducto);
                }
                case 4 -> {
                    int idProducto = Menu.getInt("Introduce el id del producto a modificar: ");
                    String ean = Menu.getString("Introduce el EAN del producto: ");
                    String descripcion = Menu.getString("Introduce la descripción del producto: ");
                    String keyRFID = Menu.getString("Introduce la clave RFID del producto: ");
                    producto.updateProducto(new Producto(idProducto, descripcion, ean, keyRFID));
                }
                case 0 -> System.out.println("Back");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 0);
    }

}
