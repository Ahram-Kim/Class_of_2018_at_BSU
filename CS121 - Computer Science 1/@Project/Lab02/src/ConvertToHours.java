/**
 * I made the program to convert from total seconds to hours. minutes, and seconds.
 */

import java.util.Scanner;

public class ConvertToHours {
    public static void main(String[] args) {
    
    Scanner kbd = new Scanner(System.in);
    
    System.out.println("Enter #seconds : ");
    int totalseconds = kbd.nextInt();
    int hours = totalseconds / 3600;
    int minutes = (totalseconds % 3600) / 60;
    int seconds = (totalseconds % 3600) % 60;
    double fractionalhours = (double)totalseconds / 3600;
    
    System.out.println("hours : " + hours);
    System.out.println("minutes : " + minutes);
    System.out.println("seconds : " + seconds);
    System.out.println("fractional hours : " + fractionalhours);
    
    kbd.close();
    }
}