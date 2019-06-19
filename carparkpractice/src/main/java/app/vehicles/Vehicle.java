package app.vehicles;

import utils.Ticket;

public abstract class Vehicle {
    private String reg;
    private Ticket ticket;
    private int currentLevel;

    public Vehicle(String reg) {
        this.reg = reg;
        this.currentLevel = 0;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return this.ticket;
    }

    public int getCurrentLevel() {
        return this.currentLevel;
    }

    public String getReg() {
        return this.reg;
    }
}
