package edu.proyectodual.controller;

import edu.proyectodual.model.manager.impl.PreguntasManagerImpl;
import edu.proyectodual.model.manager.impl.RankingManagerImpl;
import edu.proyectodual.service.PreguntasService;
import edu.proyectodual.service.RankingService;
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
        public Response respuestasCon(@PathParam("letra" )String letra) throws SQLException, ClassNotFoundException{
            return  Response.ok().entity(preguntasService.respuestasCon(letra)).build();
        }
}
