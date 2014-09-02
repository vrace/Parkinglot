/**
 * Created by ydliu on 9/1/14.
 */
public class ParkingArea {

    private Car car;

    public ParkingArea() {
        car = null;
    }

    public Car getCar() {
        return car;
    }

    public Ticket use(Car car) throws ParkingAreaOccupiedException {
        if (this.car != null) {
            throw new ParkingAreaOccupiedException();
        }
        this.car = car;
        return new Ticket();
    }

    public void release() throws ParkingAreaNotOccupiedException {
        if (this.car == null) {
            throw new ParkingAreaNotOccupiedException();
        }
        this.car = null;
    }
}
