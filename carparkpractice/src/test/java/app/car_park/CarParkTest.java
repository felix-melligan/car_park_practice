package app.car_park;

import app.car_park.machines.EntryBarrier;
import app.car_park.machines.ExitBarrier;
import app.car_park.machines.Machine;
import org.junit.Assert;
import org.junit.Test;
import utils.Report;

public class CarParkTest {
    private final int[][] defaultLayout = {{}};
    private final int[][] layout = {{100, 20}, {100, 20}, {100, 20}};
    private final Machine[] machines = {new EntryBarrier(), new ExitBarrier(), new PayPoint()};
    private final String location = "London";

    @Test
    public void generateCarPark() {
        CarPark cp1 = new CarPark();

        Assert.assertNotNull(cp1.getClass());
    }

    @Test
    public void generatesIncrementingId() {
        CarPark cp1 = new CarPark();
        CarPark cp2 = new CarPark();
        int cp1Id = cp1.getId();

        Assert.assertSame(++cp1Id, cp2.getId());
    }

    @Test
    public void givingCarParkMatrixSetsValue() {
        CarPark cp1 = new CarPark(layout);

        Assert.assertArrayEquals(layout, cp1.getLayout());
    }

    @Test
    public void notGivingCarParkMatrixSetsDefaultValue() {
        CarPark cp1 = new CarPark();

        Assert.assertArrayEquals(defaultLayout, cp1.getLayout());
    }

    @Test
    public void constructorCanTakeLocationAndSetIt() {
        CarPark cp1 = new CarPark(location);

        Assert.assertEquals(location, cp1.getLocation());
    }

    @Test
    public void constructorWithoutLocationReturnsNullString() {
        CarPark cp1 = new CarPark();

        Assert.assertEquals(null, cp1.getLocation());
    }

    @Test
    public void constructorWithLocationStillSetsMatrixDefaultValue() {
        CarPark cp1 = new CarPark(location);

        Assert.assertArrayEquals(defaultLayout, cp1.getLayout());
    }

    @Test
    public void constructorCanTakeLocationAndLayoutAndSetBoth() {
        CarPark cp1 = new CarPark(layout, location);

        Assert.assertArrayEquals(layout, cp1.getLayout());
        Assert.assertEquals(location, cp1.getLocation());
    }

    @Test
    public void constructorCanTakeMachineArrayAsArgumentOnlyWithLayout() {
        CarPark cp1 = new CarPark(layout, machines);

        Assert.assertArrayEquals(machines, cp1.getMachines());
    }

    @Test
    public void emptyConstructorSetsMachinesArrayToDefault() {
        Machine[] defaultMachines = {};
        CarPark cp1 = new CarPark();

        Assert.assertArrayEquals(defaultMachines, cp1.getMachines());
    }

    @Test
    public void canGiveConstructorLayoutMachinesAndLocationAndSetsAll() {
        CarPark cp1 = new CarPark(layout, machines, location);

        Assert.assertArrayEquals(machines, cp1.getMachines());
        Assert.assertArrayEquals(layout, cp1.getLayout());
        Assert.assertEquals(location, cp1.getLocation());
    }

    @Test
    public void generateReportCreatesAReport() {
        CarPark cp1 = new CarPark();
        Report report = cp1.generateReport(0);

        Assert.assertNotNull(report);
    }

    @Test
    public void availableSpacesSetToLayout() {
        CarPark cp1 = new CarPark(layout, machines, location);

        Assert.assertArrayEquals(layout, cp1.getAvailableSpaces());
    }
}
