package edu.proyectodual.model.manager.impl;

import edu.proyectodual.model.dao.Users;
import edu.proyectodual.model.manager.UsersManager;


import java.sql.*;
import java.util.List;
import java.util.Set;

public class UsersManagerImpl implements UsersManager {
    @Override
    public Users validateUser(Connection con, String name, String password) {
        String sql = "select * "
                + "from Users "
                + "where  name = ? "
                + "and password = ? ";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
            result.beforeFirst();


            Users user = null;
            if(result.next()){
                user = new Users(result);
            }


            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Users> findAll(Connection con) {
        return null;
    }

    @Override
    public Users findByName(Connection con, String name) {

        String sql = "select * "
                + "from Users "
                + "where  name = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet result = stmt.executeQuery();
            result.beforeFirst();
            result.next();

            Users user = new Users(result);


            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Users> findAllByIds(Connection con, Set<String> ids) {
        return null;
    }

    @Override
    public int create(Connection con, Users user) {
        //prepare SQL statement
        String sql = "INSERT INTO Users (name, email, password) values(?,?,?)";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            //Add Parameters
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            // Queries the DB
            int affectedRows = stmt.executeUpdate();

            if(affectedRows<=0){
                return 0;
            }

            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.beforeFirst();
            resultSet.next();

            return resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
