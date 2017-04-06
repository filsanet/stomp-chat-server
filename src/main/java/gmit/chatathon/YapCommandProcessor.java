package gmit.chatathon;

import org.eclipse.jetty.websocket.api.Session;

public interface YapCommandProcessor {

    public abstract void process(YapFrame frame, Session client);
}
