package gmit.chatathon;

import org.eclipse.jetty.websocket.api.Session;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static spark.Spark.*;

/**
 * Created by phil on 4/2/2017.
 */
public class StompChatServer {


    static Map<String, ChatUser> nickUserMap = new ConcurrentHashMap<>();
    static Map<String, HashSet<String>> channelUserMap = new ConcurrentHashMap<>();
    static Map<String, String> channelTopicMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        staticFiles.location("/public");
        staticFiles.expireTime(600);

        webSocketIdleTimeoutMillis(600000);
        webSocket("/chat", StompChatWebSocketHandler.class);
        init();

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
        StompChatServer.sendMessageToChannel("debug", "sysop", message);
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
}
