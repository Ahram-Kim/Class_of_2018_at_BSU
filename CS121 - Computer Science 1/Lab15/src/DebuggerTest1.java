import java.awt.Point;
import java.util.ArrayList;

/**
 * 
 * Go to Run-->Debug Configurations and select the Stop in main option. 
 * The start the debugger perspective by selecting Run --> Debug (or F11)
 * 
 * @author cs121
 *
 */
public class DebuggerTest1 
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        int n = 1;
        double x = 2.713;
        n = n + 5;
        x = Math.sqrt(x);
        
        int [] array = new int[10];
        for (int i=0; i<array.length; i++)
            array[i] = i;
        
        ArrayList<Car> garage = new ArrayList<Car>();
        Car car1 = new Car(new Point(0,0), "car1");
        garage.add(car1);
        Car car2 = new Car(new Point(0, 1), "car2");
        garage.add(car2);
    }
}