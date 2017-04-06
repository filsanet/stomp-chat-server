package gmit.chatathon.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by phil on 4/7/2017.
 */
public class Channel {
    private String name;
    private String topic;
    private HashSet<String> currentUsers = (HashSet<String>) Collections.EMPTY_SET;

    public Channel(String name, String topic) {
        this.name = name;
        this.topic = topic;
    }

    public void addUser(String nick) {
        this.currentUsers.add(nick);
    }

    public void removeUser(String nick) {
        this.currentUsers.remove(nick);
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
}
