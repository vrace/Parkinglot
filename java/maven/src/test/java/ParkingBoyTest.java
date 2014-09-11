import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by ydliu on 9/1/14.
 */
public class ParkingBoyTest
{
    protected ParkingBoy parkingBoy;

    @Before
    public void setup()
    {
        parkingBoy = new ParkingBoy();

        parkingBoy.manage(new ParkingArea("SP_A", 1));
        parkingBoy.manage(new ParkingArea("SP_B", 2));

    }

    @Test
    public void should_return_ticket_when_park_success() throws Exception
    {
        // given
        Car car = new Car("Yang's Car");

        // when
        Ticket ticket = parkingBoy.storeCar(car);

        // then
        assertThat(ticket).isNotEqualTo(null);
    }

    @Test
    public void should_return_ticket_when_park_last_one() throws Exception
    {
        // given
        parkingBoy.storeCar(new Car("A"));
        parkingBoy.storeCar(new Car("B"));

        // when
        Ticket ticket = parkingBoy.storeCar(new Car("Yang's Car"));

        // then
        assertThat(ticket).isNotEqualTo(null);
    }

    @Test
    public void should_return_null_ticket_when_failed_to_park() throws Exception
    {
        // given
        parkingBoy.storeCar(new Car("A"));
        parkingBoy.storeCar(new Car("B"));
        parkingBoy.storeCar(new Car("C"));

        // when
        Ticket ticket = parkingBoy.storeCar(new Car("D"));

        // then
        assertThat(ticket).isEqualTo(null);
    }

    @Test
    public void should_return_correct_car_when_ticket_is_correct() throws Exception
    {
        // given
        Car car = new Car("Yang's Car");
        Ticket ticket = parkingBoy.storeCar(car);

        // when
        Car fetchedCar = parkingBoy.fetchCar(ticket);

        // then
        assertThat(fetchedCar).isEqualTo(car);
    }

    @Test
    public void should_return_null_car_when_ticket_is_bad() throws Exception
    {
        // given
        parkingBoy.storeCar(new Car("Wasted Car"));
        Ticket fraudTicket = new Ticket();

        // when
        Car car = parkingBoy.fetchCar(fraudTicket);

        // then
        assertThat(car).isEqualTo(null);
    }
}
