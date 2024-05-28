package com.cifpcm.inventory.models.aula;

import java.util.ArrayList;

public interface AulaInterface {
    int getIdAula();
    String getNumeracion();
    String getDescripcion();
    String getIp();
    
    public boolean insertAula(AulaInterface aulaDatabase);
    public boolean updateAula(AulaInterface aulaDatabase);
    public boolean deleteAula(int id); 
    public AulaInterface selectAula(int id);
    public ArrayList<AulaInterface> selectAllAulas();
}
