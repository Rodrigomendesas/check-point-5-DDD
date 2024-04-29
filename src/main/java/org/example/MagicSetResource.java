package org.example;

import entities.MagicSet;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;import repositories.impl.MagicSetImpl;

import java.util.List;

@Path("/sets")
public class MagicSetResource {
    private MagicSetImpl setRepository = new MagicSetImpl();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(MagicSet set) {
        setRepository.Create(set);
        return Response.status(Response.Status.CREATED).entity(set).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MagicSet read(@PathParam("id") int id) {
        return setRepository.Read(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MagicSet> readAll() {
        return setRepository.ReadAll();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(MagicSet set) {
        setRepository.Update(set);
        return Response.status(Response.Status.OK).entity(set).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        setRepository.Delete(id);
        return Response.status(Response.Status.OK).build();
    }
}
