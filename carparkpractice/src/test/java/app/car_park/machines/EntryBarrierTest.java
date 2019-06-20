package app.car_park.machines;

import app.car_park.CarPark;
import app.vehicles.Car;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        assertEquals(Messages.CHECKING.getMessage(), eb.getCurrentMessage());
    }

    @Test
    public void dispenseTicketMethodCalledDuringOnVehicleWaiting() {
        Mockito.verify(ebWithSpaces, Mockito.times(0)).dispenseTicket();
        ebWithSpaces.setVehicleWaiting(CAR);
        Mockito.verify(ebWithSpaces, Mockito.times(1)).dispenseTicket();
    }

    @Test
    public void dispenseTicketMethodChecksForSpacesForVehicleBeforeGivingTicket() {
        assertEquals(0, CARPARK.getAvailableSpaces(new Car(REG)));
        eb.setVehicleWaiting(CAR);
        Mockito.verify(eb, Mockito.times(0)).dispenseTicket();
    }

    @Test
    public void ifBarrierDispensesTicketMessageSetToPleaseTakeTicketVehicleWaitingSetToFalse() {
        assertNotEquals(Messages.TAKE.getMessage(), ebWithSpaces.getCurrentMessage());
        ebWithSpaces.setVehicleWaiting(CAR);
        assertEquals(Messages.TAKE.getMessage(), ebWithSpaces.getCurrentMessage());
    }


}