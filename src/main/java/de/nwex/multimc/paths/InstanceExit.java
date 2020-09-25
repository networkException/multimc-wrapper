package de.nwex.multimc.paths;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.nwex.multimc.Main;
import de.nwex.multimc.annotations.Path;
import de.nwex.multimc.util.HandlerUtil;
import java.io.IOException;

@Path("/exit")
public class InstanceExit implements HttpHandler {

    @Override public void handle(HttpExchange exchange) throws IOException {
        if (!HandlerUtil.isLocal(exchange)) {
            HandlerUtil.send(exchange, 403, "Only local requests supported");
            exchange.close();
            return;
        }

        Main.getPresence().clear();
        HandlerUtil.send(exchange, "Ok");
    }
}
