package app.car_park.machines;

import app.car_park.CarPark;
import app.vehicles.Vehicle;
import utils.Ticket;

public class ExitBarrier extends TicketBarrier {
    public ExitBarrier(CarPark carPark) {
        super(carPark);
    }

    @Override
    void onVehicleWaiting(Vehicle vehicle) {
        setMessage(Messages.INSERT);
    }

    public void insertTicket(Ticket ticket) {
        setMessage(Messages.CHECKING);
        if (ticket.getPaid()) {
            setMessage(Messages.PAID);
            returnTicket();
            setMessage(Messages.TAKE);
            openBarrier();
        } else {
            setMessage(Messages.NOTPAID);
            returnTicket();
        }
    }

    public void returnTicket() {}
}
