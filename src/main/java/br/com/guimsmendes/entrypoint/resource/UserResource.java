package br.com.guimsmendes.entrypoint.resource;

import br.com.guimsmendes.core.UserUseCase;
import br.com.guimsmendes.entrypoint.model.UserRequest;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    @Inject
    UserUseCase userUseCase;

    @POST
    @PermitAll
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void insert(UserRequest userRequest) {
        userUseCase.addUser(userRequest);
    }

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.status(Response.Status.FOUND)
                .entity(userUseCase.listAllUsers()).build();
    }

}
