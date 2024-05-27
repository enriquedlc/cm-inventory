
package com.cifpcm.inventory.models.producto;

import com.cifpcm.inventory.utils.VerificarEntrada;
import java.util.ArrayList;

/**
 *
 * @author tecen
 */
public class ProductoManager implements ProductoManagerInterface {

    private ArrayList<Producto> productos;
    
    public ProductoManager(){}
    public ProductoManager(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public boolean insertProducto(Producto producto) {
        return productos.add(producto);
    }

    @Override
    public boolean updateProducto(Producto producto) {
        for (Producto p : productos) {
            if (p.getIdProducto() == producto.getIdProducto()) {
                p.setDescripcion(producto.getDescripcion());
                p.setEAN13(producto.getEan());
                p.setKeyRFID(producto.getKeyRFID());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteProducto(int id) {
        for (Producto p : productos) {
            if (p.getIdProducto() == id) {
                return productos.remove(p);
            }
        }
        return false;
    }

    @Override
    public Producto selectProducto(int id) {
        for (Producto p : productos) {
            if (p.getIdProducto() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Producto> selectAllProductos() {
        return new ArrayList<>(productos);
    }

    @Override
    public boolean exists(int idProducto, ArrayList<Producto> productos) {
        for (Producto producto : productos) {
            if (producto.getIdProducto() == idProducto) {
                return true;
            }
        }
        return false;
    }
}
