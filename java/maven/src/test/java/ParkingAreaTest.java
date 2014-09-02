import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by ydliu on 9/1/14.
 */
public class ParkingAreaTest {

    @Test
    public void should_not_be_used_at_beginning() {

        // given
        ParkingArea area = new ParkingArea();

        // when
        Car car = area.getCar();

        // then
        assertThat(car).isEqualTo(null);
    }

    @Test
    public void should_return_correct_car_when_used() throws Exception {

        // given
        ParkingArea area = new ParkingArea();
        Car car = new Car("Yang's Car");
        area.use(car);

        // when
        Car storedCar = area.getCar();

        // then
        assertThat(storedCar).isEqualTo(car);
    }

    @Test(expected = ParkingAreaOccupiedException.class)
    public void should_throw_exception_when_use_again() throws Exception {

        // given
        ParkingArea area = new ParkingArea();

        // when
        boolean exceptionCaught = false;

        area.use(new Car("My Car"));
        area.use(new Car("Bad Guy's Car"));

        // then
        assertThat(exceptionCaught).isEqualTo(true);
    }

    @Test(expected = ParkingAreaNotOccupiedException.class)
    public void should_throw_exception_when_release_and_not_used() throws Exception {

        // given
        ParkingArea area = new ParkingArea();

        // when
        area.release();

        // then
    }

    @Test
    public void should_not_throw_exception_when_release() throws Exception {

        // given
        ParkingArea area = new ParkingArea();

        // when
        area.use(new Car("My Car"));
        area.release();

        // then
        assertThat(area.getCar()).isEqualTo(null);
    }
}
