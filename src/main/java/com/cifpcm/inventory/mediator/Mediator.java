package com.cifpcm.inventory.mediator;

import com.cifpcm.inventory.models.aula.Aula;
import com.cifpcm.inventory.models.marcaje.Marcaje;
import com.cifpcm.inventory.models.marcaje.TipoMarcaje;
import com.cifpcm.inventory.models.producto.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.cifpcm.inventory.models.aula.AulaInterface;
import com.cifpcm.inventory.models.marcaje.MarcajeInterface;
import com.cifpcm.inventory.models.producto.ProductoInterface;

public class Mediator implements MediatorInterface {
    private final List<AulaInterface> aulas = new ArrayList<>();
    private final List<ProductoInterface> productos = new ArrayList<>();
    private final List<MarcajeInterface> marcajes = new ArrayList<>();

    @Override
    public void registerAula(AulaInterface aula) {
        aulas.add(aula);
    }

    @Override
    public void registerProducto(ProductoInterface producto) {
        productos.add(producto);
    }

    @Override
    public void registerMarcaje(MarcajeInterface marcaje) {
        marcajes.add(marcaje);
    }

    @Override
    public void recordMarcaje(int idProducto, int idAula, String tipo) {
        MarcajeInterface marcaje = new Marcaje(idProducto, idAula, new Date(), TipoMarcaje.valueOf(tipo));
        marcajes.add(marcaje);
    }


    @Override
    public List<MarcajeInterface> getMarcajesByProducto(int idProducto, Date startDate, Date endDate) {
        List<MarcajeInterface> result = new ArrayList<>();
        for (MarcajeInterface marcaje : marcajes) {
            if (marcaje.getIdProducto() == idProducto &&
                    marcaje.getTimeStamp().after(startDate) &&
                    marcaje.getTimeStamp().before(endDate)) {
                result.add(marcaje);
            }
        }
        return result;
    }

    @Override
    public List<MarcajeInterface> getMarcajesByAula(int idAula, Date startDate, Date endDate) {
        List<MarcajeInterface> result = new ArrayList<>();
        for (MarcajeInterface marcaje : marcajes) {
            if (marcaje.getIdAula() == idAula &&
                    marcaje.getTimeStamp().after(startDate) &&
                    marcaje.getTimeStamp().before(endDate)) {
                result.add(marcaje);
            }
        }
        return result;
    }

    // Guardar datos a ficheros sin IDs
    public void saveDataToFiles(String aulaFile, String productoFile, String marcajeFile) throws IOException {
        try (ObjectOutputStream aulaOut = new ObjectOutputStream(new FileOutputStream(aulaFile));
             ObjectOutputStream productoOut = new ObjectOutputStream(new FileOutputStream(productoFile));
             ObjectOutputStream marcajeOut = new ObjectOutputStream(new FileOutputStream(marcajeFile))) {

            for (AulaInterface aula : aulas) {
                aulaOut.writeObject(new Aula(aula.getNumeracion(), aula.getDescripcion(), aula.getIp()));
            }

            for (ProductoInterface producto : productos) {
                productoOut.writeObject(new Producto(producto.getDescripcion(), producto.getEan(), producto.getKeyRFID()));
            }

            for (MarcajeInterface marcaje : marcajes) {
                marcajeOut.writeObject(new Marcaje(marcaje.getIdProducto(), marcaje.getIdAula(), marcaje.getTimeStamp(), marcaje.getTipo()));
            }
        }
    }

    // Cargar datos de ficheros
    public void loadDataFromFiles(String aulaFile, String productoFile, String marcajeFile) throws IOException, ClassNotFoundException {
        try (ObjectInputStream aulaIn = new ObjectInputStream(new FileInputStream(aulaFile));
             ObjectInputStream productoIn = new ObjectInputStream(new FileInputStream(productoFile));
             ObjectInputStream marcajeIn = new ObjectInputStream(new FileInputStream(marcajeFile))) {

            aulas.clear();
            productos.clear();
            marcajes.clear();

            Aula aula;
            while ((aula = (Aula) aulaIn.readObject()) != null) {
                registerAula(aula);
            }

            Producto producto;
            while ((producto = (Producto) productoIn.readObject()) != null) {
                registerProducto(producto);
            }

            Marcaje marcaje;
            while ((marcaje = (Marcaje) marcajeIn.readObject()) != null) {
                registerMarcaje(marcaje);
            }
        } catch (EOFException e) {
            
        }
    }
}

