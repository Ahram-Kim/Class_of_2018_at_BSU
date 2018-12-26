import java.awt.Point;
import java.util.Random;

/** A class to represent a car. 
 * 
 * @author cs121
 *
 */
public class Car 
{
    private Point currentLocation, currentDestination;  
    private String id;
    private boolean atDestination;
    
    /** Create a car with an identifying string and a location
     * 
     * @param location the current location of the car
     * @param id identifier for car
     */
    public Car(Point location, String id) {
        Point tmp = new Point(location);
        currentLocation = tmp;
        currentDestination = tmp;
        atDestination = true;
        this.id = id;       
    }
    
    
    /** Get the destination for the car
     * @param destination the location the car should drive to
     */
    void setDestination(Point destination) {
        currentDestination = new Point(destination);
        atDestination = destination.equals( currentLocation);
    }

    
    /**
     * Return the next direction to move. Uses a simple straight line route.
     * @return
     */
    public Direction nextMove()
    {
        Direction nextMove = Direction.NOWHERE;
        if (atDestination)
            nextMove = Direction.NOWHERE;
        
        if (currentLocation.x < currentDestination.x) {
            nextMove = Direction.EAST;
            currentLocation.x--;
        } else if (currentLocation.x > currentDestination.x) {
            nextMove = Direction.WEST;
            currentLocation.x++;
        } else if (currentLocation.y < currentDestination.y) { 
            nextMove = Direction.NORTH;
            currentLocation.y++;
        } else if (currentLocation.y > currentDestination.y) {
            nextMove = Direction.SOUTH;
            currentLocation.y--;
        }
    
        atDestination = currentLocation.equals(currentDestination);
        return nextMove;
    }
    
    
    /** Determine whether the car is at its current location
     * @return whether the car is parked (at its destination)
     */
    boolean isParked() {
        return atDestination;
    }

    /** Get the car's current location
     * @return the currentLocation of the car
     */
    public Point getLocation() {
        return new Point(currentLocation);
    }

    /** Get the car's destination
     * @return the destination of the car
     */
    public Point getDestination() {
        return currentDestination;
    }

    /** Get the car's identifier
     * @return the identifier for the Car
     */
    public String getId() {
        return id;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Car [id =  " + id + ", location = [x=" + currentLocation.x + ", y=" + currentLocation.y 
                + "], destination = [x=" + currentDestination.x + ", y=" + currentDestination.y + "]]";
    }
}