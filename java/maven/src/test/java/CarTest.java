import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by ydliu on 9/1/14.
 */
public class CarTest {

    @Test
    public void should_return_correct_car_name() {

        // given
        final String presetName = "Yang's Car";
        Car car = new Car(presetName);

        // when
        final String carName = car.getName();

        // then
        assertThat(carName).isEqualTo(presetName);
    }

}
