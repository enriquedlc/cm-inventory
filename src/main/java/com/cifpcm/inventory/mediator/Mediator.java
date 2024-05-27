package com.cifpcm.inventory.mediator;

import com.cifpcm.inventory.data.Datos;
import com.cifpcm.inventory.models.aula.AulaInterface;
import com.cifpcm.inventory.models.marcaje.MarcajeInterface;
import com.cifpcm.inventory.models.producto.ProductoInterface;
import com.cifpcm.inventory.models.aula.AulaManager;
import com.cifpcm.inventory.models.marcaje.MarcajeManager;
import com.cifpcm.inventory.models.aula.GestorAula;
import com.cifpcm.inventory.models.marcaje.GestorMarcaje;
import com.cifpcm.inventory.models.producto.GestorProducto;
import com.cifpcm.inventory.reports.GestorReportes;
import com.cifpcm.inventory.data.GestorDatos;
import com.cifpcm.inventory.models.aula.Aula;
import com.cifpcm.inventory.models.marcaje.TipoMarcaje;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mediator implements MediatorInterface {
    
    private ArrayList<MarcajeInterface> marcajes = new ArrayList<>();

    @Override
    public ArrayList<MarcajeInterface> getMarcajesByProducto(int idProducto, Date fechaInicio, Date fechaFin) {
        MarcajeManager marcajeManager = new MarcajeManager(); // Crear una instancia de MarcajeManager
        ArrayList<MarcajeInterface> marcajesByProducto = marcajeManager.getAllMarcajes(); // Obtener todos los marcajes
        marcajesByProducto = marcajeManager.getMarcajesByProducto(idProducto, marcajesByProducto);

        // Aplicar el filtro por fecha si es necesario
        if (fechaInicio != null && fechaFin != null) {
            marcajesByProducto = filtrarPorFecha(marcajesByProducto, fechaInicio, fechaFin);
        }
        return marcajesByProducto;
    }

    private ArrayList<MarcajeInterface> filtrarPorFecha(ArrayList<MarcajeInterface> marcajes, Date fechaInicio, Date fechaFin) {
        ArrayList<MarcajeInterface> marcajesFiltrados = new ArrayList<>();
        for (MarcajeInterface marcaje : marcajes) {
            if (marcaje.getTimeStamp().after(fechaInicio) && marcaje.getTimeStamp().before(fechaFin)) {
                marcajesFiltrados.add(marcaje);
            }
        }
        return marcajesFiltrados;
    }

    @Override
    public Map<Integer, String> obtenerNumeracionesAulas() {
        Map<Integer, String> aulaNumeraciones = new HashMap<>();
        AulaManager aulaManager = new AulaManager(); // Crear una instancia de AulaManager
        ArrayList<Aula> aulas = aulaManager.selectAllAulas(); // Obtener todas las aulas
        for (AulaInterface aula : aulas) {
            aulaNumeraciones.put(aula.getIdAula(), aula.getNumeracion());
        }
        return aulaNumeraciones;
    }

    public void inicio() {
        Scanner scanner = new Scanner(System.in);
        Datos datos = new Datos();
        GestorAula gestorAula = new GestorAula();
        GestorProducto gestorProducto = new GestorProducto();
        GestorMarcaje gestorMarcaje = new GestorMarcaje();

        while (true) {
            System.out.println("Menu");
            System.out.println("1 - Gestión Aulas.");
            System.out.println("2 - Gestión Productos.");
            System.out.println("3 - Gestión Marcajes.");
            System.out.println("4 - Informes.");
            System.out.println("5 - Datos.");
            System.out.println("0 - Salir");
            System.out.print("Introduzca opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 ->
                    gestorAula.showMenuAulas();
                case 2 ->
                    gestorProducto.showMenuProductos();
                case 3 ->
                    gestorMarcaje.showMenuMarcajes();
                case 4 ->
                    GestorReportes.generateReports(this, scanner);
                case 5 -> {
                try {
                    GestorDatos.manageData(this, scanner, datos);
                } catch (IOException ex) {
                    Logger.getLogger(Mediator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Mediator.class.getName()).log(Level.SEVERE, null, ex);
                }
                }

                case 0 ->
                    System.exit(0);
                default ->
                    System.out.println("Opción no válida.");
            }
        }
    }

    @Override
public ArrayList<MarcajeInterface> getMarcajesByAula(int idAula, Date fechaInicio, Date fechaFin) {
     MarcajeManager marcajeManager = new MarcajeManager();
    ArrayList<MarcajeInterface> marcajesByAula = new ArrayList<>();
    ArrayList<MarcajeInterface> marcajes = marcajeManager.getAllMarcajes(); // Obtener todos los marcajes
    
    // Filtrar los marcajes por el ID de aula
    for (MarcajeInterface marcaje : marcajes) {
        if (marcaje.getIdAula() == idAula) {
            marcajesByAula.add(marcaje);
        }
    }

    // Aplicar el filtro por fecha si es necesario
    if (fechaInicio != null && fechaFin != null) {
        marcajesByAula = filtrarPorFecha(marcajesByAula, fechaInicio, fechaFin);
    }

    return marcajesByAula;
}

@Override
public ArrayList<MarcajeInterface> getMarcajesByProductoYAula(int idProducto, int idAula) {
    MarcajeManager marcajeManager = new MarcajeManager();
    ArrayList<MarcajeInterface> marcajesByProductoYAula = new ArrayList<>();
    ArrayList<MarcajeInterface> marcajes = marcajeManager.getAllMarcajes(); // Obtener todos los marcajes
    
    // Filtrar los marcajes por el ID de producto y aula
    for (MarcajeInterface marcaje : marcajes) {
        if (marcaje.getIdProducto() == idProducto && marcaje.getIdAula() == idAula) {
            marcajesByProductoYAula.add(marcaje);
        }
    }

    return marcajesByProductoYAula;
}

    @Override
    public ArrayList<MarcajeInterface> obtenerMarcajesConErrores() {
        return detectarErrores(marcajes, obtenerNumeracionesAulas());
    }

    @Override
    public ArrayList<MarcajeInterface> obtenerMarcajesConErrores(Date fechaInicio, Date fechaFin) {
        return detectarErrores(getMarcajesEnIntervalo(fechaInicio, fechaFin), obtenerNumeracionesAulas());
    }

    private ArrayList<MarcajeInterface> getMarcajesEnIntervalo(Date fechaInicio, Date fechaFin) {
        ArrayList<MarcajeInterface> marcajesEnIntervalo = new ArrayList<>();
        for (MarcajeInterface marcaje : marcajes) {
            if (marcaje.getTimeStamp().after(fechaInicio) && marcaje.getTimeStamp().before(fechaFin)) {
                marcajesEnIntervalo.add(marcaje);
            }
        }
        return marcajesEnIntervalo;
    }
    public ArrayList<MarcajeInterface> detectarErrores(ArrayList<MarcajeInterface> marcajes, Map<Integer, String> aulaNumeraciones) {
        ArrayList<MarcajeInterface> errores = new ArrayList<>();

        // Mapa para almacenar el número de fichajes de entrada por producto
        Map<Integer, Integer> entradaPorProducto = new HashMap<>();
        
        // Mapa para almacenar los productos por aula
        Map<Integer, List<Integer>> productosPorAula = new HashMap<>();

        // Verificar las condiciones
        for (MarcajeInterface marcaje : marcajes) {
            // Condición 1: Contar el número de fichajes de entrada por producto
            if (marcaje.getTipo() == TipoMarcaje.ENTRADA) {
                int productoId = marcaje.getIdProducto();
                int cantidadEntradas = entradaPorProducto.getOrDefault(productoId, 0);
                entradaPorProducto.put(productoId, cantidadEntradas + 1);
            }

            // Condición 2: Verificar si un producto tiene el mismo ID en varias aulas
            int aulaId = marcaje.getIdAula();
            List<Integer> productos = productosPorAula.getOrDefault(aulaId, new ArrayList<>());
            if (productos.contains(marcaje.getIdProducto())) {
                // El producto ya existe en esta aula, agregar al error
                errores.add(marcaje);
            } else {
                productos.add(marcaje.getIdProducto());
                productosPorAula.put(aulaId, productos);
            }

            // Condición 3: Verificar si un producto no se encuentra en ninguna aula
            if (!aulaNumeraciones.containsKey(aulaId)) {
                // El aula no existe en el mapa de numeraciones, agregar al error
                errores.add(marcaje);
            }
        }

        // Condición 1: Agregar a la lista de errores los productos con más de dos entradas sin salida
        for (Map.Entry<Integer, Integer> entry : entradaPorProducto.entrySet()) {
            if (entry.getValue() > 2) {
                for (MarcajeInterface marcaje : marcajes) {
                    if (marcaje.getIdProducto() == entry.getKey() && marcaje.getTipo() == TipoMarcaje.ENTRADA) {
                        errores.add(marcaje);
                    }
                }
            }
        }

        return errores;
    }
}
