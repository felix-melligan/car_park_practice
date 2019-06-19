package app.car_park.machines;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import utils.Ticket;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PayPointTest {
    private PayPoint pp;
    private static final Ticket TICKET = Mockito.mock(
            Ticket.class,
            Mockito.withSettings().useConstructor().defaultAnswer(Mockito.CALLS_REAL_METHODS)
            );

    @Before
    public void setUp() {
        pp = new PayPoint();
    }

    @Test
    public void payMethodChangesTicketPaidVariableFromFalseToTrue() {
        assertFalse(TICKET.getPaid());
        pp.pay(TICKET);

        assertTrue(TICKET.getPaid());
    }

    @Test
    public void payPointGivesAmountOwed() {
        assertNotNull(pp.getTicketPrice(TICKET));
    }

    @Test
    public void amountOwedIs_0_IfTimeSinceTicketInstantiated_LessThan30MinsAgo() {
        assertTrue(TICKET.getEntryDateTime().isAfter(LocalDateTime.now().minusMinutes(30)) && TICKET.getEntryDateTime().isBefore(LocalDateTime.now()));
        assertEquals(0.0, pp.getTicketPrice(TICKET), 0);
    }

    @Test
    public void amountOwedIs_1_IfTimeOfEntryIs_Over30MinsAgo_But_LessThan_1HourAgo() {
        Field privateEntryDateTimeField = null;
        try {
            privateEntryDateTimeField = Ticket.class.
                    getDeclaredField("entryDateTime");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        privateEntryDateTimeField.set();
        assertTrue(TICKET.getEntryDateTime().isAfter(LocalDateTime.now().minusHours(1)) && TICKET.getEntryDateTime().isBefore(LocalDateTime.now().minusMinutes(30)));
        assertEquals(1.0, pp.getTicketPrice(TICKET), 0);
    }
}