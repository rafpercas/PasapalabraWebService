package edu.proyectodual.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import edu.proyectodual.crearpdf.PdfCreator;
import edu.proyectodual.email.Sender;
import edu.proyectodual.model.dao.Users;
import edu.proyectodual.model.manager.impl.UsersManagerImpl;
import edu.proyectodual.service.UsersService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
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
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/email/{email}")
    public Response getUsersByEmail(@PathParam("email" )String email) throws SQLException, ClassNotFoundException{
        try {
            if (email == null) {
                return Response.status(400).entity("Incorrect parameters.").build();
            } else {
                return Response.ok(usersService.findByEmail(email)).build();
            }
        } catch (SQLException | ClassNotFoundException e){
            return Response.status(500).entity("Internal Error during DB interaction.").build();
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cuenta/{email}")
    public Response enviarCuenta(@PathParam("email" )String email) throws SQLException, ClassNotFoundException, FileNotFoundException {
        try {
            if (email == null) {
                return Response.status(400).entity("Incorrect parameters.").build();
            } else {
                Users usuarioRecibido = new Users(usersService.findByEmail(email).getName(),
                        usersService.findByEmail(email).getEmail(),usersService.findByEmail(email).getPassword());
                String content = "Nombre de usuario: " + usuarioRecibido.getName() + "\n" + "Contraseña: " + usuarioRecibido.getPassword();

                PdfCreator pdfCreator = new PdfCreator();
                pdfCreator.createPdf("datosusuario",content);


                Sender sender = new Sender();

                sender.send("proyectodualpasapalabra@gmail.com", email, "Recuperación de cuenta", content );
                return Response.ok(usersService.findByEmail(email)).build();

            }
        } catch (SQLException | ClassNotFoundException | DocumentException | IOException | URISyntaxException e){
            return Response.status(500).entity("Internal Error during DB interaction.").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createuser")
    public Response createUser(Users user) {
        try {

            if (usersService.createUser(user)) {
                return Response.status(201).entity(usersService.findByName(user.getName())).build();
            } else {
                return Response.status(500).entity("Internal Error During Creating The User").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/validateuser")
    public Response validateUser(Users users) {
        try {

            if (usersService.validateUser(users.getName(),users.getPassword())) {
                return Response.status(200).build();
            } else {
                return Response.status(401).build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

}
