package edu.proyectodual.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.proyectodual.model.application.connector.MySQLConnector;
import edu.proyectodual.model.application.dao.Preguntas;
import edu.proyectodual.model.application.manager.PreguntasManager;
import edu.proyectodual.model.application.manager.impl.PreguntasManagerImpl;
import edu.proyectodual.model.logs.dao.Log;
import edu.proyectodual.model.logs.dao.LogStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class PreguntasService {
    private PreguntasManager preguntasManager;
    private final LogService logService;


    public PreguntasService(PreguntasManagerImpl preguntasManager) {
        this.preguntasManager = preguntasManager;
        this.logService = new LogService();

    }

    public List<Preguntas> respuestasCon(String letra) throws SQLException, ClassNotFoundException, JsonProcessingException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            logService.registerLog(
                    Log.builder().fecha(LocalDateTime.now())
                            .logStatus(LogStatus.OK).titulo("Buscando respuestas con la "+letra+".")
                            .mensaje("Respuestas encontradas correctamente.").build());
            logService.registerLog(
                    Log.builder().fecha(LocalDateTime.now())
                            .logStatus(LogStatus.OK).titulo("Obteniendo respuestas.")
                            .mensaje("Respuestas con la "+letra+" obtenidas correctamente.").build());
            return preguntasManager.respuestasCon(con, letra);
        }
    }
}
