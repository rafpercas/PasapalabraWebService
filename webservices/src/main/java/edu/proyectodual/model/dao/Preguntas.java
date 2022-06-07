package edu.proyectodual.model.dao;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Preguntas {
    private String pregunta;
    private String respuesta;

    public Preguntas(ResultSet result) {
        try {
            this.pregunta = result.getString("pregunta");
            this.respuesta = result.getString("respuesta");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
