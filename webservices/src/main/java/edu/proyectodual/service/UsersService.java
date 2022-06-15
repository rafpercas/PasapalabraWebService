package edu.proyectodual.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import edu.proyectodual.model.application.connector.MySQLConnector;
import edu.proyectodual.model.application.dao.Users;
import edu.proyectodual.model.application.manager.UsersManager;
import edu.proyectodual.model.application.manager.impl.UsersManagerImpl;
import edu.proyectodual.model.logs.dao.Log;
import edu.proyectodual.model.logs.dao.LogStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class UsersService {

    private final UsersManager usersManager;
    private final LogService logService;

    public UsersService(UsersManagerImpl usersManager) {
        this.usersManager = usersManager;
        this.logService = new LogService();

    }

    public List<Users> findAll() throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return usersManager.findAll(con);
        }
    }

    public Users findByName(String name) throws SQLException, ClassNotFoundException, JsonProcessingException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            logService.registerLog(
                    Log.builder().fecha(LocalDateTime.now())
                            .logStatus(LogStatus.OK).titulo("Buscando usuario por nombre.")
                            .mensaje("Buscando usuario con el nombre "+name).build());
            logService.registerLog(
                    Log.builder().fecha(LocalDateTime.now())
                            .logStatus(LogStatus.OK).titulo("Usuario encontrado.")
                            .mensaje("Se ha encontrado un usuario con el nombre "+name).build());
            return usersManager.findByName(con, name);
        }
    }
    public Users findByEmail(String email) throws SQLException, ClassNotFoundException, JsonProcessingException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            logService.registerLog(
                    Log.builder().fecha(LocalDateTime.now())
                            .logStatus(LogStatus.OK).titulo("Buscando usuario por email.")
                            .mensaje("Buscando usuario con el email "+email).build());
            logService.registerLog(
                    Log.builder().fecha(LocalDateTime.now())
                            .logStatus(LogStatus.OK).titulo("Usuario encontrado.")
                            .mensaje("Se ha encontrado un usuario con el email "+email).build());
            return usersManager.findByEmail(con, email);
        }
    }

    public boolean createUser(Users users) throws SQLException, ClassNotFoundException, JsonProcessingException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            logService.registerLog(
                    Log.builder().fecha(LocalDateTime.now())
                            .logStatus(LogStatus.OK).titulo("Creando usuario.")
                            .mensaje("Creando usuario en base de datos.").build());
            logService.registerLog(
                    Log.builder().fecha(LocalDateTime.now())
                            .logStatus(LogStatus.OK).titulo("Usuario creado.")
                            .mensaje("Se ha creado un usuario con nombre de "+users.getName()).build());
            return usersManager.create(con, users);
        }
    }

    public boolean validateUser(String name, String password) throws JsonProcessingException, SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            logService.registerLog(
                    Log.builder().fecha(LocalDateTime.now())
                            .logStatus(LogStatus.OK).titulo("Validando usuario.")
                            .mensaje("Validando usuario con nombre "+name).build());
            logService.registerLog(
                    Log.builder().fecha(LocalDateTime.now())
                            .logStatus(LogStatus.OK).titulo("Usuario validado.")
                            .mensaje("Se ha validado correctamente el usuario "+name).build());
           return usersManager.validateUser(con,name,password) != null;


        }
    }

}
