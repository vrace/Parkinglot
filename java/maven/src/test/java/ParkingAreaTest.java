import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by ydliu on 9/1/14.
 */
public class ParkingAreaTest
{
    @Test
    public void should_return_same_name() throws Exception
    {
        // given
        String name = "Software Park";
        ParkingArea area = new ParkingArea(name, 1);

        // when
        String checkName = area.getName();

        // then
        assertThat(checkName).isEqualTo(name);
    }

    @Test
    public void should_return_same_capacity() throws Exception
    {
        // given
        int capacity = 5;
        ParkingArea area = new ParkingArea("Software Park", capacity);

        // when
        int checkCapacity = area.getCapacity();

        // then
        assertThat(checkCapacity).isEqualTo(capacity);
    }

    @Test
    public void should_return_free_room_equal_to_capacity_at_start() throws Exception
    {
        // given
        int capacity = 10;
        ParkingArea area = new ParkingArea("Software Park", capacity);

        // when
        int freeRoom = area.getFreeRoom();

        // then
        assertThat(freeRoom).isEqualTo(capacity);
    }

    @Test
    public void should_return_ticket_when_store_car_with_free_room() throws Exception
    {
        // given
        ParkingArea area = new ParkingArea("Software Park", 3);

        // when
        Ticket ticket = area.store(new Car("Alto"));

        // then
        assertThat(ticket).isNotEqualTo(null);
    }

    @Test
    public void should_return_null_ticket_when_store_car_without_free_room() throws Exception
    {
        // given
        ParkingArea area = new ParkingArea("Software Park", 2);
        area.store(new Car("1"));
        area.store(new Car("2"));

        // when
        Ticket ticket = area.store(new Car("Giant Truck"));

        // then
        assertThat(ticket).isEqualTo(null);
    }

    @Test
    public void should_return_same_car_when_fetch_with_correct_ticket() throws Exception
    {
        // given
        ParkingArea area = new ParkingArea("Software Park", 2);
        Car car = new Car("1");
        Ticket ticket = area.store(car);

        // when
        Car fetchedCar = area.fetch(ticket);

        // then
        assertThat(fetchedCar).isEqualTo(car);
    }

    @Test
    public void should_return_null_car_when_fetch_with_fraud_ticket() throws Exception
    {
        // given
        ParkingArea area = new ParkingArea("Software Park", 2);
        Car malibu = new Car("Malibu");
        area.store(malibu);

        // when
        Car whateverCar = area.fetch(new Ticket());

        // then
        assertThat(whateverCar).isNotEqualTo(malibu);
        assertThat(whateverCar).isEqualTo(null);
    }
}
