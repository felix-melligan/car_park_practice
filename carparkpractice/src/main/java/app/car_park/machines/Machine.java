package app.car_park.machines;

import utils.State;

public abstract class Machine {
    private static int classId = 0;
    private int id;
    private State state = State.OFF;

    public Machine() {
        this.id = this.classId++;
    }

    public int getId() {
        return this.id;
    }

    public State getState() {
        return this.state;
    }

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
