package com.cifpcm.inventory.models.producto;

import java.util.ArrayList;

/**
 *
 * @author tecen
 */
public interface ProductoManagerInterface {

    boolean insertProducto(Producto producto);

    boolean updateProducto(Producto producto);

    boolean deleteProducto(int id);

    Producto selectProducto(int id);

    ArrayList<Producto> selectAllProductos();
    
    boolean exists(int idProducto);
}
