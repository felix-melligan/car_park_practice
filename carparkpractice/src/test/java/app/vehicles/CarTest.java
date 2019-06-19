package app.vehicles;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.Ticket;

import static org.junit.Assert.*;

public class CarTest {
    private Car c;
    private String reg = "Car";

    @Before
    public void setUp() {
        c = new Car(reg);
    }

    @Test
    public void setTicketSetsTicket() {
        Assert.assertNull(c.getTicket());

        Ticket t = new Ticket();
        c.setTicket(t);

        Assert.assertEquals(t, c.getTicket());
    }

    @Test
    public void getCurrentLevelShowsLevel() {
    }

    @Test
    public void getReg() {
    }

    @Test
    public void setSpace() {
    }
}