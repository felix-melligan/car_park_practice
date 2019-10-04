package app.car_park.machines;

import app.car_park.CarPark;
import utils.Ticket;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class PayPoint extends Machine {

    public PayPoint(CarPark carPark) {
        super(carPark);
    }

    public void pay(Ticket ticket) {
        ticket.setPaidTrue();
    }

    public long getTicketPrice(Ticket ticket) {
//        get values from map and assign
        Map dateTimeDifferenceMap = getDifferenceInTime(ticket.getEntryDateTime());
        long days = (long) dateTimeDifferenceMap.get("DAYS");
        long hours = (long) dateTimeDifferenceMap.get("HOURS");
        long cost = 0;

//        10 per day
        cost += days*10;

//        each hour bracket adds a different value
        if (hours < 1) {}
        else if (hours < 2) {cost+=1;}
        else if (hours < 4) {cost+=2;}
        else if (hours < 6) {cost+=3;}
        else if (hours < 8) {cost+=4;}
        else if (hours < 12) {cost+=5;}
        else if (hours < 24) {cost+=8;}

        return cost;
    }

    private Map getDifferenceInTime(LocalDateTime dateTimeFrom) {
        Map<String, Long> dateTimDifferenceMap = new HashMap<>();
        LocalDateTime dateTimeNow = LocalDateTime.now();

        LocalDateTime tempDateTime = LocalDateTime.from( dateTimeFrom );

        long days = tempDateTime.until( dateTimeNow, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays( days );

        long hours = tempDateTime.until( dateTimeNow, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( dateTimeNow, ChronoUnit.MINUTES);

        dateTimDifferenceMap.put("DAYS", days);
        dateTimDifferenceMap.put("HOURS", hours);
        dateTimDifferenceMap.put("MINUTES", minutes);

        return dateTimDifferenceMap;
    }
}
