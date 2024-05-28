package com.cifpcm.inventory.models.producto;

import com.cifpcm.inventory.mediator.Mediator;

import java.util.ArrayList;

public class ProductoFileSystem implements ProductoInterface {
    private String descripcion;
    private String EAN13;
    private String keyRFID;
    private int idProducto;
    private Mediator mediator;

    public ProductoFileSystem(Mediator mediator ) {
        this.mediator = mediator;
    }

    public ProductoFileSystem(int idProducto, String descripcion, String EAN13, String keyRFID) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.EAN13 = EAN13;
        this.keyRFID = keyRFID;
    }

    public ProductoFileSystem(String descripcion, String EAN13, String keyRFID) {
        this.descripcion = descripcion;
        this.EAN13 = EAN13;
        this.keyRFID = keyRFID;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEan() {
        return EAN13;
    }

    public String getKeyRFID() {
        return keyRFID;
    }

    @Override
    public boolean insertProducto(ProductoInterface producto) {
        return mediator.addProducto(producto);
    }

    @Override
    public boolean updateProducto(ProductoInterface producto) {
        ArrayList<ProductoInterface> productos = (ArrayList<ProductoInterface>) mediator.getProductos();
        for (ProductoInterface producto1 : productos) {
            if (producto1.getIdProducto() == producto.getIdProducto()) {
                productos.remove(producto1);
                productos.add(producto);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteProducto(int id) {
        ArrayList<ProductoInterface> productos = (ArrayList<ProductoInterface>) mediator.getProductos();
        for (ProductoInterface producto : productos) {
            if (producto.getIdProducto() == id) {
                productos.remove(producto);
                return true;
            }
        }
        return false;
    }

    @Override
    public ProductoInterface selectProducto(int id) {
        ArrayList<ProductoInterface> productos = (ArrayList<ProductoInterface>) mediator.getProductos();
        for (ProductoInterface producto : productos) {
            if (producto.getIdProducto() == id) {
                return (ProductoInterface) producto;
            }
        }
        return null;
    }

    @Override
    public ArrayList<ProductoInterface> selectAllProductos() {
        return (ArrayList<ProductoInterface>) mediator.getProductos();
    }

    @Override
    public String toString() {
        return "Producto{" + "descripcion=" + descripcion + ", EAN13=" + EAN13 + ", keyRFID=" + keyRFID + ", idProducto=" + idProducto + '}';
    }
}
