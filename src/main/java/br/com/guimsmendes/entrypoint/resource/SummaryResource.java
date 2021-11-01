package br.com.guimsmendes.entrypoint.resource;

import br.com.guimsmendes.entrypoint.model.SummaryTagProducerRequest;
import br.com.guimsmendes.core.SummaryUseCase;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.FOUND;

@Path("/summary")
public class SummaryResource {

    @Inject
    SummaryUseCase summaryService;

    @POST
    @Path("/tag-producer")
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateSummaryTag(@Valid SummaryTagProducerRequest summaryTagProducerRequest) {
        var tag = summaryService.createTag(summaryTagProducerRequest);
        return Response.status(CREATED).entity(tag).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin","user"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSummary(@PathParam("id") String id) {
        var bucketsSummary = summaryService.getBucketsSummary(id);
        return Response.status(FOUND).entity(bucketsSummary).build();
    }
}