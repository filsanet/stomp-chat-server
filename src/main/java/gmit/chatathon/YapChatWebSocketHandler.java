package gmit.chatathon;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by phil on 4/6/2017.
 */
@WebSocket
public class YapChatWebSocketHandler {

    public static final Logger logger = LoggerFactory.getLogger(YapChatWebSocketHandler.class);

    @OnWebSocketConnect
    public void onConnect(Session client) {
        logger.info ("WebSocket client connected: {}~{}", client.hashCode(), client.getRemoteAddress().toString());
    }

    @OnWebSocketMessage
    public void onMessage(Session client, String message) {
        YapChatServer.echo(client, message);
        YapFrame frame = YapFrame.parse(message);

        frame.getCommand().process(frame, client);
        logger.debug("Processing command {}", frame.getCommand().toString());
    }

    @OnWebSocketClose
    public void onClose(Session client, int statusCode, String reason) {
        logger.info("Session closed. [{}~{}] {} {}", client.hashCode(), client.getRemoteAddress().toString(),
                statusCode, reason);

    }

    @OnWebSocketError
    public void onError(Session client, Throwable error) {
        logger.info("Session error: [%i~%s] %s", client.hashCode(), client.getRemoteAddress().toString(), error.getMessage());

    }

}
