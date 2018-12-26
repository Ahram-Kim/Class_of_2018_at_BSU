import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;

/**
 * Represents a photo album.
 *
 * @author CS121 Instructors
 * @author Ahram Kim - complete getPhotoArray() method
 */
public class PhotoAlbum
{
	private String name;
	private ArrayList<Photo> photos;

	/**
	 * Creates a new photo album with the given name from the data file located at the
	 * given file path. The album data file should have the format
	 * <pre>
	 * Photo Name 0
	 * filepath 0
	 * Photo Name 1
	 * filepath 1
	 * ...
	 * </pre>
	 * @param name The name of the album.
	 * @param filename The relative path the the file containing the photo album data.
	 */
	public PhotoAlbum(String name, String filename)
	{
		this.name = name;
		this.photos = new ArrayList<Photo>();
		loadFromFile(filename);
	}

	/**
	 * Returns the number of photos in this album.
	 * @return the number of photos in this album.
	 */
	public int getNumPhotos()
	{
		return photos.size();
	}

	/**
	 * Returns a copy of the photos list as an array of Photos.
	 * @return an array of Photos in the album.
	 */
	public Photo[] getPhotoArray()
	{
		Photo[] copy = new Photo[photos.size()];

		// TODO: Use a for-loop to copy the contents from the photos ArrayList to
		// a new Photo[] and return the copied array.
		
		for(int i=0; i<photos.size(); i++) {
			copy[i] = photos.get(i);
		}

		return copy;
	}

	/**
	 * Loads photos from the data in the given file into this photo album.
	 * @param the path of the file to read from.
	 */
	private void loadFromFile(String filename)
	{
		File file = new File(filename);
		if(file.exists()) {
			try {
				Scanner scan = new Scanner(file);
				while(scan.hasNextLine()) {
					String photoName = scan.nextLine().trim();
					File photoFile = new File(scan.nextLine().trim());
					Photo photo = new Photo(photoName, photoFile);
					photos.add(photo);
				}
				scan.close();
			} catch(FileNotFoundException e) {
				System.err.println("Could not read album file: " + e.getMessage());
			}
		} else {
			System.err.println("Album not found:: " + file);
		}
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("------ " + name + " ------\n");
		for(Photo photo : photos) {
			builder.append(photo + "\n");
		}
		return builder.toString();
	}

	public static void main(String[] args)
	{
		PhotoAlbum album = new PhotoAlbum("Test Album", "photos/album.dat");

		Photo[] copy = album.getPhotoArray();
		for(Photo p : copy) {
			System.out.println(p);
		}
	}
}