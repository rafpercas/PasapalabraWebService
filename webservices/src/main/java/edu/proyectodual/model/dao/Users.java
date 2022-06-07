package edu.proyectodual.model.dao;

import lombok.AllArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
@AllArgsConstructor
public class Users {
    private String name;
    private String email;
    private String password;

    public Users() {

    }
    public Users(ResultSet result) {
        try {
            this.name = result.getString("name");
            this.email = result.getString("email");
            this.password = result.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
