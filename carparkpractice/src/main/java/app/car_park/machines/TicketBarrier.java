package app.car_park.machines;

import app.car_park.CarPark;
import app.vehicles.Vehicle;
import utils.Ticket;

enum Messages {
    INIT("Initialised"),
    TAKE("Please take ticket"),
    CHECKING("Checking, please wait..."),
    INSERT("Please insert your ticket"),
    PAID("You have paid, thank you for staying with us"),
    NOTPAID("Please go to a PayPoint to pay the balance on your ticket"),
    NOSPACE("Sorry, there are no spaces left for your vehicle, please wait for one to become available")
    ;

    private String message;

    public String getMessage() {
        return this.message;
    }

    Messages(String message) {
        this.message = message;
    }
}

public abstract class TicketBarrier extends Machine {
    private boolean isOpen;
    private boolean vehicleWaiting;
    private Messages currentMessage;

    TicketBarrier(CarPark carPark) {
        super(carPark);
        this.currentMessage = Messages.INIT;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public boolean getVehicleWaiting() {
        return vehicleWaiting;
    }

    public String getCurrentMessage() {
        return currentMessage.getMessage();
    }

    public void setMessage(Messages message) {
        this.currentMessage = message;
    }

    public void openBarrier() {
        this.isOpen = true;
        closeBarrier();
    }

    public void closeBarrier() {
        this.isOpen = false;
    }

    public void setVehicleWaiting(Vehicle vehicle) {
        this.vehicleWaiting = true;
        onVehicleWaiting(vehicle);
    }

    abstract void onVehicleWaiting(Vehicle vehicle);
}
