import org.junit.Test;

import java.util.Vector;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ydliu on 9/3/14.
 */
public class ParkingManagerTest
{
    @Test
    public void should_invoke_select_boy_when_park() throws Exception
    {
        // given
        SelectBoy selectMock = mock(SelectBoy.class);
        when(selectMock.selectParkingBoy(any(Vector.class))).thenReturn(null);

        ParkingBoy boy1 = new ParkingBoy();
        boy1.manage(new ParkingArea("SP_A", 2));
        boy1.manage(new ParkingArea("SP_B", 3));

        ParkingBoy boy2 = new ParkingBoy();
        boy2.manage(new ParkingArea("SP_C", 2));
        boy2.manage(new ParkingArea("SP_D", 3));

        ParkingBoy boy3 = new ParkingBoy();
        boy3.manage(new ParkingArea("SP_E", 2));
        boy3.manage(new ParkingArea("SP_F", 3));

        ParkingManager parkingManager = new ParkingManager(selectMock, new SelectByMostFreeRoom());

        parkingManager.manage(boy1);
        parkingManager.manage(boy2);
        parkingManager.manage(boy3);

        parkingManager.manage(new ParkingArea("SP_G", 2));

        // when
        Ticket ticket = parkingManager.storeCar(new Car("1"));

        // that
        assertThat(ticket).isNotEqualTo(null);
        verify(selectMock).selectParkingBoy(any(Vector.class));
    }

    @Test
    public void should_invoke_store_car_of_selected_boy() throws Exception
    {
        // given

        ParkingBoy boy1 = new ParkingBoy();
        boy1.manage(new ParkingArea("SP_A", 2));
        boy1.manage(new ParkingArea("SP_B", 3));

        ParkingBoy boy2 = new ParkingBoy();
        boy2.manage(new ParkingArea("SP_C", 2));
        boy2.manage(new ParkingArea("SP_D", 3));

        ParkingBoy boy3 = new ParkingBoy();
        boy3.manage(new ParkingArea("SP_E", 2));
        boy3.manage(new ParkingArea("SP_F", 3));

        ParkingBoy boy4 = mock(ParkingBoy.class);
        when(boy4.storeCar(any(Car.class))).thenReturn(new Ticket());

        SelectBoy selectMock = mock(SelectBoy.class);
        when(selectMock.selectParkingBoy(any(Vector.class))).thenReturn(boy4);


        ParkingManager parkingManager = new ParkingManager(selectMock, new SelectByMostFreeRoom());

        parkingManager.manage(boy1);
        parkingManager.manage(boy2);
        parkingManager.manage(boy3);
        parkingManager.manage(boy4);

        parkingManager.manage(new ParkingArea("SP_G", 2));

        // when
        parkingManager.storeCar(new Car("1"));

        // then
        verify(boy4).storeCar(any(Car.class));
    }

    @Test
    public void should_return_properly_indented_report_with_child_reports() throws Exception
    {
        // given
        // given
        SelectBoy selectMock = mock(SelectBoy.class);
        when(selectMock.selectParkingBoy(any(Vector.class))).thenReturn(null);

        ParkingBoy boy1 = new ParkingBoy();
        boy1.manage(new ParkingArea("SP_A", 2));
        boy1.manage(new ParkingArea("SP_B", 3));

        ParkingBoy boy2 = new ParkingBoy();
        boy2.manage(new ParkingArea("SP_C", 2));
        boy2.manage(new ParkingArea("SP_D", 3));

        ParkingBoy boy3 = new ParkingBoy();
        boy3.manage(new ParkingArea("SP_E", 2));
        boy3.manage(new ParkingArea("SP_F", 3));

        ParkingManager parkingManager = new ParkingManager(selectMock, new SelectByMostFreeRoom());

        parkingManager.manage(boy1);
        parkingManager.manage(boy2);
        parkingManager.manage(boy3);

        parkingManager.manage(new ParkingArea("SP_G", 2));

        String expected =
                "Parking Manager - 3 Parking Boys\n" +
                "  Parking Boy - 2 Parking Areas\n" +
                "    Parking Area 'SP_A' - 0/2 Occupied\n" +
                "    Parking Area 'SP_B' - 0/3 Occupied\n" +
                "  Parking Boy - 2 Parking Areas\n" +
                "    Parking Area 'SP_C' - 0/2 Occupied\n" +
                "    Parking Area 'SP_D' - 0/3 Occupied\n" +
                "  Parking Boy - 2 Parking Areas\n" +
                "    Parking Area 'SP_E' - 0/2 Occupied\n" +
                "    Parking Area 'SP_F' - 0/3 Occupied\n" +
                "  Parking Area 'SP_G' - 0/2 Occupied\n";

        // when
        String report = parkingManager.report("");

        // then
        assertThat(report).isEqualTo(expected);
    }
}
