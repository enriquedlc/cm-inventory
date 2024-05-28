
package com.cifpcm.inventory.models.producto;

import com.cifpcm.inventory.mediator.Mediator;
import com.cifpcm.inventory.utils.VerificarEntrada;
import java.util.ArrayList;

/**
 *
 * @author tecen
 */
public class ProductoManager implements ProductoManagerInterface {

    Mediator mediator;
    public ProductoManager(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public boolean insertProducto(Producto producto) {
        return mediator.getProductos().add(producto);
    }

    @Override
    public boolean updateProducto(Producto producto) {
        for (Producto p : mediator.getProductos()) {
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
        return mediator.getProductos().removeIf(p -> p.getIdProducto() == id);
    }

    @Override
    public Producto selectProducto(int id) {
        for (Producto producto : mediator.getProductos()) {
            if (producto.getIdProducto() == id) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Producto> selectAllProductos() {
        return mediator.getProductos();
    }

    @Override
    public boolean exists(int idProducto) {
        return mediator.getProductos().stream().anyMatch(p -> p.getIdProducto() == idProducto);
    }
}
