package edu.proyectodual.model.application.manager;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

public interface Manager<T, U> {
    /**
     * Finds all the entities in the DB
     *
     * @param con DB connection
     * @return a {@link List} of {@link T}
     */
    List<T> findAll(Connection con);


    /**
     * Finds all the entities in the DB based on a list of ids.
     *
     * @param con DB connection
     * @param ids Entities id set to search for.
     * @return a {@link List} of {@link T}
     */
    List<T> findAllByIds(Connection con, Set<U> ids);

    boolean create(Connection con, T entity);

}
