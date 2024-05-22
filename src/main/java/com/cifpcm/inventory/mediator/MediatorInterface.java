package com.cifpcm.inventory.mediator;


import java.util.Date;
import java.util.List;
import com.cifpcm.inventory.models.aula.AulaInterface;
import com.cifpcm.inventory.models.marcaje.MarcajeInterface;
import com.cifpcm.inventory.models.producto.ProductoInterface;


public interface MediatorInterface {
    void registerAula(AulaInterface aula);
    void registerProducto(ProductoInterface producto);
    void registerMarcaje(MarcajeInterface marcaje);
    void recordMarcaje(int idProducto, int idAula, String tipo);
    List<MarcajeInterface> getMarcajesByProducto(int idProducto, Date startDate, Date endDate);
    List<MarcajeInterface> getMarcajesByAula(int idAula, Date startDate, Date endDate);
}
