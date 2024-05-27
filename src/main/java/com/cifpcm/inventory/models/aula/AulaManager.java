package com.cifpcm.inventory.models.aula;

import java.util.ArrayList;

/**
 *
 * @author tecen
 */
public class AulaManager implements AulaManagerInterface {

    private ArrayList<Aula> aulas;

    public AulaManager() {
        this.aulas = new ArrayList<>();
    }

    @Override
    public boolean insertAula(Aula aula) {
        return aulas.add(aula);
    }

    @Override
    public boolean updateAula(Aula aula) {
        for (int i = 0; i < aulas.size(); i++) {
            if (aulas.get(i).getIdAula() == aula.getIdAula()) {
                aulas.set(i, aula);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteAula(int idAula) {
        return aulas.removeIf(aula -> aula.getIdAula() == idAula);
    }

    @Override
    public Aula selectAula(int idAula) {
        for (Aula aula : aulas) {
            if (aula.getIdAula() == idAula) {
                return aula;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Aula> selectAllAulas() {
        return new ArrayList<>(aulas);
    }
    
    public boolean exists(int idAula) {
        for (Aula aula : aulas) {
            if (aula.getIdAula() == idAula) {
                return true;
            }
        }
        return false;
    }
}
