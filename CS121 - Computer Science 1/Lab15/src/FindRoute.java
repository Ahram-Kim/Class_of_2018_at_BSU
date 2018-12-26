import java.awt.Point;

/**
 * 
 * Find the route for a car from initial location to specified destination.
 * This code doesn't work because of an intentional bug in the Car class!
 * 
 * @author cs121
 *
 */
public class FindRoute 
{
    /** Driver for the FindRoute project.
     * 
     * @param args an array of four integers containing [x coordinate of car, y coordinate of car, 
     * x coordinate of destination, ycoordinate of destination] 
     */
    public static void main(String[] args) 
    {
        if (args.length<5)
        {
            System.err.println( "Usage java FindRoute id Xstart Ystart Xend Yend");
            System.exit(1);
        }
        
        String carId = args[0];
        int xCar = Integer.parseInt(args[1]);
        int yCar = Integer.parseInt(args[2]);
        int xDestination = Integer.parseInt(args[3]);
        int yDestination = Integer.parseInt(args[4]);
    
        Car car = new Car(new Point(xCar, yCar), carId);
        car.setDestination(new Point(xDestination, yDestination));
        
        System.out.println();
        System.out.println(car);
        System.out.println();

        int distance = Math.abs(xCar - xDestination) + Math.abs(yCar - yDestination);
        int count = 0;
        while (!car.isParked()); {
            System.out.println(car.nextMove());
            count++;
            if (count > 2 * distance) {
                System.err.println("Oops!!! there is an error in the car's driving...it is taking too long");
                System.exit(1);
            }
        }
    }
}