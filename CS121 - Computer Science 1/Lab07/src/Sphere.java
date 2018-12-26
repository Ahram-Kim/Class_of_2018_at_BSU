/**
 * Represents a sphere.
 * @author seong hoon son, ahram kim
 */
public class Sphere {
	private double radius;
	
	/**
	 * Constructor
	 * @param radius the sphere's radius
	 */
	public Sphere(double radius) {
		this.radius = radius;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * @return the surface area
	 */
	public double getSurfaceArea() {
		return 4.0 * Math.PI * Math.pow(radius, 2);
	}
	
	/**
	 * @return the volume
	 */
	public double getVolume() {
		return 4.0 / 3.0* Math.PI* Math.pow(radius, 3);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.format("radius: %8.2f\t surface area: %8.2f\t volume: %8.2f\t",
				radius, getSurfaceArea(), getVolume());
	}
}