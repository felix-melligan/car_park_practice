package app.car_park.machines;

import app.car_park.CarPark;
import app.vehicles.Car;
import app.vehicles.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import utils.Ticket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ExitBarrierTest {
    private static final CarPark CAR_PARK = new CarPark();
    private static final Vehicle CAR = new Car("Reg");
    private Ticket ticket;
    private ExitBarrier exb;
    private ExitBarrier exbMock;
    private InOrder inOrder;

    @Before
    public void setUp() {
        ticket = new Ticket(CAR_PARK);
        exb = new ExitBarrier(CAR_PARK);
        exbMock = Mockito.mock(exb.getClass(),
                Mockito.withSettings().useConstructor(CAR_PARK).defaultAnswer(Mockito.CALLS_REAL_METHODS));
        inOrder = inOrder(exbMock);
    }

    @Test
    public void constructorSetsCarPark() {
        assertEquals(CAR_PARK, exb.getCarPark());
    }

    @Test
    public void onVehicleWaitingSetsMessageToPleaseInsertTicketAndBarrierIsClosed() {
        assertFalse(exb.getIsOpen());
        exb.onVehicleWaiting(CAR);
        assertEquals(Messages.INSERT.getMessage(), exb.getCurrentMessage());
    }

    @Test
    public void insertTicketChecksTicketIsPaidAndSetsMessageToCheckingThenPaidIfPaidAlsoReturnsTicketThenOpensBarrier() {
        ticket.setPaidTrue();
        exbMock.insertTicket(ticket);
        inOrder.verify(exbMock, times(1)).setMessage(Messages.CHECKING);
        inOrder.verify(exbMock, times(1)).setMessage(Messages.PAID);
        inOrder.verify(exbMock, times(1)).returnTicket();
        inOrder.verify(exbMock, times(1)).setMessage(Messages.TAKE);
        inOrder.verify(exbMock, times(1)).openBarrier();
        inOrder.verify(exbMock, times(1)).closeBarrier();
    }

    @Test
    public void ifTicketPaidIsFalseMessageSetToNotPaidAndBarrierClosedReturnTicketCalled() {
        assertFalse(ticket.getPaid());
        exbMock.insertTicket(ticket);
        assertEquals(Messages.NOTPAID.getMessage(), exbMock.getCurrentMessage());
        verify(exbMock, times(1)).returnTicket();
    }

}