package edu.proyectodual.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.proyectodual.model.application.dao.Ranking;
import edu.proyectodual.model.application.manager.impl.RankingManagerImpl;
import edu.proyectodual.service.RankingService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
@Path("/ranking")
public class RankingController {
    private RankingService rankingService;
    public RankingController(){
        this.rankingService= new RankingService(new RankingManagerImpl());
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException, ClassNotFoundException, JsonProcessingException {
        return  Response.ok().entity(rankingService.findAll()).build();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addranking")
    public Response createRanking(Ranking ranking) throws JsonProcessingException {
        try {

            if (rankingService.createRanking(ranking)) {
                return Response.status(201).build();
            } else {
                return Response.status(500).entity("Internal Error During Creating The User").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }
}
