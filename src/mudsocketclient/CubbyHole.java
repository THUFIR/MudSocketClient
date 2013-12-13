package mudsocketclient;

import java.util.logging.Logger;

public class CubbyHole {

    private static Logger log = Logger.getLogger(CubbyHole.class.getName());
    private String message = "some message";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        log.fine(message);
    }

    public String toString() {
        return message;
    }
}
