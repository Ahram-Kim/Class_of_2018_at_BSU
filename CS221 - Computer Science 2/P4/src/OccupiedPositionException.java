/** 
 * Thrown when an attempt is made to add a trace to an occupied position in a CircuitBoard
 * @author mvail
 */
@SuppressWarnings("serial")
public class OccupiedPositionException extends RuntimeException {
	public OccupiedPositionException(String msg) {
		super(msg);
	}
}
