package gmit.chatathon;

import gmit.chatathon.model.ChatUser;
import org.eclipse.jetty.websocket.api.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static spark.Spark.*;

/**
 * Created by phil on 4/2/2017.
 */
public class YapChatServer {

    public static final Logger logger = LoggerFactory.getLogger(YapChatServer.class);

    static Map<String, ChatUser> nickUserMap = new ConcurrentHashMap<>();
    static Map<String, HashSet<String>> channelUserMap = new ConcurrentHashMap<>();
    static Map<String, String> channelTopicMap = new ConcurrentHashMap<>();

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
        nickUserMap.values().stream().filter(s -> s.getWsSession().isOpen()).forEach(session -> {
            // send message
            // log
        });
    }

    public static void sendMessageToChannel(String channel, String sender, String message) {
        nickUserMap.entrySet().stream()
                .filter(entry -> channelUserMap.get(channel).contains(entry.getKey()))
                .map(entry -> entry.getValue().getWsSession())
                .filter(Session::isOpen)
                .forEach(session -> {
            // send message
            // log
        });

    }

    public static void sendMessageToDebugChannel(String message) {
        YapChatServer.sendMessageToChannel("debug", "sysop", message);
    }

    public static void sendMessageToUser(String user, String sender, String message) {

    }

    public static void joinChannel(String channel, String user) {
        if (!channelUserMap.containsKey(channel)) {
            channelUserMap.put(channel, new HashSet<String>(Collections.EMPTY_SET));
        }
        channelUserMap.get(channel).add(user);
    }

    public static void leaveChannel(String channel, String user) {
        if (channelUserMap.containsKey(channel)) {
            channelUserMap.get(channel).remove(user);
        }
    }

    public static void setChannelTopic(String channel, String topic) {
        if (channelTopicMap.containsKey(channel) != true) {
            channelTopicMap.put(channel, topic);
        } else {
            channelTopicMap.replace(channel, topic);
        }
    }

    public static void removeUser(Session user) {
        // remove Session & nick from all collections.
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

    public static void processConnection(Session client, YapFrame frame) {
    }
}
