package com.cifpcm.inventory.models.aula;

import com.cifpcm.inventory.mediator.MediatorInterface;
import com.cifpcm.inventory.utils.Menu;

import java.util.List;

public class GestorAula  {

    public GestorAula(MediatorInterface mediator) {

    }

    public static void showMenuAulas() {
        Aula aula = new Aula();
        int option;
        do  {
            System.out.println(Menu.showSpecific("Aula"));
            option = Menu.getInt();
            switch (option) {
                case 1 -> {
                    String numeracion = Menu.getString("Introduce la numeración del aula: ");
                    String descripcion = Menu.getString("Introduce la descripción del aula: ");
                    String ip = Menu.getString("Introduce la IP del aula: ");
                    aula.insertAula(new Aula(numeracion, descripcion, ip));
                }
                case 2 -> aula.selectAllAulas().forEach(System.out::println);
                case 3 -> {
                    int idAula = Menu.getInt("Introduce el id del aula a eliminar: ");
                    aula.deleteAula(idAula);
                }
                case 4 -> {
                    int idAula = Menu.getInt("Introduce el id del aula a modificar: ");
                    String numeracion = Menu.getString("Introduce la numeración del aula: ");
                    String descripcion = Menu.getString("Introduce la descripción del aula: ");
                    String ip = Menu.getString("Introduce la IP del aula: ");
                    aula.updateAula(new Aula(idAula, numeracion, descripcion, ip));

                }
                case 0 -> System.out.println("Back");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 0);
    }
}

