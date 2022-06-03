package edu.proyectodual.service;


import edu.proyectodual.model.connector.MySQLConnector;
import edu.proyectodual.model.dao.Users;
import edu.proyectodual.model.manager.UsersManager;
import edu.proyectodual.model.manager.impl.UsersManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsersService {

    private final UsersManager usersManager;

    public UsersService(UsersManagerImpl usersManager) {
        this.usersManager = usersManager;
    }

    public List<Users> findAll() throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return usersManager.findAll(con);
        }
    }

    public Users findByName(String name) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return usersManager.findByName(con, name);
        }
    }
    public Users findByEmail(String email) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return usersManager.findByEmail(con, email);
        }
    }

    public boolean createUser(Users users) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return usersManager.create(con, users);
        }
    }

    public boolean validateUser(String name, String password) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {

           return usersManager.validateUser(con,name,password) != null;


        }
    }

}
