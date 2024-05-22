package com.cifpcm.inventory.models.aula;

import com.cifpcm.inventory.mediator.MediatorInterface;
import com.cifpcm.inventory.utils.Menu;
public class GestorAula  {

    public GestorAula(MediatorInterface mediator) {

    }

    public static void showMenuAulas() {
        Menu.manageSpecific("Aula");
    }
}

