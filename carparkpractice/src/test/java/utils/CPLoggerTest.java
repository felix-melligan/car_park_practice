package utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static app.App.LOGGER;

public class CPLoggerTest {
    private final File file = new File("Logs/CPLog.txt");

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
}