package edu.proyectodual.controller;

import edu.proyectodual.model.dao.Ranking;
import edu.proyectodual.model.dao.Users;
import edu.proyectodual.model.manager.impl.RankingManagerImpl;
import edu.proyectodual.model.manager.impl.UsersManagerImpl;
import edu.proyectodual.service.RankingService;
import edu.proyectodual.service.UsersService;
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
    public Response findAll() throws SQLException, ClassNotFoundException{
        return  Response.ok().entity(rankingService.findAll()).build();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addranking")
    public Response createRanking(Ranking ranking) {
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
