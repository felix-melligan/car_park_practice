package app.car_park.machines;

import app.car_park.CarPark;
import app.vehicles.Vehicle;

public class ExitBarrier extends TicketBarrier {
    public ExitBarrier(CarPark carPark) {
        super(carPark);
    }

    @Override
    void onVehicleWaiting(Vehicle vehicle) {

    }
}
