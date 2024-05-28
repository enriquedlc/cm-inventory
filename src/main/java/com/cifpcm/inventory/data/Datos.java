package com.cifpcm.inventory.data;

import com.cifpcm.inventory.mediator.Mediator;
import com.cifpcm.inventory.models.aula.Aula;
import com.cifpcm.inventory.models.aula.AulaInterface;
import com.cifpcm.inventory.models.aula.AulaManager;
import com.cifpcm.inventory.models.marcaje.Marcaje;
import com.cifpcm.inventory.models.marcaje.MarcajeInterface;
import com.cifpcm.inventory.models.marcaje.MarcajeManager;
import com.cifpcm.inventory.models.marcaje.MarcajeManagerInterface;
import com.cifpcm.inventory.models.marcaje.TipoMarcaje;
import com.cifpcm.inventory.models.producto.Producto;
import com.cifpcm.inventory.models.producto.ProductoInterface;
import com.cifpcm.inventory.models.producto.ProductoManager;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author tecen
 */
public class Datos {

    private final ArrayList<AulaInterface> aulas = new ArrayList<>();
    private final ArrayList<ProductoInterface> productos = new ArrayList<>();
    private final ArrayList<MarcajeInterface> marcajes = new ArrayList<>();

    Mediator mediator;
    MarcajeManagerInterface marcajeManager;

    public Datos(Mediator mediator) {
        this.marcajeManager = new MarcajeManager(mediator);
        this.mediator = mediator;
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

    public void mostrarAulas() {
        System.out.println("Aulas:");
        for (AulaInterface aula : aulas) {
            System.out.println(aula);
        }
    }

    public void mostrarProductos() {
        System.out.println("Productos:");
        for (ProductoInterface producto : productos) {
            System.out.println(producto);
        }
    }

    public void mostrarMarcajes() {
        System.out.println("Marcajes:");
        for (MarcajeInterface marcaje : marcajes) {
            System.out.println(marcaje);
        }
    }

    public ArrayList<ProductoInterface> getAllProductos() {
        return productos;
    }

    public ArrayList<MarcajeInterface> getAllMarcajes() {
        return marcajes;
    }

    private void cargarAulasDesdeCSV(String aulaFile, AulaManager aulaManager) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(aulaFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String numeracion = fields[0];
                String descripcion = fields[1];
                String ip = fields[2];
                Aula aula = new Aula(numeracion, descripcion, ip);
                aulaManager.insertAula(aula); // Agregar el aula al ArrayList de aulas a través de aulaManager
            }

            // Mostrar las aulas cargadas
            System.out.println("Aulas cargadas:");
            for (AulaInterface aula : aulaManager.selectAllAulas()) {
                System.out.println(aula);
                registerAula(aula);
            }
        }
    }

    private void cargarProductosDesdeCSV(String productoFile, Datos datos) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(productoFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 3) {
                    String descripcion = fields[0];
                    String ean = fields[1];
                    String keyRFID = fields[2];
                    Producto producto = new Producto(descripcion, ean, keyRFID);
                    // Agregar el producto a través de un método en Datos
                    datos.registerProducto(producto);
                } else {
                    System.out.println("Línea inválida en el archivo: " + line);
                }
            }

            // Mostrar los productos cargados
            System.out.println("Productos cargados:");
            for (ProductoInterface producto : datos.getAllProductos()) {
                System.out.println(producto);
            }
        } catch (IOException e) {
            throw new IOException("Error al cargar los datos de productos: " + e.getMessage());
        }
    }

    private void cargarMarcajesDesdeCSV(String marcajeFile, Datos datos) throws IOException, ParseException {
        try (BufferedReader br = new BufferedReader(new FileReader(marcajeFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 4) {
                    try {
                        int idProducto = Integer.parseInt(fields[0].trim());
                        int idAula = Integer.parseInt(fields[1].trim());
                        Date timeStamp = parseDate(fields[2].trim());
                        TipoMarcaje tipo = convertToTipoMarcaje(fields[3].trim());
                        if (tipo != null) {
                            Marcaje marcaje = new Marcaje(idProducto, idAula, timeStamp, tipo);
                            // Agregar el marcaje a la lista de marcajes en Datos
                            datos.registerMarcaje(marcaje);
                        } else {
                            System.out.println("Error: TipoMarcaje no válido en línea: " + line);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Valor no numérico en línea: " + line);
                    }
                } else {
                    System.out.println("Línea inválida en el archivo: " + line);
                }
            }
            System.out.println("Marcajes cargados:");
            for (MarcajeInterface marcaje : marcajes) {
                System.out.println(marcaje);
            }
        } catch (IOException e) {
            throw new IOException("Error al cargar los datos de marcajes: " + e.getMessage());
        }
    }

    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(dateString);
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

    // Método para guardar las aulas en el archivo
    private void guardarAulasEnArchivo(String aulaFile) throws IOException {
        try (BufferedWriter aulaWriter = new BufferedWriter(new FileWriter(aulaFile))) {
            for (AulaInterface aula : aulas) {
                aulaWriter.write(aula.getNumeracion() + "," + aula.getDescripcion() + "," + aula.getIp());
                aulaWriter.newLine();
            }
        }
        System.out.println("Aulas guardadas en el archivo: " + aulaFile);
    }

    // Método para guardar los productos en el archivo
    private void guardarProductosEnArchivo(String productoFile) throws IOException {
        try (BufferedWriter productoWriter = new BufferedWriter(new FileWriter(productoFile))) {
            for (ProductoInterface producto : productos) {
                productoWriter.write(producto.getDescripcion() + "," + producto.getEan() + "," + producto.getKeyRFID());
                productoWriter.newLine();
            }
        }
        System.out.println("Productos guardados en el archivo: " + productoFile);
    }

    // Método para guardar los marcajes en el archivo
    private void guardarMarcajesEnArchivo(String marcajeFile) throws IOException {
        try (BufferedWriter marcajeWriter = new BufferedWriter(new FileWriter(marcajeFile))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (MarcajeInterface marcaje : marcajes) {
                String tipoString = (marcaje.getTipo() == TipoMarcaje.ENTRADA) ? "0" : "1";
                marcajeWriter.write(marcaje.getIdProducto() + "," + marcaje.getIdAula() + "," + dateFormat.format(marcaje.getTimeStamp()) + "," + tipoString);
                marcajeWriter.newLine();
            }
        }
        System.out.println("Marcajes guardados en el archivo: " + marcajeFile);
    }
    // Método para modificar un aula en el ArrayList y actualizar el archivo correspondiente

    public void modificarAula(AulaInterface aula, String aulaFile) throws IOException {
        for (int i = 0; i < aulas.size(); i++) {
            if (aulas.get(i).getNumeracion().equals(aula.getNumeracion())) {
                aulas.set(i, aula);
                guardarAulasEnArchivo(aulaFile);
                System.out.println("Aula modificada correctamente.");
                return;
            }
        }
        System.out.println("No se encontró el aula a modificar.");
    }

    // Método para modificar un producto en el ArrayList y actualizar el archivo correspondiente
    public void modificarProducto(ProductoInterface producto, String productoFile) throws IOException {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getEan().equals(producto.getEan())) {
                productos.set(i, producto);
                guardarProductosEnArchivo(productoFile);
                System.out.println("Producto modificado correctamente.");
                return;
            }
        }
        System.out.println("No se encontró el producto a modificar.");
    }

    // Método para modificar un marcaje en el ArrayList y actualizar el archivo correspondiente
    public void modificarMarcaje(MarcajeInterface marcaje, String marcajeFile) throws IOException {
        for (int i = 0; i < marcajes.size(); i++) {
            if (marcajes.get(i).getIdProducto() == marcaje.getIdProducto() && marcajes.get(i).getIdAula() == marcaje.getIdAula()) {
                marcajes.set(i, marcaje);
                guardarMarcajesEnArchivo(marcajeFile); // Suponiendo que MARCAJE_FILE es un atributo de la clase que contiene la ruta del archivo de marcajes
                System.out.println("Marcaje modificado correctamente.");
                return;
            }
        }
        System.out.println("No se encontró el marcaje a modificar.");
    }

    // Cargar datos de ficheros
    void cargarDatosDeArchivos(String aulaFile, String productoFile, String marcajeFile, AulaManager aulaManager, Datos datos) throws IOException, ParseException {
        cargarAulasDesdeCSV(aulaFile, aulaManager);
        cargarProductosDesdeCSV(productoFile, datos);
        cargarMarcajesDesdeCSV(marcajeFile, datos);
        System.out.println("Cargando datos de trabajo desconectado...");
    }

    // Guardar datos a ficheros sin IDs
    public void guardarDatosEnArchivos(String aulaFile, String productoFile, String marcajeFile) throws IOException {
        guardarAulasEnArchivo(aulaFile);
        guardarProductosEnArchivo(productoFile);
        guardarMarcajesEnArchivo(marcajeFile);
        System.out.println("Guardando datos a trabajo desconectado...");

    }
}
