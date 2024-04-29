package org.example;

import entities.MagicCard;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repositories.impl.MagicCardImpl;

import java.util.List;
@Path("/cards")
public class MagicCardResource {

        private MagicCardImpl cardRepository = new MagicCardImpl();

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public Response create(MagicCard card) {
            cardRepository.Create(card);
            return Response.status(Response.Status.CREATED).entity(card).build();
        }

        @GET
        @Path("{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public MagicCard read(@PathParam("id") int id) {
            return cardRepository.Read(id);
        }

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<MagicCard> readAll() {
            return cardRepository.ReadAll();
        }

        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        public Response update(MagicCard card) {
            cardRepository.Update(card);
            return Response.status(Response.Status.OK).entity(card).build();
        }

        @DELETE
        @Path("{id}")
        public Response delete(@PathParam("id") int id) {
            cardRepository.Delete(id);
            return Response.status(Response.Status.OK).build();
        }
}

