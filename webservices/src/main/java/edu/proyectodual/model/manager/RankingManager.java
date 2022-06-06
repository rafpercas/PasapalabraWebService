package edu.proyectodual.model.manager;

import edu.proyectodual.model.dao.Ranking;

import java.sql.Connection;

public interface RankingManager extends Manager{

    boolean create(Connection con, Ranking ranking);
}
