package edu.proyectodual.model.logs.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {
    private static final long serialVersionUID = 1L;
    protected LocalDateTimeDeserializer (){
        super(LocalDateTime.class);
    }
    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        return LocalDateTime.parse(jp.readValueAs(String.class));
    }
}
