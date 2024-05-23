package com.cifpcm.inventory;

import com.cifpcm.inventory.mediator.Mediator;
import com.cifpcm.inventory.mediator.MediatorInterface;

public class Main {
    public static void main(String[] args) {
        MediatorInterface mediator = new Mediator();
        mediator.inicio();
    }
}
