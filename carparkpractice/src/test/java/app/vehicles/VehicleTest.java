package app.vehicles;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import utils.Ticket;

import static org.junit.Assert.assertEquals;

public class VehicleTest {
    private Vehicle v;
    private final static String REG = "reg";

    @Before
    public void setUp() {
        v = Mockito.mock(
                Vehicle.class,
                Mockito.withSettings().useConstructor(REG).defaultAnswer(Mockito.CALLS_REAL_METHODS));
    }

    @Test
    public void setTicketSetsTicketAndGetTicketWorks() {
        Ticket t = new Ticket();
        v.setTicket(t);

        assertEquals(t, v.getTicket());
    }

    @Test
    public void constructorSetsLevelTo0() {
        assertEquals(0, v.getCurrentLevel());
    }

    @Test
    public void constructorSetsReg() {
        assertEquals(REG, v.getReg());
    }
}