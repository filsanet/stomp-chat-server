package gmit.chatathon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by phil on 4/6/2017.
 */
public class YapFrame {

    public static final Logger logger = LoggerFactory.getLogger(YapFrame.class);

    private final char NULL_OCTET = '\u0000';
    private final char LINE_FEED = '\n';

    private final YapCommand command;
    private final String body;
    private final Map<String, String> headers;

    private YapFrame(Builder builder) {
        command = builder.command;
        body = builder.body;
        headers = builder.headers;
    }

    @Override
    public String toString() {
        return String.format("YapFrame[%s] %s~%s", this.hashCode(), command.toString(), this.body);
    }

    public ByteBuffer serialize() {
        StringBuffer out = new StringBuffer();
        out.append(command.toString());
        out.append(LINE_FEED);

        if (!headers.isEmpty()) {
            out.append( headers.entrySet().stream()
                    .map(e -> e.getKey() + ":" + e.getValue() + LINE_FEED)
                    .reduce("", (a, b) -> a + b)
            );
        }
        out.append(LINE_FEED);
        if (body.length() > 0) { out.append(body); }
        out.append(NULL_OCTET);

        return ByteBuffer.wrap(out.toString().getBytes(StandardCharsets.UTF_8));
    }

    public static YapFrame parse(String message) {

        String commandString, headersString;

        List<String> lines = Arrays.asList(message.split("\n\n", 2));
        logger.debug("lines length = " + lines.size() );

        String messageHeaders = lines.get(0);
        String messageBody = lines.get(1);

        commandString = messageHeaders.split("\n", 2)[0];
        headersString = messageHeaders.split("\n", 2)[1];

        logger.debug ("== commandString=" + commandString + " commandString.length=" + commandString.length());

        YapCommand command = YapCommand.valueOf(commandString);

        Builder b = new YapFrame.Builder(command);
        for (String s : Arrays.asList(headersString.split("\n", -1))) {
            logger.debug ("HeadersString Loop: s=" + s);
            String[] keyVal = s.split(":");
            b = b.header(keyVal[0], keyVal[1]);
        }
        if (!messageBody.isEmpty()) {
            b = b.body(messageBody);
        }
        return b.build();
    }

    public YapCommand getCommand() {
        return this.command;
    }

    public String getBody() {
        return this.body;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public static class Builder {
        private final YapCommand command;
        private HashMap<String, String> headers = new HashMap<String, String>();
        private String body;

        public Builder(YapCommand command) {
            this.command = command;
        }

        public Builder header(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public YapFrame build() {
            return new YapFrame(this);
        }

    }
}
