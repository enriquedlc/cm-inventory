package com.cifpcm.inventory.models.aula;

import com.cifpcm.inventory.mediator.MediatorInterface;
import com.cifpcm.inventory.utils.Menu;
import com.cifpcm.inventory.utils.Confirm;
import com.cifpcm.inventory.utils.VerificarEntrada;


public class GestorAula {

   private AulaManager aulaManager;
    
    public GestorAula(){
        this.aulaManager = new AulaManager(); // Inicializa aulaManager
    }

    public GestorAula(MediatorInterface mediator) {
        this.aulaManager = new AulaManager(); // Inicializa aulaManager
    }
   
    public void showMenuAulas() {
        int option;
        do {
            System.out.println(Menu.showSpecific("Aula"));
            option = VerificarEntrada.getMenuOption("Selecciona una opción: ", 0, 4);
            switch (option) {
                case 1 -> {
                    String numeracion = VerificarEntrada.getString("Introduce la numeración del aula: ");
                    String descripcion = VerificarEntrada.getString("Introduce la descripción del aula: ");
                    String ip = VerificarEntrada.getIp("Introduce la IP del aula: ");
                    aulaManager.insertAula(new Aula(numeracion, descripcion, ip));
                    aulaManager.selectAllAulas().forEach(System.out::println);
                }
                case 2 -> aulaManager.selectAllAulas().forEach(System.out::println);
                case 3 -> {
                    aulaManager.selectAllAulas().forEach(System.out::println);
                    int idAula = VerificarEntrada.getInt("Introduce el id del aula a eliminar: ");
                    boolean confirm = Confirm.getConfirmation("¿Estás seguro de que deseas eliminar el aula con ID " + idAula + "? (s/n): ");
                    if (confirm) {
                        boolean deleted = aulaManager.deleteAula(idAula);
                        if (deleted) {
                            System.out.println("Aula eliminada.");
                        } else {
                            System.out.println("No se pudo eliminar el aula.");
                        }
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                    aulaManager.selectAllAulas().forEach(System.out::println);
                }
                case 4 -> {
                    int idAula = VerificarEntrada.getInt("Introduce el id del aula a modificar: ");
                    String numeracion = VerificarEntrada.getString("Introduce la numeración del aula: ");
                    String descripcion = VerificarEntrada.getString("Introduce la descripción del aula: ");
                    String ip = VerificarEntrada.getIp("Introduce la IP del aula: ");
                    boolean updated = aulaManager.updateAula(new Aula(idAula, numeracion, descripcion, ip));
                    if (updated) {
                        System.out.println("Aula actualizada.");
                    } else {
                        System.out.println("No se pudo actualizar el aula.");
                    }
                    aulaManager.selectAllAulas().forEach(System.out::println);
                }
                case 0 -> System.out.println("Regresando al menú principal.");
                default -> System.out.println("Opción inválida. Por favor, intenta de nuevo.");
            }
        } while (option != 0);
    }
}
