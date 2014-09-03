import java.util.Comparator;

/**
 * Created by ydliu on 9/3/14.
 */
public class SelectByMostFreeRoom implements Comparator<ParkingArea>
{
    @Override
    public int compare(ParkingArea parkingArea, ParkingArea parkingArea2)
    {
        return parkingArea2.getFreeRoom() - parkingArea.getFreeRoom();
    }
}
