package de.nwex.multimc;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import de.nwex.multimc.annotations.Path;
import de.nwex.multimc.discord.Presence;
import de.nwex.multimc.voxelmap.WaypointFormatter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;
import org.reflections.Reflections;

public class Main {

    private static Presence presence;

    public static void main(String[] args) throws IOException {
        if(args.length == 1) {
            java.nio.file.Path filePath = java.nio.file.Path.of(args[0]);

            Files.write(filePath, WaypointFormatter.format(Files.lines(filePath).collect(Collectors.toList())), StandardCharsets.UTF_8);

            return;
        }

        HttpServer server = HttpServer.create(new InetSocketAddress(20240), 0);

        new Reflections("de.nwex.multimc.paths")
            .getTypesAnnotatedWith(Path.class)
            .forEach(path -> {
                try {
                    String url = path.getAnnotation(Path.class).value();
                    server.createContext(url, (HttpHandler) path.getConstructors()[0].newInstance());
                    System.out.printf("Path '%s' initialised at '%s'\n", path.getName(), url);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    System.out.printf("Path '%s' could not be initialized\n", path.getName());
                }
            });

        server.setExecutor(null);
        server.start();

        presence = new Presence();
    }

    public static Presence getPresence() {
        return presence;
    }
}
