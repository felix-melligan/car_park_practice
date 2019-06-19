package app.car_park.machines;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MachineTest {
    private Machine m;

    @Before
    public void setUp() {
        m = Mockito.mock(
                Machine.class,
                Mockito.CALLS_REAL_METHODS);
    }

    @Test
    public void machineClassHasIncrementingId() {
        Machine m2 = Mockito.mock(
                Machine.class,
                Mockito.CALLS_REAL_METHODS);
        assertEquals(0, m.getId());
        assertEquals(1, m2.getId());
    }
}