import java.awt.Point;

/**
 * @author cs121
 *
 */
public class DebuggerTest2 
{
    private static void initializeFleet(int n) {
        Car [] fleet = new Car[n];
        for (int i = 0; i <= fleet.length; i++) {
            Point location = new Point(0, 0);
            fleet[i] = new Car(location, "car"+i);
        }
    }

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
        for (int i = 0; i < array.length; i++)
            array[i] = i;
        
        initializeFleet(10);
		System.out.println("Successfully initialized the fleet of cars!");
    }
}