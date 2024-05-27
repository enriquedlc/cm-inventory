package com.cifpcm.inventory.models.marcaje;

import java.util.Date;
import java.util.List;

/**
 *
 * @author tecen
 */
public interface MarcajeManagerInterface {

    boolean insertMarcaje(MarcajeInterface marcaje);

    boolean updateMarcaje(MarcajeInterface marcaje);

    boolean deleteMarcaje(int id);

    MarcajeInterface selectMarcaje(int id);

    boolean deleteMarcajesByProducto(int idProducto);

    int countMarcajesByAula(int idAula);

    List<MarcajeInterface> getAllMarcajes();

    List<MarcajeInterface> getAllMarcajes(Date fechaInicio, Date fechaFin);
}
