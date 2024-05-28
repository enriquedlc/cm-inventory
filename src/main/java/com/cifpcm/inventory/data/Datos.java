package com.cifpcm.inventory.data;

import com.cifpcm.inventory.models.aula.Aula;
import com.cifpcm.inventory.models.aula.AulaInterface;
import com.cifpcm.inventory.models.marcaje.Marcaje;
import com.cifpcm.inventory.models.marcaje.MarcajeInterface;
import com.cifpcm.inventory.models.marcaje.TipoMarcaje;
import com.cifpcm.inventory.models.producto.Producto;
import com.cifpcm.inventory.models.producto.ProductoInterface;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
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
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String numeracion = fields[0];
                String descripcion = fields[1];
                String ip = fields[2];
                Aula aula = new Aula(numeracion, descripcion, ip);
                registerAula(aula);
            }

            // Mostrar las aulas cargadas
            System.out.println("Aulas cargadas:");
            for (AulaInterface aula : aulas) {
                System.out.println(aula);
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

            // Mostrar los productos cargados
            System.out.println("Productos cargados:");
            for (ProductoInterface producto : productos) {
                System.out.println(producto);
            }
        } catch (IOException e) {
            throw new IOException("Error al cargar los datos de productos: " + e.getMessage());
        }
    }


    private void cargarMarcajesDesdeCSV(String marcajeFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(marcajeFile))) {
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 4) {
                    System.out.println("Error: Línea inválida en el archivo: " + line);
                    continue; // Pasar a la siguiente línea
                }
                try {
                    int idProducto = Integer.parseInt(fields[0].trim()); // test
                    int idAula = Integer.parseInt(fields[1].trim());
                    Date timeStamp = dateFormat.parse(fields[2].trim()); // Parsear la fecha sin la hora
                    TipoMarcaje tipo = convertToTipoMarcaje(fields[3].trim());
                    if (tipo != null) {
                        Marcaje marcaje = new Marcaje(idProducto, idAula, timeStamp, tipo);
                        registerMarcaje(marcaje);
                    } else {
                        System.out.println("Error: TipoMarcaje no válido en línea: " + line);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Valor no numérico en línea: " + line);
                } catch (ParseException e) {
                    System.out.println("Error: Fecha no válida en línea: " + line);
                }
            }

            // Mostrar los marcajes cargados
            System.out.println("Marcajes cargados:");
            for (MarcajeInterface marcaje : marcajes) {
                System.out.println(marcaje);
            }
        }
    }


    private TipoMarcaje convertToTipoMarcaje(String tipoString) {
        if (tipoString.equals("0") || tipoString.equalsIgnoreCase("ENTRADA")) {
            return TipoMarcaje.ENTRADA;
        } else if (tipoString.equals("1") || tipoString.equalsIgnoreCase("SALIDA")) {
            return TipoMarcaje.SALIDA;
        } else {
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
        try (BufferedWriter aulaWriter = new BufferedWriter(new FileWriter(aulaFile));
             BufferedWriter productoWriter = new BufferedWriter(new FileWriter(productoFile));
             BufferedWriter marcajeWriter = new BufferedWriter(new FileWriter(marcajeFile))) {

            for (AulaInterface aula : aulas) {
                aulaWriter.write(aula.getNumeracion() + "," + aula.getDescripcion() + "," + aula.getIp());
                aulaWriter.newLine();
            }

            for (ProductoInterface producto : productos) {
                productoWriter.write(producto.getDescripcion() + "," + producto.getEan() + "," + producto.getKeyRFID());
                productoWriter.newLine();
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (MarcajeInterface marcaje : marcajes) {
                String tipoString = (marcaje.getTipo() == TipoMarcaje.ENTRADA) ? "0" : "1";
                marcajeWriter.write(marcaje.getIdProducto() + "," + marcaje.getIdAula() + "," + dateFormat.format(marcaje.getTimeStamp()) + "," + tipoString);
                marcajeWriter.newLine();
            }
        }

        System.out.println("Guardando datos a trabajo desconectado...");
    }
}
