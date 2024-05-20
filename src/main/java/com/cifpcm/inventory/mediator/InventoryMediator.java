package com.cifpcm.inventory.mediator;

import com.cifpcm.inventory.models.aula.Aula;
import com.cifpcm.inventory.models.marcaje.Marcaje;
import com.cifpcm.inventory.models.producto.Producto;

import java.util.Date;
import java.util.List;

public interface InventoryMediator {
    void registerAula(Aula aula);
    void registerProducto(Producto producto);
    void registerMarcaje(Marcaje marcaje);
    void recordMarcaje(int idProducto, int idAula, String tipo);
    List<Marcaje> getMarcajesByProducto(int idProducto, Date startDate, Date endDate);
    List<Marcaje> getMarcajesByAula(int idAula, Date startDate, Date endDate);
}
