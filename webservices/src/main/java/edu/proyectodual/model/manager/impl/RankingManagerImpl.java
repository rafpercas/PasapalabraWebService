package edu.proyectodual.model.manager.impl;

import edu.proyectodual.model.dao.Ranking;
import edu.proyectodual.model.manager.RankingManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RankingManagerImpl implements RankingManager {

    @Override
    public List<Ranking> findAll(Connection con) {
        String sql = "select * "
                + "from Ranking order by points desc";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet result = stmt.executeQuery();
            result.beforeFirst();

            Ranking ranking = null;
            List<Ranking> listaRanking = new ArrayList<>();
            while (result.next()) {
                ranking = new Ranking(result);

                listaRanking.add(ranking);

            }

            return listaRanking;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean create(Connection con, Ranking ranking) {
        //prepare SQL statement
        String sql = "INSERT INTO Ranking (name, points) values(?,?)";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setString(1, ranking.getName());
            stmt.setInt(2, ranking.getPoints());

            // Queries the DB
            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List findAllByIds(Connection con, Set ids) {
        return null;
    }

    @Override
    public boolean create(Connection con, Object entity) {
        return true;
    }
}
