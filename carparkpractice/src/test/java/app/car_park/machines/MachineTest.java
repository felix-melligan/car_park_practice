package app.car_park.machines;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import utils.State;

import static org.junit.Assert.assertEquals;

public class MachineTest {
    private Machine m;

    @Before
    public void setUp() {
        m = Mockito.mock(
                Machine.class,
                Mockito.withSettings().useConstructor().defaultAnswer(Mockito.CALLS_REAL_METHODS));
    }

    @Test
    public void machineClassHasIncrementingId() {
        Machine m2 = Mockito.mock(
                Machine.class,
                Mockito.withSettings().useConstructor().defaultAnswer(Mockito.CALLS_REAL_METHODS));
        int mId = m.getId();
        assertEquals(++mId, m2.getId());
    }

    @Test
    public void hasEnumContainingStateSetToOFFAfterConstructor() {
        assertEquals(State.OFF, m.getState());
    }

    @Test
    public void canSwitchMachineOnIfOFF() {
        assertEquals(State.OFF, m.getState());

        m.switchOn();

        assertEquals(State.ON, m.getState());
    }

    @Test
    public void switchOnDoesNothingIfStateAlreadyON() {
        m.switchOn();
        assertEquals(State.ON, m.getState());

        m.switchOn();
        assertEquals(State.ON, m.getState());
    }

    @Test
    public void canSwitchStateToOFFIfON() {
        m.switchOn();
        assertEquals(State.ON, m.getState());

        m.switchOff();
        assertEquals(State.OFF, m.getState());
    }

    @Test
    public void switchOffDoesNothingIfStateAlreadyOFF() {
        assertEquals(State.OFF, m.getState());

        m.switchOff();
        assertEquals(State.OFF, m.getState());
    }
}