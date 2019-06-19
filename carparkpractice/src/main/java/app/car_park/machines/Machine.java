package app.car_park.machines;

public abstract class Machine {
    private static int id = 0;

    public int getId() {
        return this.id;
    }
}
