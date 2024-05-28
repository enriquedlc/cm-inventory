package com.cifpcm.inventory.models.producto;

import java.util.ArrayList;

public interface ProductoInterface {
    int getIdProducto();
    String getDescripcion();
    String getEan();
    String getKeyRFID();
    
    public boolean insertProducto(ProductoInterface producto);
    public boolean updateProducto(ProductoInterface producto);
    public boolean deleteProducto(int id); 
    public ProductoInterface selectProducto(int id);
    public ArrayList<ProductoInterface> selectAllProductos();

}