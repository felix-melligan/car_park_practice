package app.car_park.machines;

enum Messages {
    INIT("Initialised"),
    TAKE("Please take a ticket"),
    INSERT("Please insert your ticket"),
    PAID("You have paid, thank you for staying with us"),
    NOTPAID("Please go to a PayPoint to pay the balance on your ticket")
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
    private boolean carWaiting;
    private Messages currentMessage;

    TicketBarrier() {
        this.currentMessage = Messages.INIT;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public boolean getCarWaiting() {
        return carWaiting;
    }

    public String getCurrentMessage() {
        return currentMessage.getMessage();
    }

    public void setMessage(Messages message) {
        this.currentMessage = message;
    }

    public void openBarrier() {
        this.isOpen = true;
    }

    public void closeBarrier() {
        this.isOpen = false;
    }

    public void setCarWaiting(boolean value) {
        this.carWaiting = value;
        if (value) onVehicleWaiting();
    }

    public abstract void onVehicleWaiting();
}
