package app.car_park;

import app.car_park.machines.Machine;
import app.vehicles.Car;
import app.vehicles.Motorcycle;
import app.vehicles.Vehicle;
import utils.Report;

public class CarPark {
    private static int classId = 0;
    private int id;
    private int[][] layout;
    private int[][] availableSpaces;
    private String location;
    private Machine[] machines;

    public CarPark() {
        this.id = classId++;
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
        return this.id;
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

    public int getAvailableSpaces(Vehicle vehicle) {
        int spacesForVehicle = 0;
        for (int[] level : this.availableSpaces) {
            if (vehicle instanceof Car && level.length>0) spacesForVehicle+=level[0];
            if (vehicle instanceof Motorcycle && level.length>1) spacesForVehicle+=level[1];
        }
        return spacesForVehicle;
    }

    public Report generateReport(int hoursToReportOn) {
        return new Report();
    }
}
