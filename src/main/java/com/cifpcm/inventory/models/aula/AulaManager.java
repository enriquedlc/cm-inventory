package com.cifpcm.inventory.models.aula;

import com.cifpcm.inventory.mediator.Mediator;
import java.util.ArrayList;

/**
 *
 * @author tecen
 */
public class AulaManager implements AulaManagerInterface {

    Mediator mediator;
    public AulaManager(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public boolean insertAula(Aula aula) {
        return mediator.getAulas().add(aula);
    }

    @Override
    public boolean updateAula(Aula aula) {
        for (int i = 0; i < mediator.getAulas().size(); i++) {
            if (mediator.getAulas().get(i).getIdAula() == aula.getIdAula()) {
                mediator.getAulas().set(i, aula);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteAula(int idAula) {
        return mediator.getAulas().removeIf(aula -> aula.getIdAula() == idAula);
    }

    @Override
    public Aula selectAula(int idAula) {
        for (Aula aula : mediator.getAulas()) {
            if (aula.getIdAula() == idAula) {
                return aula;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Aula> selectAllAulas() {
        return mediator.getAulas();
    }
    
    public boolean exists(int idAula) {
        return mediator.getAulas().stream().anyMatch(aula -> aula.getIdAula() == idAula);
    }
}
