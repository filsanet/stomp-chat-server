package gmit.chatathon;


import org.eclipse.jetty.websocket.api.Session;

/**
 * Created by phil on 4/6/2017.
 */
public enum YapCommand implements YapCommandProcessor {
    CONNECT {
        public void process(YapFrame frame, Session client) {
            // check headers
            // validate/authenticate users
            // return list of channels

        }
    },
    DISCONNECT {
        public void process(YapFrame frame, Session client) {

        }

    },
    JOIN {
        public void process(YapFrame frame, Session client) {

        }

    },
    LEAVE {
        public void process(YapFrame frame, Session client) {

        }

    },
    SEND {
        public void process(YapFrame frame, Session client) {

        }

    },
    PING {
        public void process(YapFrame frame, Session client) {

        }

    },
    MESSAGE{
        public void process(YapFrame frame, Session client) {

        }

    },
    DATA {
        public void process(YapFrame frame, Session client) {

        }

    },
    ERROR {
        public void process(YapFrame frame, Session client) {

        }

    },
    PONG {
        public void process(YapFrame frame, Session client) {

        }

    }
}

