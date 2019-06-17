package app;

import utils.CPLogger;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
//    User classname for logger in case of refactor
    public final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) {
        App app = new App();
        app.initialiseApp(args);
    }

    public void initialiseApp(String[] args) {
        try {
            CPLogger.setup();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException("Problem making log file");
        }
        if (args.length > 0) {
            LOGGER.setLevel(Level.INFO);
            LOGGER.severe("Arguments found, System.exit called with status 0");
            System.exit(0);
        }
    }
}
