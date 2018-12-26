import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * GUI contains a list of photos and a preview area.
 * As photos are selected, corresponding photo is displayed in the preview area.
 *
 * @author CS121 Instructors
 */
public class PhotoAlbumGUI
{
	/**
	 * Create the JFrame and add the main panel.
	 * @param args not used
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Photo Album GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new PhotoAlbumGUIPanel());
		frame.setPreferredSize(new Dimension(600, 500));
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); //centers frame on screen	
	}
}