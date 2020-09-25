package de.nwex.multimc.paths;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.nwex.multimc.annotations.Path;
import java.io.IOException;

@Path("/launch")
public class InstanceLaunch implements HttpHandler {

    @Override public void handle(HttpExchange exchange) throws IOException {

    }
}
