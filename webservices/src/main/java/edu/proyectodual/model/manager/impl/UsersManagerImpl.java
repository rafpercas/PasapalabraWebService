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
    public Users findByEmail(Connection con, String email) {

        String sql = "select * "
                + "from Users "
                + "where  email = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
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
    public boolean create(Connection con, Users user) {
        //prepare SQL statement
        String sql = "INSERT INTO Users (name, email, password) values(?,?,?)";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            // Queries the DB
            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
