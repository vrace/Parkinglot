import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by ydliu on 9/2/14.
 */
public class SmartParkingBoyTest extends ParkingBoyTest
{
    @Test
    public void should_call_store_car_on_largest_parking_area_when_park_with_most_free_room_boy() throws Exception
    {
        // given
        SmartParkingBoy boy = new SmartParkingBoy(new SelectByMostFreeRoom());
        ParkingArea p1 = spy(new ParkingArea("SP_A", 2));
        ParkingArea p2 = spy(new ParkingArea("SP_B", 3));
        ParkingArea p3 = spy(new ParkingArea("SP_C", 1));
        boy.manage(p1);
        boy.manage(p2);
        boy.manage(p3);
        Car car = new Car("Yang's Car");

        // then
        boy.storeCar(car);

        // then
        verify(p2).store(car);
        verify(p1, never()).store(car);
        verify(p3, never()).store(car);
    }

    @Test
    public void should_call_store_car_on_greatest_free_rate_parking_area_when_park_with_greatest_free_rate_boy() throws Exception
    {
        // given
        SmartParkingBoy boy = new SmartParkingBoy(new SelectByMostFreeRate());
        ParkingArea p1 = spy(new ParkingArea("SP_A", 2));
        ParkingArea p2 = spy(new ParkingArea("SP_B", 3));
        p1.store(new Car("1"));
        p2.store(new Car("2"));
        p2.store(new Car("3"));
        boy.manage(p1);
        boy.manage(p2);
        Car car = new Car("My Car");

        // when
        boy.storeCar(car);

        // then
        verify(p1).store(car);
        verify(p2, never()).store(car);
    }
}
