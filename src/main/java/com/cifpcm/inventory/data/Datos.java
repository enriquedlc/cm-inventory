package com.cifpcm.inventory.data;

import com.cifpcm.inventory.models.aula.Aula;
import com.cifpcm.inventory.models.aula.AulaInterface;
import com.cifpcm.inventory.models.marcaje.Marcaje;
import com.cifpcm.inventory.models.marcaje.MarcajeInterface;
import com.cifpcm.inventory.models.marcaje.TipoMarcaje;
import com.cifpcm.inventory.models.producto.Producto;
import com.cifpcm.inventory.models.producto.ProductoInterface;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tecen
 */
public class Datos {
    
    private final List<AulaInterface> aulas = new ArrayList<>();
    private final List<ProductoInterface> productos = new ArrayList<>();
    private final List<MarcajeInterface> marcajes = new ArrayList<>();

    public Datos() {
    }
    
    
    public void registerAula(AulaInterface aula) {
        aulas.add(aula);
    }

    public void registerProducto(ProductoInterface producto) {
        productos.add(producto);
    }

    public void registerMarcaje(MarcajeInterface marcaje) {
        marcajes.add(marcaje);
    }

    public void recordMarcaje(int idProducto, int idAula, String tipo) {
        MarcajeInterface marcaje = new Marcaje(idProducto, idAula, new Date(), TipoMarcaje.valueOf(tipo));
        marcajes.add(marcaje);
    }
    
    private void cargarAulasDesdeCSV(String aulaFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(aulaFile))) {
            aulas.clear();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String numeracion = fields[0];
                String descripcion = fields[1];
                String ip = fields[2];
                Aula aula = new Aula(numeracion, descripcion, ip);
                registerAula(aula);
            }
        }
    }
    
    public void cargarProductosDesdeCSV(String productoFile) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(productoFile))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields.length == 3) {
                String descripcion = fields[0];
                String ean = fields[1]; 
                String keyRFID = fields[2];
                Producto producto = new Producto(descripcion, ean, keyRFID);
                productos.add(producto);
            } else {
                System.out.println("Línea inválida en el archivo: " + line);
            }
        }
    } catch (IOException e) {
        throw new IOException("Error al cargar los datos de productos: " + e.getMessage());
    }
}
    private void cargarMarcajesDesdeCSV(String marcajeFile) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(marcajeFile))) {
        marcajes.clear();
        String line;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields.length != 4) {
                System.out.println("Error: Línea inválida en el archivo: " + line);
                continue; // Pasar a la siguiente línea
            }
            try {
                int idProducto = Integer.parseInt(fields[0].trim());
                int idAula = Integer.parseInt(fields[1].trim());
                Date timeStamp = parseDate(fields[2].trim(), dateFormat);
                if (timeStamp == null) {
                    System.out.println("Error: Fecha no válida en línea: " + line);
                    continue;
                }
                TipoMarcaje tipo = convertToTipoMarcaje(fields[3].trim());
                if (tipo != null) {
                    Marcaje marcaje = new Marcaje(idProducto, idAula, new java.sql.Date(timeStamp.getTime()), tipo);
                    registerMarcaje(marcaje);
                } else {
                    System.out.println("Error: TipoMarcaje no válido en línea: " + line);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Valor no numérico en línea: " + line);
            }
        }
    }
}
    
    private Date parseDate(String dateStr, SimpleDateFormat dateFormat) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
    
    private TipoMarcaje convertToTipoMarcaje(String tipo) {
        try {
            return TipoMarcaje.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    // Cargar datos de ficheros
    void cargarDatosTrabajoDesconectado(String aulaFile, String productoFile, String marcajeFile) throws IOException {
        cargarAulasDesdeCSV(aulaFile);
        cargarProductosDesdeCSV(productoFile);
        cargarMarcajesDesdeCSV(marcajeFile);
        System.out.println("Cargando datos de trabajo desconectado...");
    }

    // Guardar datos a ficheros sin IDs
    void guardarDatosTrabajoDesconectado(String aulaFile, String productoFile, String marcajeFile) throws IOException {
        try (ObjectOutputStream aulaOut = new ObjectOutputStream(new FileOutputStream(aulaFile)); ObjectOutputStream productoOut = new ObjectOutputStream(new FileOutputStream(productoFile)); ObjectOutputStream marcajeOut = new ObjectOutputStream(new FileOutputStream(marcajeFile))) {

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

        System.out.println("Guardando datos a trabajo desconectado...");
    }
    
}
