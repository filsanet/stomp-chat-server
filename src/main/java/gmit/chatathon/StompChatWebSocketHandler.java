package gmit.chatathon;

import org.eclipse.jetty.websocket.api.annotations.*;

import org.eclipse.jetty.websocket.api.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by phil on 4/2/2017.
 */
@WebSocket
public class StompChatWebSocketHandler {

    public static final Logger logger = LoggerFactory.getLogger(StompChatWebSocketHandler.class);

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {

        //StompChatServer.nickUserMap.put(nick, user);
        StompChatServer.sendMessageToDebugChannel( "New connection from session" + user.hashCode() );
        logger.info("New connection from session: %s~%s.", user.hashCode(), user.getRemote().toString());
    }


    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {

    }

    @OnWebSocketError
    public void onError(Session user, Throwable cause) {

    }

    /**
     * See https://github.com/mrstampy/Stampy/blob/master/examples/asia/stampy/examples/system/server/SystemServer.java
     *
     * @param user
     * @param message
     */
    @OnWebSocketMessage
    public void onMessage(Session user, String message) {

    }
}
