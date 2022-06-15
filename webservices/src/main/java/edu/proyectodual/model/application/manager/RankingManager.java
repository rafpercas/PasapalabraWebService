package edu.proyectodual.model.application.manager;

import edu.proyectodual.model.application.dao.Ranking;

import java.sql.Connection;

public interface RankingManager extends Manager{

    boolean create(Connection con, Ranking ranking);
}
