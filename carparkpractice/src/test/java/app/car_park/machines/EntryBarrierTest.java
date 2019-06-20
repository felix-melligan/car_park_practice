package app.car_park.machines;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntryBarrierTest {
    private EntryBarrier eb;

    @Before
    public void setUp() {
        eb = new EntryBarrier();
    }

    @Test
    public void onCarWaitingCalledWhenCarWaitingSetToTrueSetsMessageToTakeTicket() {
        assertEquals(Messages.INIT.getMessage(), eb.getCurrentMessage());
        eb.setCarWaiting(true);
        assertEquals(Messages.TAKE.getMessage(), eb.getCurrentMessage());
    }

}