package app.car_park.machines;

public class EntryBarrier extends TicketBarrier {
    @Override
    public void onVehicleWaiting() {
        this.setMessage(Messages.TAKE);
    }
}
