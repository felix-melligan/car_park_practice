package app.car_park.machines;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

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

    @Test
    public void canSetCarWaiting() {
        assertFalse(tb.getCarWaiting());
        tb.setCarWaiting(true);
        assertTrue(tb.getCarWaiting());
    }

    @Test
    public void whenCarWaitingSetToTrueOnCarWaitingCalled() {
        Mockito.verify(tb, times(0)).onVehicleWaiting();
        tb.setCarWaiting(true);
        Mockito.verify(tb, times(1)).onVehicleWaiting();
    }

    @Test
    public void whenCarWaitingSetWithFalseOnCarWaitingNotCalled() {
        tb.setCarWaiting(false);
        Mockito.verify(tb, times(0)).onVehicleWaiting();
    }
}