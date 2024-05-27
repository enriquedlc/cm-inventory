package com.cifpcm.inventory.models.marcaje;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author tecen
 */
public class MarcajeManager implements MarcajeManagerInterface {

    private ArrayList<MarcajeInterface> marcajes;

    public MarcajeManager() {
        this.marcajes = new ArrayList<>();
    }

    @Override
    public boolean insertMarcaje(MarcajeInterface marcaje) {
        return marcajes.add(marcaje);
    }

    @Override
    public boolean updateMarcaje(MarcajeInterface marcaje) {
        for (int i = 0; i < marcajes.size(); i++) {
            if (marcajes.get(i).getIdMarcaje() == marcaje.getIdMarcaje()) {
                marcajes.set(i, marcaje);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteMarcaje(int id) {
        return marcajes.removeIf(m -> m.getIdMarcaje() == id);
    }

    @Override
    public MarcajeInterface selectMarcaje(int id) {
        for (MarcajeInterface marcaje : marcajes) {
            if (marcaje.getIdMarcaje() == id) {
                return marcaje;
            }
        }
        return null;
    }

    @Override
    public boolean deleteMarcajesByProducto(int idProducto) {
        return marcajes.removeIf(m -> m.getIdProducto() == idProducto);
    }

    @Override
    public int countMarcajesByAula(int idAula) {
        return (int) marcajes.stream().filter(m -> m.getIdAula() == idAula).count();
    }

    @Override
    public ArrayList<MarcajeInterface> getAllMarcajes() {
        return new ArrayList<>(marcajes);
    }

    @Override
    public ArrayList<MarcajeInterface> getAllMarcajes(Date fechaInicio, Date fechaFin) {
        return marcajes.stream()
                .filter(m -> !m.getTimeStamp().before(fechaInicio) && !m.getTimeStamp().after(fechaFin))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    public static ArrayList<MarcajeInterface> getMarcajesByProducto(int idProducto, ArrayList<MarcajeInterface> marcajes) {
    ArrayList<MarcajeInterface> marcajesByProducto = new ArrayList<>();

    for (MarcajeInterface marcaje : marcajes) {
        if (marcaje.getIdProducto() == idProducto) {
            marcajesByProducto.add(marcaje);
        }
    }

    return marcajesByProducto;
}
}
