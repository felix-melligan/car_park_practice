package utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class CPLoggerTest {
    private final File file = new File("CPLog.txt");

    @Test
    public void setupCreatesLoggerFile() {
        try {
            CPLogger.setup();
            Assert.assertTrue(file.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}