package app.vehicles;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CarTest {
    private static final String REG = "reg";
    private Car c;

    @Before
    public void setUp() {
        c = new Car(REG);
    }

    @Test
    public void constructorSetsReg() {
        assertEquals(REG, c.getReg());
    }

    @Test
    public void carInstanceOfVehicle() {
        assertTrue(c instanceof Vehicle);
    }
}