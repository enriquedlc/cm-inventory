package com.cifpcm.inventory.models.marcaje;

import com.cifpcm.inventory.models.marcaje.enums.TipoMarcaje;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MarcajeInterface {
    int getIdMarcaje();
    int getIdProducto();
    int getIdAula();
    Date getTimeStamp();
    TipoMarcaje getTipo();
    String getDescripcionProducto();

   
     

    
}
