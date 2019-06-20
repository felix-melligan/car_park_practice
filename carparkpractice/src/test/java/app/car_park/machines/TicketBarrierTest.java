package app.car_park.machines;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class TicketBarrierTest {
    private TicketBarrier tb;

    @Before
    public void setUp() {
        tb = Mockito.mock(
                TicketBarrier.class,
                Mockito.withSettings().useConstructor().defaultAnswer(Mockito.CALLS_REAL_METHODS));
    }

    @Test
    public void isOpenAndCarWaitingBothSetToFalseOnInstantiation() {
        assertFalse(tb.getIsOpen());
        assertFalse(tb.getCarWaiting());
    }

    @Test
    public void currentMessageSetToInitialised() {
        assertEquals(Messages.INIT.getMessage(), tb.getCurrentMessage());
    }

    @Test
    public void canOpenAndCloseBarrier() {
        assertFalse(tb.getIsOpen());
        tb.openBarrier();
        assertTrue(tb.getIsOpen());
        tb.closeBarrier();
        assertFalse(tb.getIsOpen());
    }

    @Test
    public void canSetMessage() {
        assertEquals(Messages.INIT.getMessage(), tb.getCurrentMessage());
        tb.setMessage(Messages.TAKE);
        assertEquals(Messages.TAKE.getMessage(), tb.getCurrentMessage());
    }
}