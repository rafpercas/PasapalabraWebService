package edu.proyectodual.service;

import edu.proyectodual.model.connector.MySQLConnector;
import edu.proyectodual.model.dao.Preguntas;
import edu.proyectodual.model.dao.Users;
import edu.proyectodual.model.manager.PreguntasManager;
import edu.proyectodual.model.manager.RankingManager;
import edu.proyectodual.model.manager.impl.PreguntasManagerImpl;
import edu.proyectodual.model.manager.impl.RankingManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PreguntasService {
    private PreguntasManager preguntasManager;

    public PreguntasService(PreguntasManagerImpl preguntasManager) {
        this.preguntasManager = preguntasManager;
    }

    public List<Preguntas> respuestasCon(String letra) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return preguntasManager.respuestasCon(con, letra);
        }
    }
}
