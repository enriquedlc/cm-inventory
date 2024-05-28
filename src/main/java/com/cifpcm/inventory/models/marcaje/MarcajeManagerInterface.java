package com.cifpcm.inventory.models.marcaje;

import java.util.Date;
import java.util.List;

/**
 *
 * @author tecen
 */
public interface MarcajeManagerInterface {

    boolean insertMarcaje(Marcaje marcaje);

    boolean updateMarcaje(Marcaje marcaje);

    boolean deleteMarcaje(int id);

    Marcaje selectMarcaje(int id);

    boolean deleteMarcajesByProducto(int idProducto);

    int countMarcajesByAula(int idAula);

    List<Marcaje> getAllMarcajes();

    List<Marcaje> getAllMarcajes(Date fechaInicio, Date fechaFin);
}
