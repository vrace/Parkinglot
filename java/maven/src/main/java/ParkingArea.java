import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ydliu on 9/1/14.
 */

public class ParkingArea implements Reportable
{
    private String name;
    private int capacity;
    private HashMap<Ticket, Car> occupied;

    public ParkingArea(String name, int capacity)
    {
        this.name = name;
        this.capacity = capacity;
        occupied = new HashMap<Ticket, Car>();
    }

    public Ticket store(Car car)
    {
        if (occupied.size() < capacity)
        {
            Ticket ticket = new Ticket();
            occupied.put(ticket, car);
            return ticket;
        }

        return null;
    }

    public Car fetch(Ticket ticket)
    {
        if (occupied.containsKey(ticket))
        {
            Car car = occupied.get(ticket);
            occupied.remove(ticket);
            return car;
        }

        return null;
    }

    public String getName()
    {
        return name;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public int getFreeRoom()
    {
        return capacity - occupied.size();
    }

    @Override
    public String report()
    {
        return String.format("Parking Area '%s' - %d/%d Occupied",
                name, occupied.size(), capacity);
    }

    @Override
    public ArrayList<Reportable> getSubNodes()
    {
        return new ArrayList<Reportable>();
    }
}
