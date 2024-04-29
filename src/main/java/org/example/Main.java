package org.example;

import entities.MagicCard;
import entities.MagicSet;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import repositories.impl.MagicCardImpl;
import repositories.impl.MagicSetImpl;

import java.io.IOException;
import java.net.URI;


/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/api";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in org.example package
        final ResourceConfig rc = new ResourceConfig().packages("org.example");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        MagicCardImpl magicCardImplementation = new MagicCardImpl();
        MagicSetImpl magicSetImplementation = new MagicSetImpl();

        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.stop();

        MagicSet mySet = new MagicSet(1, "A New Set", 5, "v0.102", "11/09/1997");
        MagicSet mySetUpdated = new MagicSet(1, "The Set", 6, "v0.110", "12/11/2002");
        MagicCard myCard = new MagicCard(2, "Horde Breaker", "uri", 2, "Fighter", "The Classical Lazy Peon has gained new skin", "Red", mySet);
        MagicCard myCardUpdated = new MagicCard(2, "Horde Defender", "uri", 3, "Fighter", "The counterattack is upon us", "Red", mySetUpdated);

        System.out.println(mySet);
        System.out.println(myCard);

        magicSetImplementation.Create(mySet);
        magicCardImplementation.Create(myCard);

        System.out.println(magicCardImplementation.Read(2));
        System.out.println(magicSetImplementation.Read(1));

        System.out.println(magicCardImplementation.ReadAll());
        System.out.println(magicSetImplementation.ReadAll());

        magicSetImplementation.Update(mySetUpdated);
        magicCardImplementation.Update(myCardUpdated);
    }
}

