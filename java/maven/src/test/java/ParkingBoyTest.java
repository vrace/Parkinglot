import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by ydliu on 9/1/14.
 */
public class ParkingBoyTest {

    private ParkingBoy parkingBoy;

    @Before
    public void setup() {

        parkingBoy = new ParkingBoy();

        parkingBoy.manage(new Parkinglot(1));
        parkingBoy.manage(new Parkinglot(2));

    }

    @Test
    public void should_return_ticket_when_park_success() throws Exception {

        // given
        Car car = new Car("Yang's Car");

        // when
        Ticket ticket = parkingBoy.storeCar(car);

        // then
        assertThat(ticket).isNotEqualTo(null);
    }

    @Test
    public void should_return_ticket_when_park_last_one() throws Exception {

        // given
        parkingBoy.storeCar(new Car("A"));
        parkingBoy.storeCar(new Car("B"));

        // when
        Ticket ticket = parkingBoy.storeCar(new Car("Yang's Car"));

        // then
        assertThat(ticket).isNotEqualTo(null);
    }

    @Test(expected = OutOfParkingAreaException.class)
    public void should_throw_exception_when_failed_to_park() throws Exception {

        // given

        // when
        parkingBoy.storeCar(new Car("A"));
        parkingBoy.storeCar(new Car("B"));
        parkingBoy.storeCar(new Car("C"));
        parkingBoy.storeCar(new Car("D"));

        // then
    }

    @Test
    public void should_return_correct_car_when_ticket_is_correct() throws Exception {

        // given
        Car car = new Car("Yang's Car");
        Ticket ticket = parkingBoy.storeCar(car);

        // when
        Car returnedCar = parkingBoy.returnCar(ticket);

        // then
        assertThat(returnedCar).isEqualTo(car);
    }

    @Test(expected = BadTicketException.class)
    public void should_throw_exception_when_ticket_is_bad() throws Exception {

        // given
        parkingBoy.storeCar(new Car("Wasted Car"));
        Ticket fraudTicket = new Ticket();

        // when
        parkingBoy.returnCar(fraudTicket);

        // then
    }
}
