package com.cifpcm.inventory.models.aula;

import com.cifpcm.inventory.mediator.Mediator;
import com.cifpcm.inventory.utils.Menu;

public class GestorAulaFileSystem {
    private static Mediator mediator = null;
    public GestorAulaFileSystem(Mediator mediator) {
        GestorAulaFileSystem.mediator = mediator;
    }

    public void showMenu(){
        AulaFileSystem aulaFileSystem = new AulaFileSystem(mediator);
        int option;

        do {
            System.out.println(Menu.showSpecific("Aula"));
            option = Menu.getInt();
            switch (option) {
                case 1 -> {
                    String numeracion = Menu.getString("Introduce la numeración del aula: ");
                    String descripcion = Menu.getString("Introduce la descripción del aula: ");
                    String ip = Menu.verificarIp("Introduce la IP del aula: ");
                    boolean added = aulaFileSystem.insertAula(new AulaFileSystem(numeracion, descripcion, ip));
                    System.out.println(added ? "Aula añadida." : "No se pudo añadir el aula.");
                    aulaFileSystem.selectAllAulas().forEach(System.out::println);
                }
                case 2 -> aulaFileSystem.selectAllAulas().forEach(System.out::println);
                case 3 -> {
                    aulaFileSystem.selectAllAulas().forEach(System.out::println);
                    int idAula = Menu.getInt("Introduce el id del aula a eliminar: ");

                    boolean hasMarcajes = mediator.getMarcajes().stream()
                            .anyMatch(marcaje -> marcaje.getIdAula() == idAula);

                    if (hasMarcajes) {
                        System.out.println("No se ha podido eliminar el aula debido a que tiene marcajes existentes.");
                    } else {
                        boolean deleted = aulaFileSystem.deleteAula(idAula);
                        if (deleted) {
                            System.out.println("Aula eliminada.");
                        } else {
                            System.out.println("No se pudo eliminar el aula.");
                        }
                    }
                    aulaFileSystem.selectAllAulas().forEach(System.out::println);
                }
                case 4 -> {
                    aulaFileSystem.selectAllAulas().forEach(System.out::println);
                    int idAula = Menu.getInt("Introduce el id del aula a modificar: ");
                    String numeracion = Menu.getString("Introduce la numeración del aula: ");
                    String descripcion = Menu.getString("Introduce la descripción del aula: ");
                    String ip = Menu.verificarIp("Introduce la IP del aula: ");
                    boolean updated = aulaFileSystem.updateAula(new AulaFileSystem(idAula, numeracion, descripcion, ip));
                    System.out.println(updated ? "Aula actualizada." : "No se pudo actualizar el aula.");
                    aulaFileSystem.selectAllAulas().forEach(System.out::println);
                }
                case 0 -> System.out.println("Back");
                default -> System.out.println("Opción inválida. Porfavor intenta de nuevo.");
            }
        } while (option != 0);
    }
}
