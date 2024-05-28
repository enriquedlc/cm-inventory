package com.cifpcm.inventory.mediator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.cifpcm.inventory.models.aula.AulaInterface;

import com.cifpcm.inventory.models.aula.GestorAulaFileSystem;
import com.cifpcm.inventory.models.marcaje.GestorMarcaje;
import com.cifpcm.inventory.models.marcaje.MarcajeInterface;
import com.cifpcm.inventory.models.producto.GestorProductoFileSystem;
import com.cifpcm.inventory.models.producto.ProductoInterface;

import com.cifpcm.inventory.data.GestorDatos;
import com.cifpcm.inventory.reports.GestorReportes;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mediator implements MediatorInterface {
    private final List<AulaInterface> aulas = new ArrayList<>();
    private final List<ProductoInterface> productos = new ArrayList<>();
    private final List<MarcajeInterface> marcajes = new ArrayList<>();

    public boolean addAula(AulaInterface aula) {
        return aulas.add(aula);
    }

    public boolean addProducto(ProductoInterface producto) {
        return productos.add(producto);
    }

    public boolean addMarcaje(MarcajeInterface marcaje) {
        return marcajes.add(marcaje);
    }

    public List<AulaInterface> getAulas() {
        return aulas;
    }

    public List<ProductoInterface> getProductos() {
        return productos;
    }

    public List<MarcajeInterface> getMarcajes() {
        return marcajes;
    }

    public List<MarcajeInterface> getMarcajesByProducto(int idProducto, Date fechaInicio, Date fechaFin) {
        List<MarcajeInterface> result = new ArrayList<>();
        for (MarcajeInterface marcaje : marcajes) {
            if (marcaje.getIdProducto() == idProducto &&
                    marcaje.getTimeStamp().after(fechaInicio) &&
                    marcaje.getTimeStamp().before(fechaFin)) {
                result.add(marcaje);
            }
        }
        return result;
    }

    public List<MarcajeInterface> getMarcajesByAula(int idAula, Date fechaInicio, Date fechaFin) {
        List<MarcajeInterface> result = new ArrayList<>();
        for (MarcajeInterface marcaje : marcajes) {
            if (marcaje.getIdAula() == idAula &&
                    marcaje.getTimeStamp().after(fechaInicio) &&
                    marcaje.getTimeStamp().before(fechaFin)) {
                result.add(marcaje);
            }
        }
        return result;
    }

    public void inicio() {
        Scanner scanner = new Scanner(System.in);
        Mediator mediator = new Mediator();
        GestorDatos gestorDatos = new GestorDatos(mediator);
        GestorAulaFileSystem gestorAulaFileSystem = new GestorAulaFileSystem(mediator);
        GestorProductoFileSystem gestorProductoFileSystem = new GestorProductoFileSystem(mediator);
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
                case 1 -> gestorAulaFileSystem.showMenu();
                case 2 -> gestorProductoFileSystem.showMenu();
                case 3 -> GestorMarcaje.showMenuMarcajes();
                case 4 -> GestorReportes.generateReports(mediator, scanner);
                case 5 -> {
                    try {
                        gestorDatos.manageData(scanner);
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(Mediator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                case 0 -> System.exit(0);
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
