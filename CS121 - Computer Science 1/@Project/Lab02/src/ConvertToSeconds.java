/**
 * I made the program to convert from hours, minutes, and seconds to total seconds.
 *
 * @author Ahram Kim
 */

import java.util.Scanner; 
 
public class ConvertToSeconds {
    public static void main(String[] args)  {
        // Instantiate the scanner (you only need to do this once.)
        Scanner kbd = new Scanner(System.in);
        
        // use the keyboard scanner to get the next integer from the use and 
        // stor it in your house variable.
        // Do the same for minutes and seconds. 
        
        System.out.println("Enter hours : ");
        int hours = kbd.nextInt();
        System.out.println("Enter minuetes : ");
        int minutes = kbd.nextInt();
        System.out.println("Enter seconds : ");
        int seconds = kbd.nextInt();
        int totalseconds = (hours * 3600) + (minutes * 60) + seconds;
        System.out.println("Total #seconds: " + totalseconds);  
        
        // Declare a variable for the totalSeconds,
        // calculate the resulf, store it in the variable,
        // and print the result.
        
        kbd.close();
    }
}
 