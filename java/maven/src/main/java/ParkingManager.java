import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by ydliu on 9/3/14.
 */
public class ParkingManager extends SmartParkingBoy
{
    protected Vector<ParkingBoy> parkingBoys;
    protected Map<Ticket, ParkingBoy> ticketBoyMap;
    SelectBoy parkingBoySelector;

    public ParkingManager(SelectBoy boySelector, Comparator<ParkingArea> parkingAreaSelector)
    {
        super(parkingAreaSelector);
        parkingBoySelector = boySelector;
        parkingBoys = new Vector<ParkingBoy>();
        ticketBoyMap = new HashMap<Ticket, ParkingBoy>();
    }

    public void manage(ParkingBoy parkingBoy)
    {
        parkingBoys.add(parkingBoy);
    }

    @Override
    public Ticket storeCar(Car car)
    {
        ParkingBoy boy = parkingBoySelector.selectParkingBoy(parkingBoys);

        if (boy != null)
        {
            Ticket ticket = boy.storeCar(car);
            ticketBoyMap.put(ticket, boy);
            return ticket;
        }

        return super.storeCar(car);
    }

    protected ParkingBoy selectBoyByTicket(Ticket ticket)
    {
        if (ticketBoyMap.containsKey(ticket))
        {
            return ticketBoyMap.get(ticket);
        }

        return null;
    }

    @Override
    public Car fetchCar(Ticket ticket)
    {
        ParkingBoy boy = selectBoyByTicket(ticket);
        if (boy != null)
        {
            ticketBoyMap.remove(ticket);
            return boy.fetchCar(ticket);
        }

        return super.fetchCar(ticket);
    }

    @Override
    public String report(String indentSpace)
    {
        String self = String.format("%sParking Manager - %d Parking Boys\n", indentSpace, parkingBoys.size());
        String boyReports = "";
        String nextIndent = indentSpace + "  ";
        for (ParkingBoy boy : parkingBoys)
        {
            boyReports += boy.report(nextIndent);
        }
        return self + boyReports + reportParkingAreas(nextIndent);
    }
}
