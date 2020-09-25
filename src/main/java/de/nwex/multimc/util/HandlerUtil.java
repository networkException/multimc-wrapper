package de.nwex.multimc.util;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

public class HandlerUtil {
    public static boolean isLocal(HttpExchange exchange) {
        return exchange.getRemoteAddress().getHostString().equals("127.0.0.1");
    }

    public static void send(HttpExchange exchange, String message) throws IOException {
        send(exchange, 200, message);
    }

    public static void send(HttpExchange exchange, int code, String message) throws IOException {
        exchange.sendResponseHeaders(code, message.length());
        OutputStream os = exchange.getResponseBody();
        os.write(message.getBytes());
        os.close();
    }
}
