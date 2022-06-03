package edu.proyectodual.model.manager.impl;

import edu.proyectodual.model.dao.Preguntas;
import edu.proyectodual.model.manager.PreguntasManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PreguntasManagerImpl implements PreguntasManager {


    @Override
    public List findAll(Connection con) {
        return null;
    }

    @Override
    public List findAllByIds(Connection con, Set ids) {
        return null;
    }

    @Override
    public boolean create(Connection con, Object entity) {
        return true;
    }

    @Override
    public List<Preguntas> respuestasCon(Connection con, String letra) {
        String sql = "select * "
                + "from preguntas where respuesta like '"+letra+"%'";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet result = stmt.executeQuery();
            result.beforeFirst();

            Preguntas pregunta = null;
            List<Preguntas> listaPreguntas = new ArrayList<>();
            while (result.next()) {
                pregunta = new Preguntas(result);

                listaPreguntas.add(pregunta);

            }

            return listaPreguntas;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
