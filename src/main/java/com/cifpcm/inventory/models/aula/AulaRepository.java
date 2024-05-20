package com.cifpcm.inventory.models.aula;

public interface AulaRepository {
    boolean create(Aula aula);
    Aula read(int idAula);
    boolean update(Aula aula);
    boolean delete(Aula aula);
}
