package utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;

import static app.App.LOGGER;

public class CPLoggerTest {
    private final File file = new File("logs/CPLog.txt");

    public boolean stringInLogFile(String stringToFind) {
        boolean found = false;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(stringToFind)) {
                    found = true;
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return found;
    }

    @Test
    public void setupCreatesLoggerFile() {
        Assert.assertTrue(file.exists());
    }

    @Test
    public void logsToFileAsSevereWhenCalled() {
        String message = "Severe";
        LOGGER.setLevel(Level.INFO);
        LOGGER.severe(message);
        Assert.assertTrue(stringInLogFile(message));
    }

    @Test
    public void logsToFileAsWarningWhenCalled() {
        String message = "Warning";
        LOGGER.setLevel(Level.INFO);
        LOGGER.warning(message);
        Assert.assertTrue(stringInLogFile(message));
    }

    @Test
    public void logsToFileAsInfoWhenCalled() {
        String message = "Info";
        LOGGER.setLevel(Level.INFO);
        LOGGER.info(message);
        Assert.assertTrue(stringInLogFile(message));
    }

    @Test
    public void doesNotLogAsFinestWhenCalled() {
        String message = "Finest";
        LOGGER.setLevel(Level.INFO);
        LOGGER.finest(message);
        Assert.assertFalse(stringInLogFile(message));
    }
}