package gmit.chatathon;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;

/**
 *
 * sendMessage (Web Socket)
 * sendPing
 * receivePong
 *
 *
 * Created by phil on 4/7/2017.
 */
public class YapServer {


    /**
     * Low level Web Socket Message sender.
     * @param client
     * @param frame
     */
    public static void sendMessage(Session client, YapFrame frame) {
        try {
            client.getRemote().sendBytes(frame.serialize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doData(String message, YapFrame sourceFrame) {
        YapFrame out = new YapFrame.Builder(YapCommand.ERROR)
                .header("message", message)

                .build();

    }
}
