package gmit.chatathon.model;

import org.eclipse.jetty.websocket.api.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by phil on 4/7/2017.
 */
public class Channel {
    private String name;
    private String topic;
    private HashSet<Session> currentUsers = (HashSet<Session>) Collections.EMPTY_SET;

    public Channel(String name, String topic) {
        this.name = name;
        this.topic = topic;
    }

    public void addUser(Session client) {
        this.currentUsers.add(client);
    }

    public void removeUser(String client) {
        this.currentUsers.remove(client);
    }


    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int userCount() {
        return this.currentUsers.size();
    }

    public boolean hasUser(Session client) {
        return this.currentUsers.contains(client);
    }
}
