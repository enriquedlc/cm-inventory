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
        Menu.manageSpecific("Producto");
    }

}
