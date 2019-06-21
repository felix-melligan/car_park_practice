package app.car_park.machines;

import app.car_park.CarPark;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import utils.Ticket;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PayPointTest {
    private PayPoint pp;
    private Ticket ticket;

    @Before
    public void setUp() {
        pp = new PayPoint(new CarPark());
        ticket = Mockito.mock(
                Ticket.class,
                Mockito.withSettings().useConstructor().defaultAnswer(Mockito.CALLS_REAL_METHODS)
        );
    }

    private void changePrivateDateTimeVariable(int days, int hours, int minutes) {
        Field privateEntryDateTimeField = null;
        String privateMethod = "entryDateTime";
        try {
            privateEntryDateTimeField = Ticket.class.
                    getDeclaredField(privateMethod);
            privateEntryDateTimeField.setAccessible(true);
            privateEntryDateTimeField.set(ticket, LocalDateTime.now().minusDays(days).minusHours(hours).minusMinutes(minutes));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Could not find field "+privateMethod);
        }
    }

    @Test
    public void payMethodChangesTicketPaidVariableFromFalseToTrue() {
        assertFalse(ticket.getPaid());
        pp.pay(ticket);

        assertTrue(ticket.getPaid());
    }

    @Test
    public void payPointGivesAmountOwed() {
        assertNotNull(pp.getTicketPrice(ticket));
    }

    @Test
    public void amountOwedIs_0_IfTimeSinceTicketInstantiated_LessThan30MinsAgo() {
        assertTrue(ticket.getEntryDateTime().isAfter(LocalDateTime.now().minusMinutes(30)) && ticket.getEntryDateTime().isBefore(LocalDateTime.now()));
        assertEquals(0.0, pp.getTicketPrice(ticket), 0);
    }

    @Test
    public void amountOwedIs_1_IfTimeOfEntryIs_Over1HourAgo_But_LessThan_2HoursAgo() {
        changePrivateDateTimeVariable(0, 1, 0);
        assertTrue(ticket.getEntryDateTime().isAfter(LocalDateTime.now().minusHours(1).minusMinutes(30)));
        assertEquals(1.0, pp.getTicketPrice(ticket), 0);
    }

    @Test
    public void amountOwedIs_2_IfTimeOfEntryIs_Over2HoursAgo_But_LessThan_4HoursAgo() {
        changePrivateDateTimeVariable(0, 2, 30);
        assertTrue(ticket.getEntryDateTime().isAfter(LocalDateTime.now().minusHours(4)));
        assertEquals(2.0, pp.getTicketPrice(ticket), 0);
    }

    @Test
    public void amountOwedIs_3_IfTimeOfEntryIs_Over4HoursAgo_But_LessThan_6HoursAgo() {
        changePrivateDateTimeVariable(0, 4, 30);
        assertTrue(ticket.getEntryDateTime().isAfter(LocalDateTime.now().minusHours(6)));
        assertEquals(3.0, pp.getTicketPrice(ticket), 0);
    }

    @Test
    public void amountOwedIs_4_IfTimeOfEntryIs_Over6HoursAgo_But_LessThan_8HoursAgo() {
        changePrivateDateTimeVariable(0, 6, 30);
        assertTrue(ticket.getEntryDateTime().isAfter(LocalDateTime.now().minusHours(8)));
        assertEquals(4.0, pp.getTicketPrice(ticket), 0);
    }

    @Test
    public void amountOwedIs_5_IfTimeOfEntryIs_Over8HoursAgo_But_LessThan_12HoursAgo() {
        changePrivateDateTimeVariable(0, 8, 30);
        assertTrue(ticket.getEntryDateTime().isAfter(LocalDateTime.now().minusHours(12)));
        assertEquals(5.0, pp.getTicketPrice(ticket), 0);
    }

    @Test
    public void amountOwedIs_8_IfTimeOfEntryIs_Over12HoursAgo_But_LessThan_24HoursAgo() {
        changePrivateDateTimeVariable(0, 12, 30);
        assertTrue(ticket.getEntryDateTime().isAfter(LocalDateTime.now().minusHours(24)));
        assertEquals(8.0, pp.getTicketPrice(ticket), 0);
    }

    @Test
    public void amountOwedIs_10_IfTimeOfEntryIs_Over24HoursAgo_But_LessThan_25HoursAgo() {
        changePrivateDateTimeVariable(0, 24, 30);
        assertTrue(ticket.getEntryDateTime().isAfter(LocalDateTime.now().minusHours(25)));
        assertEquals(10.0, pp.getTicketPrice(ticket), 0);
    }

    @Test
    public void amountOwedIs_11_IfTimeOfEntryIs_Over25HoursAgo_But_LessThan_26HoursAgo() {
        changePrivateDateTimeVariable(1, 1, 30);
        assertTrue(ticket.getEntryDateTime().isAfter(LocalDateTime.now().minusHours(26)));
        assertEquals(11.0, pp.getTicketPrice(ticket), 0);
    }

    @Test
    public void amountOwedIs_70_IfTimeOfEntryIs_Over7DaysAgo_But_LessThan_7DaysAnd1HourAgo() {
        changePrivateDateTimeVariable(7, 0, 30);
        assertTrue(ticket.getEntryDateTime().isAfter(LocalDateTime.now().minusDays(7).minusHours(1)));
        assertEquals(70.0, pp.getTicketPrice(ticket), 0);
    }
}