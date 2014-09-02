import java.util.Vector;

/**
 * Created by ydliu on 9/1/14.
 */
public class ParkingBoy {

    private Vector<Parkinglot> parkinglots;

    public ParkingBoy() {
        parkinglots = new Vector<Parkinglot>();
    }

    public void manage(Parkinglot parkinglot) {
        parkinglots.add(parkinglot);
    }

    public Ticket storeCar(Car car) throws OutOfParkingAreaException {

        for (Parkinglot p : parkinglots) {

            try {
                Ticket t = p.storeCar(car);
                return t;
            } catch (Exception e) {

            }
        }

        throw new OutOfParkingAreaException();
    }

    public Car returnCar(Ticket ticket) throws BadTicketException {

        for (Parkinglot p : parkinglots) {

            try {
                Car car = p.returnCar(ticket);
                return car;
            } catch (Exception e) {

            }
        }

        throw new BadTicketException();
    }
}
