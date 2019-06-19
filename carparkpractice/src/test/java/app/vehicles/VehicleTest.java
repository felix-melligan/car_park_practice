package app.vehicles;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import utils.Ticket;

@RunWith(PowerMockRunner.class)
@PrepareForTest(A.class)

public class VehicleTest {
    private Vehicle v;
    private final static String REG = "reg";

    @Before
    public void setUp() {
        v = Mockito.make
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