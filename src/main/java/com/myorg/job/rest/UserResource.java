package com.myorg.job.rest;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.myorg.job.dao.UserDao;
import com.myorg.job.model.User;
import com.myorg.job.model.Users;

/**
 * User REST resource to provide the basic CRUD operations via REST
 *
 * @author vg
 * @since Feb 2021
 */
@Path("users")
public class UserResource {

    private UserDao userDaoService;

    @Inject
    UserResource(final UserDao userService) {
        this.userDaoService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        Users hellos = new Users();
        hellos.setUserList(this.userDaoService.getAll());
        return Response.ok(hellos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        int userId = this.userDaoService.add(user);
        return Response.created(URI.create("/api/v1/user/" + userId)).build();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("userId") int userId) {
        User user = this.userDaoService.getById(userId);
        if (user != null) {
            return Response.ok(user).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHello(@PathParam("userId") int userId, User user) {
        final User updateHello = new User();
        updateHello.setUserId(userId);
        updateHello.setUserEmail(user.getUserEmail());

        boolean success = userDaoService.update(updateHello);
        if (success) {
            return Response.ok(this.userDaoService.getById(userId)).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteHello(@PathParam("helloId") int helloId) {
        boolean success = userDaoService.delete(helloId);
        if (success) {
            return Response.noContent().build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
