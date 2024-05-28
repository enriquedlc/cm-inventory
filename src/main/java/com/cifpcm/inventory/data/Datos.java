package com.cifpcm.inventory.data;

import com.cifpcm.inventory.mediator.Mediator;
import com.cifpcm.inventory.models.aula.AulaDatabase;
import com.cifpcm.inventory.models.aula.AulaInterface;
import com.cifpcm.inventory.models.marcaje.MarcajeDatabase;
import com.cifpcm.inventory.models.marcaje.MarcajeInterface;
import com.cifpcm.inventory.models.marcaje.enums.TipoMarcaje;
import com.cifpcm.inventory.models.producto.ProductoDatabase;
import com.cifpcm.inventory.models.producto.ProductoInterface;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Datos {
    private final Mediator mediator;

    public Datos(Mediator mediator) {
        this.mediator = mediator;
    }

    private void cargarAulasDesdeCSV(String aulaFile) throws IOException {
        mediator.getAulas().clear();
        try (BufferedReader br = new BufferedReader(new FileReader(aulaFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String numeracion = fields[0];
                String descripcion = fields[1];
                String ip = fields[2];
                AulaInterface aula = new AulaDatabase(mediator, numeracion, descripcion, ip);
                mediator.addAula(aula);
            }
        }
    }

    public void cargarProductosDesdeCSV(String productoFile) throws IOException {
        mediator.getProductos().clear();
        try (BufferedReader br = new BufferedReader(new FileReader(productoFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 3) {
                    String descripcion = fields[0];
                    String ean = fields[1];
                    String keyRFID = fields[2];
                    ProductoDatabase producto = new ProductoDatabase(descripcion, ean, keyRFID);
                    mediator.addProducto(producto);
                } else {
                    System.out.println("Línea inválida en el archivo: " + line);
                }
            }
        }
    }

    private void cargarMarcajesDesdeCSV(String marcajeFile) throws IOException {
        mediator.getMarcajes().clear();
        try (BufferedReader br = new BufferedReader(new FileReader(marcajeFile))) {
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 4) {
                    System.out.println("Error: Línea inválida en el archivo: " + line);
                    continue;
                }
                try {
                    int idProducto = Integer.parseInt(fields[0].trim());
                    int idAula = Integer.parseInt(fields[1].trim());
                    Date timeStamp = dateFormat.parse(fields[2].trim());
                    TipoMarcaje tipo = convertToTipoMarcaje(fields[3].trim());
                    if (tipo != null) {
                        MarcajeDatabase marcaje = new MarcajeDatabase(idProducto, idAula, timeStamp, tipo);
                        mediator.addMarcaje(marcaje);
                    } else {
                        System.out.println("Error: TipoMarcaje no válido en línea: " + line);
                    }
                } catch (NumberFormatException | ParseException e) {
                    System.out.println("Error: Línea inválida en el archivo: " + line);
                }
            }
        }
    }

    private TipoMarcaje convertToTipoMarcaje(String tipoString) {
        return switch (tipoString) {
            case "0", "ENTRADA" -> TipoMarcaje.ENTRADA;
            case "1", "SALIDA" -> TipoMarcaje.SALIDA;
            default -> null;
        };
    }

    public void cargarDatosTrabajoDesconectado(String aulaFile, String productoFile, String marcajeFile) throws IOException {
        cargarAulasDesdeCSV(aulaFile);
        cargarProductosDesdeCSV(productoFile);
        cargarMarcajesDesdeCSV(marcajeFile);
    }

    public void guardarDatosTrabajoDesconectado(String aulaFile, String productoFile, String marcajeFile) throws IOException {
        try (BufferedWriter aulaWriter = new BufferedWriter(new FileWriter(aulaFile));
             BufferedWriter productoWriter = new BufferedWriter(new FileWriter(productoFile));
             BufferedWriter marcajeWriter = new BufferedWriter(new FileWriter(marcajeFile))) {

            for (AulaInterface aula : mediator.getAulas()) {
                aulaWriter.write(aula.getNumeracion() + "," + aula.getDescripcion() + "," + aula.getIp());
                aulaWriter.newLine();
            }

            for (ProductoInterface producto : mediator.getProductos()) {
                productoWriter.write(producto.getDescripcion() + "," + producto.getEan() + "," + producto.getKeyRFID());
                productoWriter.newLine();
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (MarcajeInterface marcaje : mediator.getMarcajes()) {
                String tipoString = (marcaje.getTipo() == TipoMarcaje.ENTRADA) ? "0" : "1";
                marcajeWriter.write(marcaje.getIdProducto() + "," + marcaje.getIdAula() + "," + dateFormat.format(marcaje.getTimeStamp()) + "," + tipoString);
                marcajeWriter.newLine();
            }
        }
    }
}
