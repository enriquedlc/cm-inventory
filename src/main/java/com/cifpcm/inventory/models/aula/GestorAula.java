package com.cifpcm.inventory.models.aula;

import com.cifpcm.inventory.mediator.MediatorInterface;
import com.cifpcm.inventory.utils.Menu;
import com.cifpcm.inventory.utils.Confirm;

import java.util.List;

public class GestorAula {

    public GestorAula(MediatorInterface mediator) {

    }

    public static void showMenuAulas() {
        Aula aula = new Aula();
        int option;
        do {
            System.out.println(Menu.showSpecific("Aula"));
            option = Menu.getInt();
            switch (option) {
                case 1 -> {
                    String numeracion = Menu.getString("Introduce la numeración del aula: ");
                    String descripcion = Menu.getString("Introduce la descripción del aula: ");
                    String ip = Menu.getString("Introduce la IP del aula: ");
                    aula.insertAula(new Aula(numeracion, descripcion, ip));
                    aula.selectAllAulas().forEach(System.out::println);
                }
                case 2 ->
                    aula.selectAllAulas().forEach(System.out::println);
                case 3 -> {
                    aula.selectAllAulas().forEach(System.out::println);
                    int idAula = Menu.getInt("Introduce el id del aula a eliminar: ");
                    boolean confirm = Confirm.getConfirmation("¿Estás seguro de que deseas eliminar el aula con ID " + idAula + "? (s/n): ");
                    if (confirm) {
                        boolean deleted = aula.deleteAula(idAula);
                        if (deleted) {
                            System.out.println("Aula eliminada.");
                        } else {
                            System.out.println("No se pudo eliminar el aula.");
                        }
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                    aula.selectAllAulas().forEach(System.out::println);
                }
                case 4 -> {
                    int idAula = Menu.getInt("Introduce el id del aula a modificar: ");
                    String numeracion = Menu.getString("Introduce la numeración del aula: ");
                    String descripcion = Menu.getString("Introduce la descripción del aula: ");
                    String ip = Menu.getString("Introduce la IP del aula: ");
                    aula.updateAula(new Aula(idAula, numeracion, descripcion, ip));
                    aula.selectAllAulas().forEach(System.out::println);

                }
                case 0 ->
                    System.out.println("Back");
                default ->
                    System.out.println("Opción inválida. Porfavor intenta de nuevo.");
            }
        } while (option != 0);
    }
}
