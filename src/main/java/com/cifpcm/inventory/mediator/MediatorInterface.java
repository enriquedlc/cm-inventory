package com.cifpcm.inventory.mediator;

import com.cifpcm.inventory.models.marcaje.MarcajeInterface;
import java.util.Date;
import java.util.List;



public interface MediatorInterface {
    void inicio();
     List<MarcajeInterface> getMarcajesByProducto(int idProducto, Date fechaInicio, Date fechaFin);
     List<MarcajeInterface> getMarcajesByAula(int idAula, Date fechaInicio, Date fechaFin);
}
