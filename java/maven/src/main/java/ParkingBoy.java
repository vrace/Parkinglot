import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * Created by ydliu on 9/1/14.
 */
public class ParkingBoy implements Reportable
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

    @Override
    public String report()
    {
        return String.format("Parking Boy - %d Parking Areas", parkingAreas.size());
    }

    @Override
    public ArrayList<Reportable> getSubNodes()
    {
        ArrayList<Reportable> arr = new ArrayList<Reportable>();
        for (ParkingArea parkingArea : parkingAreas)
        {
            arr.add(parkingArea);
        }
        return arr;
    }
}
