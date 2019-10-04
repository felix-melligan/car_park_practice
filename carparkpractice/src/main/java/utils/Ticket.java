package utils;

import app.car_park.CarPark;

import java.time.LocalDateTime;

public class Ticket {
    private CarPark carPark;
    private boolean paid;
    private LocalDateTime entryDateTime;

    public Ticket(CarPark carPark) {
        this.entryDateTime = LocalDateTime.now();
        this.carPark = carPark;
    }

    public CarPark getCarPark() {
        return this.carPark;
    }

    public boolean getPaid() {
        return this.paid;
    }

    public void setPaidTrue() {
        this.paid = true;
    }

    public LocalDateTime getEntryDateTime() {
        return this.entryDateTime;
    }
}
