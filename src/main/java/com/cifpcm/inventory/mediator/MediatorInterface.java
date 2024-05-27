package com.cifpcm.inventory.mediator;

import com.cifpcm.inventory.models.aula.AulaInterface;
import com.cifpcm.inventory.models.marcaje.MarcajeInterface;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface MediatorInterface {
     // Métodos para obtener marcajes
    List<MarcajeInterface> getMarcajesByProducto(int idProducto, Date fechaInicio, Date fechaFin);

    List<MarcajeInterface> getMarcajesByAula(int idAula, Date fechaInicio, Date fechaFin);

    List<MarcajeInterface> getMarcajesByProductoYAula(int idProducto, int idAula);

    List<MarcajeInterface> obtenerMarcajesConErrores();

    List<MarcajeInterface> obtenerMarcajesConErrores(Date fechaInicio, Date fechaFin);

    // Método para obtener numeraciones de aulas
    Map<Integer, String> obtenerNumeracionesAulas();

    // Método para iniciar el menú de la aplicación
    void inicio();
}
