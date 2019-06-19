package app.car_park.machines;

import utils.Ticket;

public class PayPoint extends Machine {

    public void pay(Ticket ticket) {
        ticket.setPaidTrue();
    }

    public double getTicketPrice(Ticket ticket) {
        return 0.0;
    }
}
