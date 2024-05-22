package com.cifpcm.inventory.models.producto;

import java.util.ArrayList;

public interface ProductoInterface {
    int getIdProducto();
    String getDescripcion();
    String getEan();
    String getKeyRFID();
    
    public boolean insertProducto(Producto producto);
    public boolean updateProducto(Producto producto); 
    public boolean deleteProducto(int id); 
    public Producto selectProducto(int id);
    public ArrayList<Producto> selectAllProductos();

}