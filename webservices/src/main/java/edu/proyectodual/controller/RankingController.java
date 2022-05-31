package edu.proyectodual.controller;

import edu.proyectodual.model.manager.impl.RankingManagerImpl;
import edu.proyectodual.model.manager.impl.UsersManagerImpl;
import edu.proyectodual.service.RankingService;
import edu.proyectodual.service.UsersService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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
}
