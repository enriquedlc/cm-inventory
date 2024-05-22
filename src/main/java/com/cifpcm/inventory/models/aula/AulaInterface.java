package com.cifpcm.inventory.models.aula;

import java.util.ArrayList;

public interface AulaInterface {
    int getIdAula();
    String getNumeracion();
    String getDescripcion();
    String getIp();
    
    public boolean insertAula(Aula aula);
    public boolean updateAula(Aula aula); 
    public boolean deleteAula(int id); 
    public Aula selectAula(int id);
    public ArrayList<Aula> selectAllAulas();
}
