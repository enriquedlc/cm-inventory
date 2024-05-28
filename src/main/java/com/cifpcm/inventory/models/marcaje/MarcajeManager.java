package com.cifpcm.inventory.models.marcaje;

import com.cifpcm.inventory.mediator.Mediator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author tecen
 */
public class MarcajeManager implements MarcajeManagerInterface {

    Mediator mediator;
    public MarcajeManager(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public boolean insertMarcaje(Marcaje marcaje) {
        return mediator.getMarcajes().add(marcaje);
    }

    @Override
    public boolean updateMarcaje(Marcaje marcaje) {
        for (int i = 0; i < mediator.getMarcajes().size(); i++) {
            if (mediator.getMarcajes().get(i).getIdMarcaje() == marcaje.getIdMarcaje()) {
                mediator.getMarcajes().set(i, marcaje);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteMarcaje(int id) {
        return mediator.getMarcajes().removeIf(m -> m.getIdMarcaje() == id);
    }

    @Override
    public Marcaje selectMarcaje(int id) {
        for (Marcaje marcaje : mediator.getMarcajes()) {
            if (marcaje.getIdMarcaje() == id) {
                return marcaje;
            }
        }
        return null;
    }

    @Override
    public boolean deleteMarcajesByProducto(int idProducto) {
        return mediator.getMarcajes().removeIf(m -> m.getIdProducto() == idProducto);
    }

    @Override
    public int countMarcajesByAula(int idAula) {
        return (int) mediator.getMarcajes().stream().filter(m -> m.getIdAula() == idAula).count();
    }

    @Override
    public ArrayList<Marcaje> getAllMarcajes() {
        return mediator.getMarcajes();
    }

    @Override
    public ArrayList<Marcaje> getAllMarcajes(Date fechaInicio, Date fechaFin) {
        return (ArrayList<Marcaje>) mediator.getMarcajes().stream().filter(m -> m.getTimeStamp().after(fechaInicio) && m.getTimeStamp().before(fechaFin)).collect(Collectors.toList());
    }
    
    public static ArrayList<Marcaje> getMarcajesByProducto(int idProducto, ArrayList<Marcaje> marcajes) {
    ArrayList<Marcaje> marcajesByProducto = new ArrayList<>();

    for (Marcaje marcaje : marcajes) {
        if (marcaje.getIdProducto() == idProducto) {
            marcajesByProducto.add(marcaje);
        }
    }

    return marcajesByProducto;
}
}
