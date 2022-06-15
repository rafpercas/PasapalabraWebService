package edu.proyectodual.model.logs.connector;

import com.mongodb.MongoClient;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Properties;
import java.util.function.Consumer;

public class MongoConnector {
    @Setter
    @Getter
    Properties prop = new Properties();
    public MongoConnector() {
        try {
            //Loads all the properties of file "config.properties".
            prop.load(getClass().getClassLoader().getResourceAsStream("mongoconfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MongoClient getMongoConnection(){
        return new MongoClient(prop.getProperty(MongoConstants.URL_HOST),
                Integer.parseInt(prop.getProperty(MongoConstants.URL_PORT)));
    }

    public static void main(String[] args) {
        MongoConnector connector = new MongoConnector();
        MongoClient connection = connector.getMongoConnection();
        Consumer<String> consumer = System.out::println;
        connection.listDatabaseNames().forEach(consumer);
    }
}
