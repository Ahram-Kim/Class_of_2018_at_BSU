import java.util.ArrayList;
import java.util.Random;


/**
 * Use an ArrayList to store a collection of objects and use a for-each loop to process the objects.
 * 
 * @author amit
 *
 */
public class ArrayListExercise {

    public static void main(String[] args)
    {
        Random rand = new Random();
        final int RADIUS_MAX = 100;
        //TODO: declare a constant for the number of spheres NUM_SPHERES and 
        //      initialize it appropriately
        
        int NUM_SPHERES=10;
        
        //TODO: Declare the ArrayList to hold the Sphere objects
        
        ArrayList<Sphere> spheres = new ArrayList<Sphere>();
        
        //TODO: Create the spheres using a normal for loop and add them to an ArrayList<Sphere>
        
        for (int i=0; i<NUM_SPHERES; i++) {
        	Sphere sphereobject= new Sphere(rand.nextInt(RADIUS_MAX));
        	spheres.add(sphereobject);
        	}
       
        //TODO: Convert to a for-each loop to print out the spheres
        
        for (Sphere s: spheres)  {
        	System.out.println(s);
        }
        
        /*
        System.out.println("Sphere 1: \t" + spheres + "\n");
        System.out.println("Sphere 2: \t" + spheres + "\n");
        System.out.println("Sphere 3: \t" + spheres + "\n");
        System.out.println("Sphere 4: \t" + spheres + "\n");
        */
       
        //TODO: Convert to a for-each loop to find the volume of the smallest sphere 
       
        Sphere temp = new Sphere (1000000);
        
        for (Sphere s: spheres)  {
        	if(s.getVolume()<temp.getVolume()) {
        			temp=s;
        	}
        }
        System.out.println("Volume of the smallest sphere: " + temp.getVolume());
        
        /*
        double min1 = Math.min(s.getVolume(), s.getVolume());
        double min2 = Math.min(s.getVolume(), s.getVolume());
   		double min = Math.min(min1, min2);
        System.out.printf("Volume of the smallest sphere: %.2f\n", min);
        */
    }
}