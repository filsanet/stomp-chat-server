package gmit.chatathon;

import gmit.chatathon.model.Channel;
import gmit.chatathon.model.ChatUser;
import org.eclipse.jetty.websocket.api.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static spark.Spark.*;

/**
 * Created by phil on 4/2/2017.
 */
public class YapChatApp {

    public static final Logger logger = LoggerFactory.getLogger(YapChatApp.class);

    static Map<Session, ChatUser> sessionUserMap = new ConcurrentHashMap<>();
    static Map<String, Channel> channelMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        setupStaticFiles();

        webSocketIdleTimeoutMillis(600000);
        webSocket("/chat", YapChatWebSocketHandler.class);
        init();

    }

    private static void setupStaticFiles() {

        logger.debug("property deploy.env="+ System.getProperty("deploy.env"));

        if ("dev".equalsIgnoreCase(System.getProperty("deploy.env"))) {

            logger.warn("Using external folder for static files");
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/src/main/resources/public";
            staticFiles.externalLocation(projectDir + staticDir);


        } else {
            staticFiles.location("/public");

        }
        staticFiles.expireTime(600);
    }

    public static void broadcastMessage(String sender, String message) {
        sessionUserMap.values().stream().filter(s -> s.getWsSession().isOpen()).forEach(session -> {
            // send message
            // log
        });
    }

    public static void sendMessageToChannel(String channel, String sender, String message) {
 /*       sessionUserMap.entrySet().stream()
                .filter(entry -> channelMap.get(channel).contains(entry.getKey()))
                .map(entry -> entry.getValue().getWsSession())
                .filter(Session::isOpen)
                .forEach(session -> {
            // send message
            // log
        });*/

    }

    public static void sendMessageToDebugChannel(String message) {
        YapChatApp.sendMessageToChannel("debug", "sysop", message);
    }

    public static void sendMessageToUser(String user, String sender, String message) {

    }

    public static void joinChannel(String channel, String user) {
    }

    public static void leaveChannel(String channel, String user) {
    }

    public static void setChannelTopic(String channel, String topic) {
    }

    public static void removeUser(Session user) {
    }

    public static void echo(Session client, String message) {
        try {
            client.getRemote().sendString("ECHO: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("--------------------");
        logger.debug(client.getRemote().toString());
        logger.debug(message);
    }

}
