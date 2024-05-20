package com.cifpcm.inventory.models.aula;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AulaFileSystem implements AulaRepository {

    @Override
    public boolean create(Aula aula) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String fileName = "aula-" + dtf.format(now);
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("Numeracion: " + aula.getNumeracion() + "\n");
            writer.write("Descripcion: " + aula.getDescripcion() + "\n");
            writer.write("Direccion IP: " + aula.getDireccionIp() + "\n");
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Aula read(int idAula) {
        return null;
    }

    @Override
    public boolean update(Aula aula) {
        return false;
    }

    @Override
    public boolean delete(Aula aula) {
        return false;
    }
}
