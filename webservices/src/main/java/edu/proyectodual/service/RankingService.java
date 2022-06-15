package edu.proyectodual.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.proyectodual.model.application.connector.MySQLConnector;
import edu.proyectodual.model.application.dao.Ranking;
import edu.proyectodual.model.application.dao.Users;
import edu.proyectodual.model.application.manager.RankingManager;
import edu.proyectodual.model.application.manager.impl.RankingManagerImpl;
import edu.proyectodual.model.logs.dao.Log;
import edu.proyectodual.model.logs.dao.LogStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class RankingService {
    private RankingManager rankingManager;
    private final LogService logService;
    public RankingService(RankingManagerImpl rankingManager) {
        this.rankingManager = rankingManager;
        this.logService = new LogService();

    }

    public List<Users> findAll() throws SQLException, ClassNotFoundException, JsonProcessingException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            logService.registerLog(
                    Log.builder().fecha(LocalDateTime.now())
                            .logStatus(LogStatus.OK).titulo("Buscando los ranking.")
                            .mensaje("Buscando todos los datos de ranking.").build());
            List<Users> ranking = rankingManager.findAll(con);
            logService.registerLog(
                    Log.builder().fecha(LocalDateTime.now())
                            .logStatus(LogStatus.OK).titulo("Busqueda de ranking exitosa.")
                            .mensaje("Busqueda de datos de ranking. Encontrados "+ranking.size()+" registros.").build());
            return ranking;
        }
    }
    public boolean createRanking(Ranking ranking) throws SQLException, ClassNotFoundException, JsonProcessingException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            logService.registerLog(
                    Log.builder().fecha(LocalDateTime.now())
                            .logStatus(LogStatus.OK).titulo("Creando ranking.")
                            .mensaje("Creando registro en la tabla ranking.").build());
            logService.registerLog(
                    Log.builder().fecha(LocalDateTime.now())
                            .logStatus(LogStatus.OK).titulo("Ranking creado.")
                            .mensaje("Se ha agregado el ranking creado correctamente.").build());
            return rankingManager.create(con, ranking);

        }
    }
}
