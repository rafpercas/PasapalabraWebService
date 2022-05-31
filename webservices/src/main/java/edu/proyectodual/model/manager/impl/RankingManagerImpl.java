package edu.proyectodual.model.manager.impl;

import edu.proyectodual.model.dao.Ranking;
import edu.proyectodual.model.dao.Users;
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
        String sql = "select *"
                + "from Ranking order by points desc";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet result = stmt.executeQuery();
            result.beforeFirst();

            // Initialize variable
            Ranking ranking = null;
            List<Ranking> listaRanking = new ArrayList<>();
            // Run through each result
            while (result.next()) {
                // Initializes a city per result
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
    public Object findByName(Connection con, Object id) {
        return null;
    }

    @Override
    public List findAllByIds(Connection con, Set ids) {
        return null;
    }
}
