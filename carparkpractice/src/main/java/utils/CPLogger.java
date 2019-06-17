package utils;

import java.io.IOException;
import java.util.logging.*;

public class CPLogger {
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
    private static boolean isUp;

    static public void setup() throws IOException {
//        check if already initialised
        if (isUp) return;

//        Get global logger and configure it
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

//        Suppress logging to console
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers.length < 1) {
            throw new RuntimeException("Logger cannot initialise, rootLogger does not contain ConsoleHandler");
        }
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        logger.setLevel(Level.INFO);
        fileTxt = new FileHandler("Logs/CPLog.txt");

//        Create the txt formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

        isUp = true;
    }
}
