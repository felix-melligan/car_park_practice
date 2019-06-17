package app;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class AppTest {
    private App app;
    @Rule
    public final ExpectedSystemExit EXIT = ExpectedSystemExit.none();

    @Before
    public void setUp() {
        app = new App();
    }

    @Test
    public void mainShouldExitWhenGivenArgumentsAndCallLogger() {
        String[] args = {"Args"};
        EXIT.expectSystemExitWithStatus(0);
        app.initialiseApp(args);
    }

    @Test
    public void mainShouldExecuteWithoutExiting() {
        String[] args = {};
        app.initialiseApp(args);
    }
}