package gmit.chatathon.model;

/**
 * Created by phil on 4/2/2017.
 */


import org.eclipse.jetty.websocket.api.Session;

/**
 * User
 * id
 * username (LDAP/other)
 * email
 * nick

 * is-identified (username checks out)
 * is-robot
 * robot-pass-key
 * is-validated (email address)
 * language code
 */

public class ChatUser {

    private String nick;
    private final String username;
    private final String email;
    private final int id;

    static int nextUserID = 1;
    private String languageCode; // 2 character ISO 639-1
    private Session wsSession;


    public static class Builder {
        private final String username;
        private final String email;
        private final int id;
        private String languageCode = "en";
        private Session wsSession;
        private String nick;

        public Builder(String username, String email) {
            this.email = email;
            this.username = username;
            this.id = ChatUser.nextUserID++;
        }

        public Builder nick(String nick) {
            this.nick = nick;
            return this;
        }
        public Builder languageCode(String languageCode) {
            this.languageCode = languageCode;
            return this;        }
        public Builder wsSession(Session wsSession){
            this.wsSession = wsSession;
            return this;
        }
    }

    private ChatUser(Builder builder) {
        username = builder.username;
        email = builder.email;
        nick = builder.nick;
        languageCode = builder.languageCode;
        wsSession = builder.wsSession;
        id = builder.id;

    }

    public int getId() { return id; }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public Session getWsSession() {
        return wsSession;
    }

    public void setWsSession(Session wsSession) {
        this.wsSession = wsSession;
    }

    @Override
    public String toString() {
        return String.format("ChatUser[%i] nick=%s,email=%s,username=%s,realname=%s,lang=%s, websocket=%s",
                getId(),  getNick(), getEmail(), getUsername(), getLanguageCode(), getWsSession().hashCode());
    }


}
