package edu.proyectodual.model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ranking {
    private String name;
    private Integer points;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Ranking(ResultSet result) {
        try {
            this.name = result.getString("name");
            this.points = result.getInt("points");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
