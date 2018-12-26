/**
 * Indicates a file formatting exception.
 * @author mvail
 */
@SuppressWarnings("serial")
public class InvalidFileFormatException extends RuntimeException {
	public InvalidFileFormatException(String msg) {
		super(msg);
	}
}
