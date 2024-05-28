package com.cifpcm.inventory.models.aula;

import com.cifpcm.inventory.mediator.Mediator;
import com.cifpcm.inventory.utils.Menu;
import com.cifpcm.inventory.utils.Confirm;

public class GestorAulaDatabase {

    private static Mediator mediator = null;
    public GestorAulaDatabase(Mediator mediator) {
        GestorAulaDatabase.mediator = mediator;
    }

    public static void showMenuAulas() {
        AulaDatabase aulaDatabase = new AulaDatabase();
        int option;
        do {
            System.out.println(Menu.showSpecific("Aula"));
            option = Menu.getInt();
            switch (option) {
                case 1 -> {
                    String numeracion = Menu.getString("Introduce la numeración del aula: ");
                    String descripcion = Menu.getString("Introduce la descripción del aula: ");
                    String ip = Menu.verificarIp("Introduce la IP del aula: ");
                    boolean added = aulaDatabase.insertAula(new AulaDatabase(mediator, numeracion, descripcion, ip));
                    System.out.println(added ? "Aula añadida." : "No se pudo añadir el aula.");
                    aulaDatabase.selectAllAulas().forEach(System.out::println);
                }
                case 2 ->
                    aulaDatabase.selectAllAulas().forEach(System.out::println);
                case 3 -> {
                    aulaDatabase.selectAllAulas().forEach(System.out::println);
                    int idAula = Menu.getInt("Introduce el id del aula a eliminar: ");
                    boolean confirm = Confirm.getConfirmation("¿Estás seguro de que deseas eliminar el aula con ID " + idAula + "? (s/n): ");
                    if (confirm) {
                        boolean deleted = aulaDatabase.deleteAula(idAula);
                        if (deleted) {
                            System.out.println("Aula eliminada.");
                        } else {
                            System.out.println("No se pudo eliminar el aula.");
                        }
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                    aulaDatabase.selectAllAulas().forEach(System.out::println);
                }
                case 4 -> {
                    int idAula = Menu.getInt("Introduce el id del aula a modificar: ");
                    String numeracion = Menu.getString("Introduce la numeración del aula: ");
                    String descripcion = Menu.getString("Introduce la descripción del aula: ");
                    String ip = Menu.verificarIp("Introduce la IP del aula: ");
                    boolean updated = aulaDatabase.updateAula(new AulaDatabase(mediator, idAula, numeracion, descripcion, ip));
                    if (updated) {
                        System.out.println("Aula actualizada.");
                    } else {
                        System.out.println("No se pudo actualizar el aula.");
                    }
                    aulaDatabase.selectAllAulas().forEach(System.out::println);
                }
                case 0 ->
                    System.out.println("Back");
                default ->
                    System.out.println("Opción inválida. Porfavor intenta de nuevo.");
            }
        } while (option != 0);
    }
}
