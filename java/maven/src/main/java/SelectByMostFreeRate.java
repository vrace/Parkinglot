import java.util.Comparator;

/**
 * Created by ydliu on 9/3/14.
 */
public class SelectByMostFreeRate implements Comparator<ParkingArea>
{
    private float calculateFreeRatio(ParkingArea parkingArea)
    {
        return (float)parkingArea.getFreeRoom() / parkingArea.getCapacity();
    }

    @Override
    public int compare(ParkingArea parkingArea, ParkingArea parkingArea2)
    {
        float rateLeft = calculateFreeRatio(parkingArea);
        float rateRight = calculateFreeRatio(parkingArea2);

        if (rateLeft < rateRight)
        {
            return 1;
        }
        else if (rateLeft > rateRight)
        {
            return -1;
        }

        return 0;
    }
}
