import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyTunesGUIPanel extends JPanel {

	private PlayList myTunes;
	private PlayList numSongs;
	private PlayList totalTime;
	private JList<Song> songList;
	private JButton upButton;
	private JButton downButton;
	private JButton addSong;
	private JButton removeSong;
	private JButton prevSong;
	private JButton nextSong;
	private JButton play_stop;
	private JLabel currentSongName;
	private JLabel currentSongArtist;
	private Song[][] songSquare;
	private JButton[][] songSquareButtons;

	public MyTunesGUIPanel() {

		setLayout(new BorderLayout());

		JPanel scrollPanel = new JPanel();
		scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));

		add(scrollPanel, BorderLayout.CENTER);
		add(eastPanel, BorderLayout.EAST);
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);

		myTunes = new PlayList(new File("playlist.txt"));
		System.out.println(myTunes);

		songList = new JList<Song>();
		songList.setListData(myTunes.getSongArray());
		songList.setSelectedIndex(0);
		songList.addListSelectionListener(new songListListener());

		JScrollPane scrollPane = new JScrollPane(songList);
		scrollPanel.add(scrollPane);

		JPanel currentSongPlay = new JPanel();
		currentSongName = new JLabel();
		currentSongArtist = new JLabel();
		currentSongPlay.add(currentSongName);
		currentSongPlay.add(currentSongArtist);
		northPanel.add(currentSongPlay);

		displaySong(songList.getSelectedValue());

		JPanel controlPanel = new JPanel();
		JPanel playPanel = new JPanel();
		JPanel add_removePanel = new JPanel();

		upButton = new JButton("Up");
		downButton = new JButton("Down");
		upButton.addActionListener(new ControlListener());
		downButton.addActionListener(new ControlListener());
		controlPanel.add(upButton);
		controlPanel.add(downButton);
		eastPanel.add(upButton);
		eastPanel.add(downButton);

		addSong = new JButton("add");
		removeSong = new JButton("remove");
		addSong.addActionListener(new ControlListener());
		removeSong.addActionListener(new ControlListener());
		add_removePanel.add(addSong);
		add_removePanel.add(removeSong);
		southPanel.add(add_removePanel);

		prevSong = new JButton("prev");
		play_stop = new JButton("play/stop");
		nextSong = new JButton("next");
		prevSong.addActionListener(new ControlListener());
		play_stop.addActionListener(new ControlListener());
		nextSong.addActionListener(new ControlListener());
		playPanel.add(prevSong);
		playPanel.add(play_stop);
		playPanel.add(nextSong);
		northPanel.add(playPanel);

		songSquare = myTunes.getMusicSquare();
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(songSquare.length, songSquare.length));
		songSquareButtons = new JButton[songSquare.length][songSquare.length];

		for (int row = 0; row < songSquare.length; row++) {
			for (int col = 0; col < songSquare[row].length; col++) {
				songSquareButtons[row][col] = new JButton();
				songSquareButtons[row][col].setPreferredSize(new Dimension(100, 100));
				songSquareButtons[row][col].addActionListener(new songSquareListener());
				gridPanel.add(songSquareButtons[row][col]);
			}
		}
		add(gridPanel, BorderLayout.WEST);

	}
	
	
	

	private class songListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			displaySong(songList.getSelectedValue());
		}
	}

	private class ControlListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			int index = songList.getSelectedIndex();

			if (event.getSource() == downButton) {
				index--;
			} else if (event.getSource() == upButton) {
				index++;
			}

			if (index >= songList.getModel().getSize() - 1) {
				songList.setSelectedIndex(0);
			} else if (index <= songList.getModel().getSize()) {
				songList.setSelectedIndex(songList.getModel().getSize() - 1);
			}

			songList.setSelectedIndex(index);
		}
	}

	private void displaySong(Song song) {

		songList = new JList<Song>();
		songList.setListData(myTunes.getSongArray());
		songList.setSelectedIndex(0);
		songList.addListSelectionListener(new songListListener());
		
	}
	
	private class songSquareListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < songSquare.length; i++) {
				for (int j = 0; j < songSquare.length; j++) {
					if (e.getSource() == songSquareButtons[i][j]) {
						songList.setSelectedValue(songSquare[i][j], true);
						displaySong(songList.getSelectedValue());
					}
				}
			}
		}
	}
	
	private class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			showForm();
		}
	}
	
	private void showForm() {
		JPanel addSongPanel = new JPanel();
		addSongPanel.setLayout(new BoxLayout(addSongPanel, BoxLayout.Y_AXIS));
		
		JTextField titleField = new JTextField(20);
		JTextField ageField = new JTextField(5);
		
		addSongPanel.add(new JLabel("Song Title: "));
		addSongPanel.add(titleField);
		addSongPanel.add(new JLabel(""));
		
	}

}
