package edu.proyectodual.service;

import edu.proyectodual.model.connector.MySQLConnector;
import edu.proyectodual.model.dao.Ranking;
import edu.proyectodual.model.dao.Users;
import edu.proyectodual.model.manager.RankingManager;
import edu.proyectodual.model.manager.impl.RankingManagerImpl;
import edu.proyectodual.model.manager.impl.UsersManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RankingService {
    private RankingManager rankingManager;

    public RankingService(RankingManagerImpl rankingManager) {
        this.rankingManager = rankingManager;
    }

    public List<Users> findAll() throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return rankingManager.findAll(con);
        }
    }
    public boolean createRanking(Ranking ranking) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return rankingManager.create(con, ranking);
        }
    }
}
