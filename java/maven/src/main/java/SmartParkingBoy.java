import java.util.Collections;
import java.util.Comparator;

/**
 * Created by ydliu on 9/2/14.
 */
public class SmartParkingBoy extends ParkingBoy
{
    protected Comparator<ParkingArea> parkingAreaComparator;

    public SmartParkingBoy(Comparator<ParkingArea> parkingAreaComparator)
    {
        this.parkingAreaComparator = parkingAreaComparator;
    }

    @Override
    public Ticket storeCar(Car car)
    {
        Collections.sort(parkingAreas, parkingAreaComparator);
        return super.storeCar(car);
    }
}
