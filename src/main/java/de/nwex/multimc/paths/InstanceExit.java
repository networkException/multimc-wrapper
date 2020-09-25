package de.nwex.multimc.paths;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.nwex.multimc.Main;
import de.nwex.multimc.annotations.Path;
import de.nwex.multimc.util.HandlerUtil;
import de.nwex.multimc.util.UriUtil;
import de.nwex.multimc.voxelmap.WaypointFormatter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;

@Path("/exit")
public class InstanceExit implements HttpHandler {

    @Override public void handle(HttpExchange exchange) throws IOException {
        if (!HandlerUtil.isLocal(exchange)) {
            HandlerUtil.send(exchange, 403, "Only local requests supported");
            exchange.close();
            return;
        }

        String name = UriUtil.splitQuery(exchange.getRequestURI()).getOrDefault("name", "undefined");
        if(name.contains("purevanilla")) {
            new Thread(() -> {
                try {
                    java.nio.file.Path filePath = java.nio.file.Path.of("/home/networkexception/common/minecraft/instances/Shared/mamiyaotaru/voxelmap/play.purevanilla.es.points");

                    Files.write(filePath, WaypointFormatter.format(Files.lines(filePath).collect(Collectors.toList())), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Main.getPresence().clear();
        HandlerUtil.send(exchange, "Ok");
    }
}
