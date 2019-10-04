package app.car_park;

import app.car_park.machines.EntryBarrier;
import app.car_park.machines.ExitBarrier;
import app.car_park.machines.Machine;
import static org.junit.Assert.*;

import app.car_park.machines.PayPoint;
import app.vehicles.Car;
import org.junit.Before;
import org.junit.Test;
import utils.Report;

public class CarParkTest {
    private final int[][] defaultLayout = {{}};
    private final int[][] layout = {{100, 20}, {100, 20}, {100, 20}};
    private CarPark cp1;
    private final Machine[] machines = {new EntryBarrier(cp1), new ExitBarrier(cp1), new PayPoint(cp1)};
    private final String location = "London";

    @Before
    public void setUp() {
        cp1 = new CarPark();
    }

    @Test
    public void generateCarPark() {
        assertNotNull(cp1.getClass());
    }

    @Test
    public void generatesIncrementingId() {
        CarPark cp1 = new CarPark();
        CarPark cp2 = new CarPark();
        int cp1Id = cp1.getId();

        assertEquals(++cp1Id, cp2.getId());
    }

    @Test
    public void givingCarParkMatrixSetsValue() {
        CarPark cp1 = new CarPark(layout);

        assertArrayEquals(layout, cp1.getLayout());
    }

    @Test
    public void notGivingCarParkMatrixSetsDefaultValue() {
        CarPark cp1 = new CarPark();

        assertArrayEquals(defaultLayout, cp1.getLayout());
    }

    @Test
    public void constructorCanTakeLocationAndSetIt() {
        CarPark cp1 = new CarPark(location);

        assertEquals(location, cp1.getLocation());
    }

    @Test
    public void constructorWithoutLocationReturnsNullString() {
        CarPark cp1 = new CarPark();

        assertEquals(null, cp1.getLocation());
    }

    @Test
    public void constructorWithLocationStillSetsMatrixDefaultValue() {
        CarPark cp1 = new CarPark(location);

        assertArrayEquals(defaultLayout, cp1.getLayout());
    }

    @Test
    public void constructorCanTakeLocationAndLayoutAndSetBoth() {
        CarPark cp1 = new CarPark(layout, location);

        assertArrayEquals(layout, cp1.getLayout());
        assertEquals(location, cp1.getLocation());
    }

    @Test
    public void constructorCanTakeMachineArrayAsArgumentOnlyWithLayout() {
        CarPark cp1 = new CarPark(layout, machines);

        assertArrayEquals(machines, cp1.getMachines());
    }

    @Test
    public void emptyConstructorSetsMachinesArrayToDefault() {
        Machine[] defaultMachines = {};
        CarPark cp1 = new CarPark();

        assertArrayEquals(defaultMachines, cp1.getMachines());
    }

    @Test
    public void canGiveConstructorLayoutMachinesAndLocationAndSetsAll() {
        CarPark cp1 = new CarPark(layout, machines, location);

        assertArrayEquals(machines, cp1.getMachines());
        assertArrayEquals(layout, cp1.getLayout());
        assertEquals(location, cp1.getLocation());
    }

    @Test
    public void generateReportCreatesAReport() {
        CarPark cp1 = new CarPark();
        Report report = cp1.generateReport(0);

        assertNotNull(report);
    }

    @Test
    public void availableSpacesSetToLayout() {
        CarPark cp1 = new CarPark(layout, machines, location);

        assertEquals(300, cp1.getAvailableSpaces(new Car("reg")));
    }
}
