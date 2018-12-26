import java.io.File;

/**
 * Represents a photo.
 * 
 * @author CS121 Instructors
 */
public class Photo
{
	private String name;
	private File file;

	/**
	 * Creates a new Photo with the given name and file.
	 * 
	 * @param name The name of the photo.
	 * @param file The file representation of where the photo is located.
	 */
	public Photo(String name, File file)
	{
		this.name = name;
		this.file = file;
	}

	/**
	 * Returns the File representation of this photo.
	 * @return the file.
	 */
	public File getFile()
	{
		return this.file;
	}

	@Override
	public String toString()
	{
		return String.format("%-30s %-20s", name, file.getName());
	}
}