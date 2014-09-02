import java.util.HashMap;
import java.util.Vector;

/**
 * Created by ydliu on 9/1/14.
 */
public class Parkinglot {

    private Vector<ParkingArea> parkingAreas;
    private HashMap<Ticket, ParkingArea> usedAreas;

    public Parkinglot(int capacity) {
        parkingAreas = new Vector<ParkingArea>();
        for (int i = 0; i < capacity; i++) {
            parkingAreas.add(new ParkingArea());
        }

        usedAreas = new HashMap<Ticket, ParkingArea>();
    }

    public Ticket storeCar(Car car) throws OutOfParkingAreaException, ParkingAreaOccupiedException {

        if (parkingAreas.isEmpty()) {
            throw new OutOfParkingAreaException();
        }

        ParkingArea area = parkingAreas.firstElement();
        parkingAreas.remove(0);

        Ticket ticket = area.use(car);
        usedAreas.put(ticket, area);

        return ticket;
    }

    public Car returnCar(Ticket ticket) throws BadTicketException, ParkingAreaNotOccupiedException {

        if (!usedAreas.containsKey(ticket)) {
            throw new BadTicketException();
        }

        ParkingArea area = usedAreas.get(ticket);
        usedAreas.remove(ticket);

        Car car = area.getCar();
        area.release();
        parkingAreas.add(area);

        return car;
    }
}
