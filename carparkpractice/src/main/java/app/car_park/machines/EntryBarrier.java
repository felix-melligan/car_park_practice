package app.car_park.machines;

import app.car_park.CarPark;
import app.vehicles.Vehicle;
import utils.Ticket;

public class EntryBarrier extends TicketBarrier {
    public EntryBarrier(CarPark carPark) {
        super(carPark);
    }

    @Override
    protected void onVehicleWaiting(Vehicle vehicle) {
        setMessage(Messages.CHECKING);
        if (getCarPark().getAvailableSpaces(vehicle) > 0) {
            dispenseTicket(vehicle);
            setMessage(Messages.TAKE);
            openBarrier();
        } else {
            setMessage(Messages.NOSPACE);
        }
    }

    protected Ticket dispenseTicket(Vehicle vehicle) {
        Ticket ticket = new Ticket(getCarPark());
        vehicle.setTicket(ticket);
        return ticket;
    }
}
