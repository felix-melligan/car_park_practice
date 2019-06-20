package app.car_park.machines;

import app.car_park.CarPark;
import app.vehicles.Car;
import app.vehicles.Vehicle;
import utils.Ticket;

public class EntryBarrier extends TicketBarrier {
    public EntryBarrier(CarPark carPark) {
        super(carPark);
    }

    @Override
    protected void onVehicleWaiting(Vehicle vehicle) {
        this.setMessage(Messages.TAKE);
        if (getCarPark().getAvailableSpaces(vehicle) > 0) {
            dispenseTicket();
        }
    }

    protected Ticket dispenseTicket() {
        return new Ticket();
    }
}
