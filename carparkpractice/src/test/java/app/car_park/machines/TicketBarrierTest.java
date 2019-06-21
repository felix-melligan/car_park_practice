package app.car_park.machines;

import app.car_park.CarPark;
import app.vehicles.Car;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

public class TicketBarrierTest {
    private TicketBarrier tb;
    private static final String REG = "reg";
    private static final Car CAR = new Car(REG);
    private static final CarPark CAR_PARK = new CarPark();

    @Before
    public void setUp() {
        tb = Mockito.mock(
                TicketBarrier.class,
                Mockito.withSettings().useConstructor(CAR_PARK).defaultAnswer(Mockito.CALLS_REAL_METHODS));
    }

    @Test
    public void isOpenAndCarWaitingBothSetToFalseOnInstantiation() {
        assertFalse(tb.getIsOpen());
        assertFalse(tb.getVehicleWaiting());
    }

    @Test
    public void currentMessageSetToInitialised() {
        assertEquals(Messages.INIT.getMessage(), tb.getCurrentMessage());
    }

    @Test
    public void canOpenAndCloseBarrier() {
//        Set InOrder to check order of methods called
        InOrder inOrder = Mockito.inOrder(tb);
        assertFalse(tb.getIsOpen());
        tb.openBarrier();
        inOrder.verify(tb, times(1)).openBarrier();
        inOrder.verify(tb, times(1)).closeBarrier();
    }

    @Test
    public void canSetMessage() {
        assertEquals(Messages.INIT.getMessage(), tb.getCurrentMessage());
        tb.setMessage(Messages.TAKE);
        assertEquals(Messages.TAKE.getMessage(), tb.getCurrentMessage());
    }

    @Test
    public void canSetCarWaiting() {
        assertFalse(tb.getVehicleWaiting());
        tb.setVehicleWaiting(CAR);
        assertTrue(tb.getVehicleWaiting());
    }

    @Test
    public void whenCarWaitingSetToTrueOnCarWaitingCalled() {
        Mockito.verify(tb, times(0)).onVehicleWaiting(CAR);
        tb.setVehicleWaiting(CAR);
        Mockito.verify(tb, times(1)).onVehicleWaiting(CAR);
    }
}