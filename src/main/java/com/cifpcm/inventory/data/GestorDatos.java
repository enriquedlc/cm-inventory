package com.cifpcm.inventory.data;

import com.cifpcm.inventory.mediator.Mediator;
import com.cifpcm.inventory.mediator.MediatorInterface;
import com.cifpcm.inventory.models.aula.AulaDatabase;
import com.cifpcm.inventory.models.aula.AulaInterface;
import com.cifpcm.inventory.models.marcaje.MarcajeDatabase;
import com.cifpcm.inventory.models.marcaje.MarcajeInterface;
import com.cifpcm.inventory.models.producto.ProductoDatabase;
import com.cifpcm.inventory.models.producto.ProductoInterface;
import com.cifpcm.inventory.utils.Menu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author tecen
 */
public class GestorDatos {
    private static final String AULA_FILE = "aulas.txt";
    private static final String PRODUCTO_FILE = "productos.txt";
    private static final String MARCAJE_FILE = "marcajes.txt";
    private static Datos datos = null;
    private final Mediator mediator;

    public GestorDatos(Mediator mediator) {
        datos = new Datos(mediator);
        this.mediator = mediator;
    }

    public void manageData(Scanner scanner) throws IOException, ClassNotFoundException {
        ProductoDatabase productoDatabase = new ProductoDatabase();
        AulaDatabase aulaDatabase = new AulaDatabase();
        MarcajeDatabase marcajeDatabase = new MarcajeDatabase();

        while (true) {
            System.out.println("Datos");
            System.out.println("1 - Cargar datos de trabajo desconectado");
            System.out.println("2 - Guardar datos a trabajo desconectado");
            System.out.println("3 - Cargar datos de Base de Datos");
            System.out.println("4 - Guardar datos a Base de Datos");
            System.out.println("0 - Volver");
            System.out.print("Introduzca opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    datos.cargarDatosTrabajoDesconectado(AULA_FILE, PRODUCTO_FILE, MARCAJE_FILE);
                    System.out.println("Datos cargados correctamente.");
                }
                case 2 -> {
                    datos.guardarDatosTrabajoDesconectado(AULA_FILE, PRODUCTO_FILE, MARCAJE_FILE);
                    System.out.println("Datos guardados correctamente.");
                }
                case 3 -> {
                    System.out.println();
                    System.out.println("Cargando datos desde la Base de Datos.");
                    System.out.println();
                    ArrayList<ProductoInterface> productos = productoDatabase.selectAllProductos();
                    List<AulaInterface> aulas = aulaDatabase.selectAllAulas();
                    List<MarcajeInterface> marcajes = marcajeDatabase.selectAllMarcajes();

                    System.out.println("Añadiendo datos a la aplicación.");
                    System.out.println();

                    for (ProductoInterface producto : productos) {
                        mediator.addProducto(producto);
                    }

                    for (AulaInterface aula : aulas) {
                        mediator.addAula(aula);
                    }

                    for (MarcajeInterface marcaje : marcajes) {
                        mediator.addMarcaje(marcaje);
                    }

                    mediator.clearFiles();

                    datos.guardarDatosTrabajoDesconectado(AULA_FILE, PRODUCTO_FILE, MARCAJE_FILE);

                    mediator.clearLists();
                }
                case 4 -> {
                    System.out.println();
                    System.out.println("Guardando datos a la Base de Datos.");
                    System.out.println();

                    datos.cargarDatosTrabajoDesconectado(AULA_FILE, PRODUCTO_FILE, MARCAJE_FILE);

                    for (ProductoInterface producto : mediator.getProductos()) {
                        ProductoInterface dbProducto = productoDatabase.selectProducto(producto.getIdProducto());
                        if (producto.getIdProducto() <= 0 || dbProducto == null) {
                            productoDatabase.insertProducto(producto);
                        } else {
                            productoDatabase.updateProducto(producto);
                        }
                    }

                    for (AulaInterface aula : mediator.getAulas()) {
                        AulaInterface dbAula = aulaDatabase.selectAula(aula.getIdAula());
                        if (aula.getIdAula() <= 0 || dbAula == null) {
                            aulaDatabase.insertAula(aula);
                        } else {
                            aulaDatabase.updateAula(aula);
                        }
                    }

                    for (MarcajeInterface marcaje : mediator.getMarcajes()) {
                        MarcajeInterface dbMarcaje = marcajeDatabase.selectMarcaje(marcaje.getIdMarcaje());
                        if (marcaje.getIdMarcaje() <= 0 || dbMarcaje == null) {
                            marcajeDatabase.insertMarcaje(marcaje);
                        } else {
                            marcajeDatabase.updateMarcaje(marcaje);
                        }
                    }

                    System.out.println("Datos guardados correctamente en la base de datos.");
                    mediator.clearLists();
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
