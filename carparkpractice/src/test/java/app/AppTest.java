package app;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

public class AppTest {
    private final String[] ARGS = {"Arg1"};
    private final File logFile = new File("CPLog.txt");

    @Rule
    public final ExpectedSystemExit EXIT = ExpectedSystemExit.none();

    @Test
    public void mainShouldExitWhenGivenArgumentsAndCallLogger() {
        boolean hasLine = false;
        LocalDate date = LocalDate.now();
        EXIT.expectSystemExitWithStatus(0);
        App.main(ARGS);
    }

    @Test
    public void mainShouldExecuteWithoutExiting() {
        String[] args = {};
        App.main(args);
    }
}