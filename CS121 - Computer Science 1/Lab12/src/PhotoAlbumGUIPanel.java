import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Displays a JList of Photos and a preview of the photo.
 * This class manages layout of controls and also handles events.
 *
 * @author CS121 Instructors: Luke Hindman
 * @author Ahram Kim
 * 
 * Reflection
 * 1. How did you select the correct photo in the JList when you used the left 
 *    and right buttons to move through the photo album?
 *    : if(event.getSource() == prevButton) {
				index--;
			} else if(event.getSource() == nextButton) {
				index++;}
			if(index>=photoList.getModel().getSize()-1){
				photoList.setSelectedIndex(0);
			} else if(index<=photoList.getModel().getSize()) {
				photoList.setSelectedIndex(photoList.getModel().getSize()-1);
			}
 *    
 * 2. How will this program help you in your final GUI project?
 * 	  : I recognized how to use JList, JButton, JPanel and JScrollPane.
 *    : That was a perfect this program for my final project.
 * 
 */
@SuppressWarnings("serial")
public class PhotoAlbumGUIPanel extends JPanel
{
	/** The data representing the list of photos in the album (the "model") */
	private PhotoAlbum album;

	/** The GUI representation of the list of photos in the album (the "view") */
	private JList<Photo> photoList;

	private JLabel imageLabel;  // Displays the image icon inside of the preview panel
	private JButton nextButton; // Selects the next image in the photoList
	private JButton prevButton; // Selects the previous image in the photoList */

	/**
	 * Instantiates the photo album and adds all of the components to the JPanel.
	 */
	public PhotoAlbumGUIPanel()
	{
		// The entire frame will have a BorderLayout (we will be adding more to it
		// in the next lab).
		setLayout(new BorderLayout());
		
		// All of your work in this lab will go in the left panel. 
		// The right panel will be added in the next lab.
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		add(leftPanel);

		// Instantiate the album object and print it to the command line
		//      to make sure it worked.
		album = new PhotoAlbum("Boise", "photos/album.dat");
		System.out.println(album);

		// TODO: Instantiate the JList<Photo> photoList object (declared above) and
		//      set the list data to album.getPhotoArray().
		
		//      Set the selected index of the photoList to position 0 to select the
		//      first photo by default.

		photoList = new JList<Photo>();
		photoList.setListData(album.getPhotoArray());
		photoList.setSelectedIndex(0);
		
		// TODO: Add a ListSelectionListener to the photoList

		photoList.addListSelectionListener(new PhotoListListener());
		
		// TODO: Create a JScrollPane containing the photoList.

		JScrollPane scrollPane = new JScrollPane(photoList);
		
		// TODO: Add the scrollPane to the leftPanel.
		
		leftPanel.add(scrollPane);
				
		// TODO: Create a new preview sub-panel.
		
		JPanel previewPanel = new JPanel();
		
		// TODO: Instantiate the imageLabel (declared above) as an empty JLabel().

		imageLabel = new JLabel();
		
		// TODO: Add the imageLabel to the previewPanel and add the previewPanel
		//      to the leftPanel.
		
		previewPanel.add(imageLabel);
		leftPanel.add(previewPanel);
		
		// TODO: Use the displayPhoto() method (provided below) to display the
		//      photo currently selected in the photoList.

		displayPhoto(photoList.getSelectedValue());
		
		// TODO: Update the valueChanged method of the PhotoListListener (below) to
		//       display the selected photo whenever you click a new photo in the list.
				
		// TODO: Create a sub-panel for control buttons.

		JPanel controlPanel = new JPanel();
				
		// TODO: Initialize the prevButton and nextButton and add the same
		//      ControlListener to both.

		prevButton = new JButton("Preview");
		nextButton = new JButton("Next");
		prevButton.addActionListener(new ControlListener());
		nextButton.addActionListener(new ControlListener());
		
		// TODO: Add both buttons to the controlPanel and add the controlPanel
		//       to the leftPanel.
		
		controlPanel.add(prevButton);
		controlPanel.add(nextButton);
		leftPanel.add(controlPanel);
		
	}

	private class PhotoListListener implements ListSelectionListener
	{
		/* (non-Javadoc)
		 * @see java.awt.event.ListSelectionListener#valueChanged(java.awt.event.ListSelectionEvent)
		 */
		@Override
		public void valueChanged(ListSelectionEvent event)
		{
			//TODO: Use the displayPhoto() method (provided below) to display the
			// photo currently selected in the photoList.
			
			displayPhoto(photoList.getSelectedValue());
			
		}
	}

	private class ControlListener implements ActionListener
	{
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent event)
		{
			// Find index of photo that is currently selected.
			int index = photoList.getSelectedIndex();

			// TODO: Update the index depending on which button was clicked.
			
		
			if(event.getSource() == prevButton) {
				index--;
			} else if(event.getSource() == nextButton) {
				index++;
			}
			
			if(index>=photoList.getModel().getSize()-1){
				photoList.setSelectedIndex(0);
			} else if(index<=photoList.getModel().getSize()) {
				photoList.setSelectedIndex(photoList.getModel().getSize()-1);
			}
			
			photoList.setSelectedIndex(index); 
		}
	}

	/**
	 * Updates the photo on the preview panel.
	 * @param photo The photo to display.
	 */
	private void displayPhoto(Photo photo)
	{
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(photo.getFile()));
			imageLabel.setIcon(icon);
		} catch (IOException ex) {
			imageLabel.setText("Image not found :(");
		}
	}

}