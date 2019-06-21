package app.car_park.machines;

import app.car_park.CarPark;
import app.vehicles.Car;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.inOrder;

public class EntryBarrierTest {
    private EntryBarrier eb;
    private EntryBarrier ebWithSpaces;
    private static final String REG = "reg";
    private static final Car CAR = new Car(REG);
    private static final CarPark CARPARK = new CarPark();

    @Before
    public void setUp() {
        eb = Mockito.mock(
                EntryBarrier.class,
                Mockito.withSettings().useConstructor(CARPARK).defaultAnswer(Mockito.CALLS_REAL_METHODS));
        ebWithSpaces = Mockito.mock(
                EntryBarrier.class,
                Mockito.withSettings().useConstructor(new CarPark(new int[][] {{100, 20}})).defaultAnswer(Mockito.CALLS_REAL_METHODS));
    }

    @Test
    public void onCarWaitingCalledWhenCarWaitingSetToTrueSetsMessageToCheckForSpaces() {
        assertEquals(Messages.INIT.getMessage(), eb.getCurrentMessage());
        eb.setVehicleWaiting(new Car(REG));
        Mockito.verify(eb, Mockito.times(1)).setMessage(Messages.CHECKING);
    }

    @Test
    public void dispenseTicketMethodCalledDuringOnVehicleWaiting() {
        Mockito.verify(ebWithSpaces, Mockito.times(0)).dispenseTicket(CAR);
        ebWithSpaces.setVehicleWaiting(CAR);
        Mockito.verify(ebWithSpaces, Mockito.times(1)).dispenseTicket(CAR);
    }

    @Test
    public void dispenseTicketMethodChecksForSpacesForVehicleBeforeGivingTicket() {
        assertEquals(0, CARPARK.getAvailableSpaces(CAR));
        eb.setVehicleWaiting(CAR);
        Mockito.verify(eb, Mockito.times(0)).dispenseTicket(CAR);
    }

    @Test
    public void ifBarrierDispensesTicketMessageSetToPleaseTakeTicketVehicleWaitingSetToFalse() {
        assertNotEquals(Messages.TAKE.getMessage(), ebWithSpaces.getCurrentMessage());
        ebWithSpaces.setVehicleWaiting(CAR);
        assertEquals(Messages.TAKE.getMessage(), ebWithSpaces.getCurrentMessage());
        assertFalse(eb.getVehicleWaiting());
    }

    @Test
    public void ifNoSpacesForVehicleTypeBarrierStaysClosedMessageSaysToWait() {
        assertNotEquals(Messages.NOSPACE.getMessage(), eb.getCurrentMessage());
        eb.setVehicleWaiting(CAR);
        assertEquals(Messages.NOSPACE.getMessage(), eb.getCurrentMessage());
    }

    @Test
    public void ifTicketDispensedBarrierOpensAndThenCloses() {
//        Create InOrder object to see the order of method calls
        InOrder inOrder = inOrder(ebWithSpaces);
        Mockito.verify(ebWithSpaces, Mockito.times(0)).openBarrier();
        Mockito.verify(ebWithSpaces, Mockito.times(0)).closeBarrier();
        ebWithSpaces.setVehicleWaiting(CAR);
        inOrder.verify(ebWithSpaces, Mockito.times(1)).openBarrier();
        inOrder.verify(ebWithSpaces, Mockito.times(1)).closeBarrier();
    }


}