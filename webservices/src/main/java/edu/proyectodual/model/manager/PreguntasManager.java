package edu.proyectodual.model.manager;

import edu.proyectodual.model.dao.Preguntas;

import java.sql.Connection;
import java.util.List;

public interface PreguntasManager extends Manager{
    List<Preguntas> respuestasCon(Connection con,String letra);
}
