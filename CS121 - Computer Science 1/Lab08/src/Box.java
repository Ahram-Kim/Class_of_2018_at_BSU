import java.text.DecimalFormat;

/**
 * 
 * @author akim
 *
 */
public class Box {

	private double width;
	private double height;
	private double depth;
	private double volume;
	private double surfacearea;

	private boolean full;

	/**
	 * Constructor for the box
	 * @param width
	 * @param height
	 * @param depth
	 */
	public Box(double width, double height, double depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		full = false;
	}

	/**
	 * @param full
	 */
	
	public void setFull(boolean full) {
		this.full = full;
	}

	/**
	 * @param width, the width we want the box to have
	 */
	
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * @param height
	 */
	
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * @param depth
	 */
	
	public void setDepth(double depth) {
		this.depth = depth;
	}
	
	/**
	 * @return full
	 */
	
	public boolean getFull() {
		return full;
	}
	
	/**
	 * @return width
	 */
	
	public double getWidth() {
		return width;
	}

	/**
	 * @return height
	 */
	
	public double getHeight() {
		return height;
	}

	/**
	 * @return depth
	 */
	
	public double getDepth() {
		return depth;
	}

	/**
	 * @param volume
	 */
	
	public void setVolume(double volume) {
		this.volume = volume;
	}

	/**
	 * @return volume
	 */
	
	public double getVolume() {
		return (width * height * depth);
	}

	/**
	 * @param surfacearea
	 */
	
	public void setsurfacearea(double surfacearea) {
		this.surfacearea = surfacearea;
	}

	/**
	 * @return surfacearea
	 */
	
	public double getsurfacearea() {
		return ((2 * width * height) + (2 * height * depth) + (2 * depth * width));
	}

	public String toString() {
		
		String s = "";
		if (full == true) {
			s += "A full box's ";
		} else {
			s += "An empty box's ";
		}
		s += String.format(
				"width = %.2f, height = %.2f, depth = %.2f " + "volume = %.2f, surface area = %.2f, "
						+ " its full status as: %s",
				getWidth(), getHeight(), getDepth(), getVolume(), getsurfacearea(), getFull());

		return s;
	}

}
