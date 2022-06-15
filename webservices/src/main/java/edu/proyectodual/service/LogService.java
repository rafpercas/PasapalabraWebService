package edu.proyectodual.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.MongoClient;
import edu.proyectodual.model.logs.connector.MongoConnector;
import edu.proyectodual.model.logs.dao.Log;
import edu.proyectodual.model.logs.manager.LogManager;
import edu.proyectodual.model.logs.manager.LogManagerImpl;

public class LogService {
    private LogManager manager;

    public LogService(){
        this.manager = new LogManagerImpl();
    }

    public void registerLog(Log log) throws JsonProcessingException {
        MongoClient client = new MongoConnector().getMongoConnection();
        manager.createLogRegistry(client, log);
    }
}
