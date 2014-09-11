import java.util.ArrayList;

/**
 * Created by ydliu on 9/4/14.
 */
public interface Reportable
{
    public String report();
    public ArrayList<Reportable> getSubNodes();
}
