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
    public UsersService(UsersManagerImpl usersManager){
        this.usersManager = usersManager;
    }

    public List<Users> findAll() throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return usersManager.findAll(con);
        }
    }

    public Users findByName(String name) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return usersManager.findByName(con,name);
        }
    }


}
