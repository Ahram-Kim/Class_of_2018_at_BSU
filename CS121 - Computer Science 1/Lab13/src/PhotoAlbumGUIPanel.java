import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
 * 1. How did you determine which photo was clicked in the photosquare?
 *    :To use method of public Photo[][] getPhotoSquare(), private Photo[][] photoSquare, 
 *     and private JButtonp[][] photoSquareButtons.
 *     
 * 2. How did you keep the photo square in sync with the JList of photos?
 *    : I add leftPanel in west side and put JList of photos in east side to use borderlayer.
 *    
 * 3. How will this program help you in your final GUI project?
 *    : I heard that the part 'Add photo to album' is really helpful in my final project.
 *      Even if I cannot finish that part, I will try later for my final project.
 *      
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
	
	//lab13
	private Photo[][] photoSquare;
	private JButton[][] photoSquareButtons;
	
	
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
		add(leftPanel, BorderLayout.WEST);

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
		
		// lab13 
		
		photoSquare = album.getPhotoSquare();
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(photoSquare.length, photoSquare.length));
		photoSquareButtons= new JButton[photoSquare.length][photoSquare.length];
		
		for(int row=0; row<photoSquare.length; row++) {
			for(int col=0; col<photoSquare[row].length; col++) {
				photoSquareButtons[row][col] = new JButton();
				photoSquareButtons[row][col].addActionListener(new PhotoSquareListener());
				setButtonIcon(photoSquareButtons[row][col], photoSquare[row][col]);
				gridPanel.add(photoSquareButtons[row][col]);	
			}
		}
		this.add(gridPanel, BorderLayout.CENTER);
		
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

	private class ControlListener implements ActionListener {
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
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
	private void displayPhoto(Photo photo) {
		
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(photo.getFile()));
			imageLabel.setIcon(icon);
		} catch (IOException ex) {
			imageLabel.setText("Image not found :(");
		}
	}
	
	private void setButtonIcon(JButton button, Photo photo) {
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(photo.getFile()));
			button.setIcon(icon);
		} catch (IOException ex) {
			button.setText("Image not found :(");
		}
	}
	
	private class PhotoSquareListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			for(int i=0; i<photoSquare.length; i++) {
				for(int j=0; j<photoSquare.length; j++) {
					if(e.getSource() == photoSquareButtons[i][j]) {
						photoList.setSelectedValue(photoSquare[i][j], true);
						displayPhoto(photoList.getSelectedValue());	
					}
				}
			}
		}
	}

}