package app.car_park;

public class CarPark {
    private static int currentId = 0;
    private int thisId;
    private int[][] layout;
    private String location;
    private Machine[] machines;

    public CarPark() {
        this.thisId = ++currentId;
        this.layout = new int[][] {{}};
        this.machines = new Machine[] {};
    }

    public CarPark(int[][] layout) {
        this();
        this.layout = layout;
    }

    public CarPark(String location) {
        this();
        this.location = location;
    }

    public CarPark(int[][] layout, String location) {
        this(layout);
        this.location = location;

    }

    public CarPark(int[][] layout, Machine[] machines) {
        this(layout);
        this.machines = machines;
    }

    public CarPark(int[][] layout, Machine[] machines, String location) {
        this(layout, machines);
        this.location = location;
    }

    public int getId() {
        return this.thisId;
    }

    public int[][] getLayout() {
        return this.layout;
    }

    public String getLocation() {
        return this.location;
    }

    public Machine[] getMachines() {
        return this.machines;
    }
}
