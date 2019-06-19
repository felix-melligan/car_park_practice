package app.car_park;

import utils.Report;

public class CarPark {
    private static int currentId = 0;
    private int thisId;
    private int[][] layout;
    private int[][] availableSpaces;
    private String location;
    private Machine[] machines;

    public CarPark() {
        this.thisId = ++currentId;
        this.layout = new int[][] {{}};
        this.machines = new Machine[] {};
        this.availableSpaces = layout;
    }

    public CarPark(int[][] layout) {
        this();
        this.layout = layout;
        this.availableSpaces = layout;
    }

    public CarPark(String location) {
        this();
        this.location = location;
    }

    public CarPark(int[][] layout, String location) {
        this(layout);
        this.location = location;
        this.availableSpaces = layout;

    }

    public CarPark(int[][] layout, Machine[] machines) {
        this(layout);
        this.machines = machines;
        this.availableSpaces = layout;
    }

    public CarPark(int[][] layout, Machine[] machines, String location) {
        this(layout, machines);
        this.location = location;
        this.availableSpaces = layout;
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

    public int[][] getAvailableSpaces() {
        return this.availableSpaces;
    }

    public Report generateReport(int hoursToReportOn) {
        return new Report();
    }
}
