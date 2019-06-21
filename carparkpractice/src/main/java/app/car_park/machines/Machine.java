package app.car_park.machines;

import app.car_park.CarPark;

enum State {
    OFF, ON
}

public abstract class Machine {
    private static int classId = 0;
    private int id;
    private State state = State.OFF;
    private CarPark containingCarPark;

    public Machine(CarPark carPark) {
        this.id = this.classId++;
        this.containingCarPark = carPark;
    }

    public int getId() {
        return this.id;
    }

    public State getState() {
        return this.state;
    }

    public CarPark getCarPark() { return this.containingCarPark; }

    public void switchOn() {
        if (this.state == State.OFF) {
            this.state = State.ON;
        }
    }

    public void switchOff() {
        if (this.state == State.ON) {
            this.state = State.OFF;
        }
    }
}
