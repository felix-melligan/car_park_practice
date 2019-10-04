package app.vehicles;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MotorcycleTest {
    private static final String REG = "reg";
    private Motorcycle m;

    @Before
    public void setUp() {
        m = new Motorcycle(REG);
    }

    @Test
    public void constructorSetsReg() {
        assertEquals(REG, m.getReg());
    }

    @Test
    public void carInstanceOfVehicle() {
        assertTrue(m instanceof Vehicle);
    }
}