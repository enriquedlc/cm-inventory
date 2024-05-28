package com.cifpcm.inventory.models.aula;

import java.util.ArrayList;

/**
 *
 * @author tecen
 */
public interface AulaManagerInterface {
    public boolean insertAula(Aula aula);
    public boolean updateAula(Aula aula); 
    public boolean deleteAula(int id); 
    public Aula selectAula(int id);
    ArrayList<Aula> selectAllAulas();
    boolean exists(int idAula);
    
}
