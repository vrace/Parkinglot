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

    protected String reportParkingAreas(String indentSpace)
    {
        String parkingAreaReports = "";

        for (ParkingArea parkingArea : parkingAreas)
        {
            parkingAreaReports += parkingArea.report(indentSpace);
        }

        return parkingAreaReports;
    }

    @Override
    public String report(String indentSpace)
    {
        String self = String.format("%sParking Boy - %d Parking Areas\n",
                indentSpace, parkingAreas.size());

        return self + reportParkingAreas(indentSpace + "  ");
    }
}
