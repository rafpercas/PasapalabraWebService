package edu.proyectodual.model.application.manager;

import edu.proyectodual.model.application.dao.Preguntas;

import java.sql.Connection;
import java.util.List;

public interface PreguntasManager extends Manager{
    List<Preguntas> respuestasCon(Connection con, String letra);
}
