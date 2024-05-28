package com.cifpcm.inventory.models.marcaje;

import com.cifpcm.inventory.models.marcaje.enums.TipoMarcaje;

import java.util.ArrayList;
import java.util.Date;

public interface MarcajeInterface {
    int getIdMarcaje();
    int getIdProducto();
    int getIdAula();
    Date getTimeStamp();
    TipoMarcaje getTipo();
     
    public boolean insertMarcaje(MarcajeInterface marcaje);
    public boolean updateMarcaje(MarcajeInterface marcaje); 
    public boolean deleteMarcaje(int id); 
    public MarcajeInterface selectMarcaje(int id);
    public ArrayList<MarcajeInterface> selectAllMarcajes();
}
