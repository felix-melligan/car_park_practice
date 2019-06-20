package app.car_park.machines;

import app.car_park.CarPark;
import app.vehicles.Car;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class EntryBarrierTest {
    private EntryBarrier eb;
    private static final String REG = "reg";
    private static final CarPark CARPARK = new CarPark();

    @Before
    public void setUp() {
        eb = Mockito.mock(
                EntryBarrier.class,
                Mockito.withSettings().useConstructor(CARPARK).defaultAnswer(Mockito.CALLS_REAL_METHODS));
    }

    @Test
    public void onCarWaitingCalledWhenCarWaitingSetToTrueSetsMessageToTakeTicket() {
        assertEquals(Messages.INIT.getMessage(), eb.getCurrentMessage());
        eb.setVehicleWaiting(new Car(REG));
        assertEquals(Messages.TAKE.getMessage(), eb.getCurrentMessage());
    }

    @Test
    public void dispenseTicketMethodCalledDuringOnVehicleWaiting() {
        Mockito.verify(eb, Mockito.times(0)).dispenseTicket();
        eb.setVehicleWaiting(new Car(REG));
        Mockito.verify(eb, Mockito.times(1)).dispenseTicket();
    }

    @Test
    public void dispenseTicketMethodChecksForSpacesForVehicleBeforeGivingTicket() {

    }
}