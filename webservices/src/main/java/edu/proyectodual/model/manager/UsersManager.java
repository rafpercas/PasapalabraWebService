package edu.proyectodual.model.manager;

import edu.proyectodual.model.dao.Users;

import java.sql.Connection;
import java.util.Set;

public interface UsersManager extends Manager<Users,String> {

    Users validateUser(Connection con, String name,String password);


    /**
     * Finds all the entities in the DB based on a list of ids.
     *
     * @param con DB connection
     * @param name  Entities id to search for.
     * @return a {@link Users}
     */
    Users findByName(Connection con, String name);
    Users findByEmail(Connection con, String email);


}
