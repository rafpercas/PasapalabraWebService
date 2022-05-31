package edu.proyectodual.controller;

import edu.proyectodual.model.dao.Users;
import edu.proyectodual.model.manager.impl.UsersManagerImpl;
import edu.proyectodual.service.UsersService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/users")

public class UsersController {
    private UsersService usersService;

    public UsersController(){
        this.usersService= new UsersService(new UsersManagerImpl());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException, ClassNotFoundException{
      return  Response.ok().entity(usersService.findAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public Response findByName(@PathParam("name" )String name) throws SQLException, ClassNotFoundException{
        try {
            if (name == null) {
                return Response.status(400).entity("Incorrect parameters.").build();
            } else {
                return Response.ok(usersService.findByName(name)).build();
            }
        } catch (SQLException | ClassNotFoundException e){
            return Response.status(500).entity("Internal Error during DB interaction.").build();
        }
    }

}
