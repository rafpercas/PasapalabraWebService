package edu.proyectodual.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.proyectodual.model.application.manager.impl.PreguntasManagerImpl;
import edu.proyectodual.service.PreguntasService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/preguntas")
public class PreguntasController {

        private PreguntasService preguntasService;
        public PreguntasController(){
            this.preguntasService= new PreguntasService(new PreguntasManagerImpl());
        }
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/{letra}")
        public Response respuestasCon(@PathParam("letra" )String letra) throws SQLException, ClassNotFoundException, JsonProcessingException {
            return  Response.ok().entity(preguntasService.respuestasCon(letra)).build();
        }
}
