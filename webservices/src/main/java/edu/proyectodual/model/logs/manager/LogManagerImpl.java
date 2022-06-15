package edu.proyectodual.model.logs.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import edu.proyectodual.model.logs.dao.Log;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class LogManagerImpl implements LogManager{
    private static final String DB= "local";
    private static final String COLLECTION="logs";
    @Override
    public void createLogRegistry(MongoClient client, Log log) throws JsonProcessingException {
        MongoDatabase db = client.getDatabase(DB);

        MongoCollection<Document> collection = db.getCollection(COLLECTION);

        Document logDocument = new Document();
        logDocument.append("fecha",log.getFecha().toString());
        logDocument.append("logStatus",log.getLogStatus().name());
        logDocument.append("titulo",log.getTitulo());
        logDocument.append("mensaje",log.getMensaje());

        collection.insertOne(logDocument);
    }

    @Override
    public List<Log> findAll(MongoClient client) throws JsonProcessingException {
        MongoDatabase db = client.getDatabase(DB);
        MongoCollection<Document> collection = db.getCollection(COLLECTION);
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> cursor = documents.projection(Projections.excludeId()).iterator();
        List<Log> logs = new ArrayList<>();
        while(cursor.hasNext()){
            Log readLog = new ObjectMapper().readValue(cursor.next().toJson(), Log.class);
            logs.add(readLog);
        }
        return logs;
    }
}
