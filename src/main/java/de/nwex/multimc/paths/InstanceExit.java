package de.nwex.multimc.paths;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.nwex.multimc.Main;
import de.nwex.multimc.annotations.Path;
import de.nwex.multimc.util.HandlerUtil;
import de.nwex.multimc.util.UriUtil;
import java.io.IOException;

@Path("/launch")
public class InstanceLaunch implements HttpHandler {

    @Override public void handle(HttpExchange exchange) throws IOException {
        if (!HandlerUtil.isLocal(exchange)) {
            HandlerUtil.send(exchange, 403, "Only local requests supported");
            exchange.close();
            return;
        }

        String name = UriUtil.splitQuery(exchange.getRequestURI()).getOrDefault("name", "undefined");
        String id = UriUtil.splitQuery(exchange.getRequestURI()).getOrDefault("id", "undefined");

        System.out.printf("Playing %s (%s)", name, id);

        Main.getPresence().clear();
        Main.getPresence().update(String.format("Playing %s (%s)", name, id));
        HandlerUtil.send(exchange, "Ok");
    }
}
