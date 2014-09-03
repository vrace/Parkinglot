import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * Created by ydliu on 9/1/14.
 */
public class ParkingBoy
{
    protected Vector<ParkingArea> parkingAreas;

    public ParkingBoy()
    {
        parkingAreas = new Vector<ParkingArea>();
    }

    public void manage(ParkingArea parkingArea)
    {
        parkingAreas.add(parkingArea);
    }

    public Ticket storeCar(Car car)
    {
        for (ParkingArea parkingArea : parkingAreas)
        {
            Ticket ticket = parkingArea.store(car);
            if (ticket != null)
            {
                return ticket;
            }
        }

        return null;
    }

    public Car fetchCar(Ticket ticket)
    {
        for (ParkingArea parkingArea : parkingAreas)
        {
            Car car = parkingArea.fetch(ticket);
            if (car != null)
            {
                return car;
            }
        }

        return null;
    }
}
