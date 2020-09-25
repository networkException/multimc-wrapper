package de.nwex.multimc;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import de.nwex.multimc.annotations.Path;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import org.reflections.Reflections;

public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(20240), 0);

        new Reflections("de.nwex.multimc.paths")
            .getTypesAnnotatedWith(Path.class)
            .forEach(path -> {
                try {
                    String url = path.getAnnotation(Path.class).value();
                    server.createContext(url, (HttpHandler) path.getConstructors()[0].newInstance());
                    System.out.printf("Path '%s' initialised at '%s'", path.getName(), url);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    System.out.printf("Path '%s' could not be initialized", path.getName());
                }
            });

        server.setExecutor(null);
        server.start();
    }
}
