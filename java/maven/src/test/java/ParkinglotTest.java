import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by ydliu on 9/1/14.
 */
public class ParkinglotTest {

    private Parkinglot parkinglot;

    @Before
    public void setup() {

        int capacity = 2;
        parkinglot = new Parkinglot(capacity);

    }

    @Test
    public void should_not_throw_exception_when_park_car() throws Exception {

        // given
        Car car = new Car("Yang's Car");

        // when
        Ticket ticket = parkinglot.storeCar(car);

        // then
        assertThat(ticket).isNotEqualTo(null);
    }

    @Test(expected = OutOfParkingAreaException.class)
    public void should_throw_exception_when_totally_used() throws Exception {

        // given

        // when
        parkinglot.storeCar(new Car("A"));
        parkinglot.storeCar(new Car("B"));
        parkinglot.storeCar(new Car("C"));

        // then
    }

    @Test
    public void should_return_correct_car_when_use_correct_ticket() throws Exception {

        // given
        Car car = new Car("Yang's Car");
        Ticket ticket = parkinglot.storeCar(car);

        // when
        Car returnedCar = parkinglot.returnCar(ticket);

        // then
        assertThat(returnedCar).isEqualTo(car);
    }

    @Test(expected = BadTicketException.class)
    public void should_throw_exception_when_use_bad_ticket() throws Exception {

        // given
        Ticket fraudTicket = new Ticket();

        // when
        parkinglot.returnCar(fraudTicket);

        // then
    }

    @Test
    public void should_reuse_parking_area_when_possible() throws Exception {

        // given
        parkinglot.storeCar(new Car("A"));
        Ticket ticket = parkinglot.storeCar(new Car("B"));
        parkinglot.returnCar(ticket);

        // when
        Ticket myTicket = parkinglot.storeCar(new Car("Yang's Car"));

        // then
        assertThat(ticket).isNotEqualTo(null);
        assertThat(ticket).isNotEqualTo(myTicket);
        assertThat(myTicket).isNotEqualTo(null);
    }
}
