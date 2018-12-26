

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyTunesGUIPanel extends JPanel {

	private static final int MILLIS_PER_SEC = 1000;
	private PlayList myTunes;
	private JList<Song> songList;
	private JPanel scrollPanel;
	private JPanel eastPanel;
	private JPanel northPanel;
	private JPanel gridPanel;
	private JPanel playSongPanel;
	private JPanel currentSongPanel;
	private JButton upButton;
	private JButton downButton;
	private JButton addSongButton;
	private JButton removeSongButton;
	private JButton prevSongButton;
	private JButton nextSongButton;
	private JButton playSongButton;
	private JButton stopSongButton;
	private String currentSongTitle;
	private String currentSongArtist;
	private int playListSong;
	private double totalMinutes;
	private JLabel countAndTime;
	private Song[][] songSquare;
	private JButton[][] songSquareButtons;
	private Timer timer;
	private JLabel playingSongLabel;
	
	public MyTunesGUIPanel() {

		setLayout(new BorderLayout());

		scrollPanel = new JPanel();
		scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
		eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
	
		add(scrollPanel, BorderLayout.CENTER);
		add(eastPanel, BorderLayout.EAST);
		add(northPanel, BorderLayout.NORTH);

		myTunes = new PlayList(new File("playlist.txt"));
		System.out.println(myTunes);

		songList = new JList<Song>();
		songList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		songList.setListData(myTunes.getSongArray());
		songList.addListSelectionListener(new songListListener());
		
		JScrollPane scrollPane = new JScrollPane(songList);
		scrollPanel.setLayout(new BorderLayout());
		scrollPanel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel addRemovePanel = new JPanel();
		addSongButton = new JButton("add Song");
		addSongButton.addActionListener(new addRemoveControlListener());
		addSongButton.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
		removeSongButton = new JButton("remove Song");
		removeSongButton.addActionListener(new addRemoveControlListener());
		removeSongButton.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
		addRemovePanel.add(addSongButton);
		addRemovePanel.add(removeSongButton);
		scrollPanel.add(addRemovePanel, BorderLayout.SOUTH);

		JPanel upDownPanel = new JPanel();
		upButton = new JButton(new ImageIcon("images/move-up-16.gif"));
		upButton.addActionListener(new ControlListener());
		downButton = new JButton(new ImageIcon("images/move-down-16.gif"));
		downButton.addActionListener(new ControlListener());
		upDownPanel.add(upButton);
		upDownPanel.add(downButton);
		eastPanel.add(upButton, BorderLayout.CENTER);
		eastPanel.add(downButton, BorderLayout.CENTER);
		
		initPlaySongPanel();
	
		initCurrentSongPanel();

		initGridPanel();
		
		timer = new Timer(MILLIS_PER_SEC, new TimerListener());

	}
	
	
	
	private void initPlaySongPanel() {
		
		playSongPanel = new JPanel();
		playingSongLabel = new JLabel("( Nothing ) by ( Nobody ) ");
		
		if(myTunes.getPlaying()!=null) {
			currentSongTitle = myTunes.getPlaying().getTitle();
			currentSongArtist = myTunes.getPlaying().getArtist();
			playSongPanel.add(new JLabel(currentSongTitle + " by " + currentSongArtist));
		}
		else{
			playSongPanel.add(playingSongLabel);
		}
		
		playSongPanel.setBorder(BorderFactory.createTitledBorder("Now Playing"));
		prevSongButton = new JButton(new ImageIcon("images/media-skip-backward-32.gif"));
		prevSongButton.addActionListener(new ControlListener());
		playSongButton = new JButton(new ImageIcon("images/play-32.gif"));
		playSongButton.addActionListener(new playStopControlListener());
		stopSongButton = new JButton(new ImageIcon("images/stop-32.gif"));
		stopSongButton.addActionListener(new playStopControlListener());
		nextSongButton = new JButton(new ImageIcon("images/media-skip-forward-32.gif"));
		nextSongButton.addActionListener(new ControlListener());
		playSongPanel.add(prevSongButton);
		playSongPanel.add(playSongButton);
		playSongPanel.add(stopSongButton);
		playSongPanel.add(nextSongButton);
		northPanel.add(playSongPanel);
		this.revalidate();
	}
	
	
	private void initCurrentSongPanel() {
		currentSongPanel = new JPanel();
		playListSong = myTunes.getNumSongs();
		totalMinutes = myTunes.getTotalPlayTime()/60;

		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		String totalMinutes2 = decimalFormat.format(totalMinutes);
		
		countAndTime = new JLabel(playListSong + " Songs ~ " + totalMinutes2 + " Minutes");
		currentSongPanel.add(countAndTime);

		scrollPanel.add(currentSongPanel, BorderLayout.NORTH);
		
		
	}
	
	
	private void initGridPanel() {
		
		songSquare = myTunes.getMusicSquare();
		
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(songSquare.length, songSquare.length));
		songSquareButtons = new JButton[songSquare.length][songSquare.length];
		
		for (int row = 0; row < songSquare.length; row++) {
			for (int col = 0; col < songSquare.length; col++) {
				songSquareButtons[row][col] = new JButton();
				songSquareButtons[row][col].setPreferredSize(new Dimension(125, 100));
				songSquareButtons[row][col].addActionListener(new songSquareListener());
				songSquareButtons[row][col].setText(songSquare[row][col].getTitle());
				songSquareButtons[row][col].setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
				gridPanel.add(songSquareButtons[row][col]);
			}
		}
		add(gridPanel, BorderLayout.WEST);
		this.revalidate();
	}
	
	

	private class songListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			//displaySong(songList.getSelectedValue());
			
		}
	}
	
	
	private class playStopControlListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			timer = new Timer(songList.getSelectedValue().getPlayTime()*MILLIS_PER_SEC, new TimerListener());
			timer.start();
			
			if(event.getSource() == playSongButton) {
				if(timer.isRunning()) {
					Song s = songList.getSelectedValue();
					myTunes.playSong(s);
					System.out.println(s.toString() + " &^&^&"); //debug
					playingSongLabel.setText( songList.getSelectedValue().getTitle() + " by " + songList.getSelectedValue().getArtist());

				}	
				
			} else if(event.getSource() == stopSongButton) {
					timer.stop();
					myTunes.stop();
					playingSongLabel.setText(" ( Nothing ) by ( Nobody ) ");
			}	
		}
	}
	
	
	private class TimerListener implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			timer.stop();
			myTunes.stop();
		}
	}
	

	private class ControlListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			
			int index = songList.getSelectedIndex();
		
			if (event.getSource() == prevSongButton) {
				index--;	
			} else if (event.getSource() == nextSongButton) {
				index++;
			} else if (event.getSource() == upButton) {
				index = myTunes.moveUp(index);
				songList.setListData(myTunes.getSongArray());
				songList.setSelectedIndex(index);
				remove(gridPanel);
				initGridPanel();
			} else if (event.getSource() == downButton) {
				index = myTunes.moveDown(index);
				songList.setListData(myTunes.getSongArray());
				songList.setSelectedIndex(index);	
				remove(gridPanel);
				initGridPanel();
			}
			
			
			if (index >= songList.getModel().getSize() - 1) {
				songList.setSelectedIndex(0);
			} else if (index <= songList.getModel().getSize()) {
				songList.setSelectedIndex(songList.getModel().getSize() - 1);
			}
			songList.setSelectedIndex(index);
		}	
	}
	
	private class addRemoveControlListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel formInputPanel = new JPanel();
			formInputPanel.setLayout(new BoxLayout(formInputPanel, BoxLayout.Y_AXIS));
			
			JTextField titleField = new JTextField();
			JTextField artistField = new JTextField();
			JTextField fileField = new JTextField();
			JTextField timeField = new JTextField();

			formInputPanel.add(new JLabel("Title: "));
			formInputPanel.add(titleField);
			formInputPanel.add(new JLabel("Artist: "));
			formInputPanel.add(artistField);
			formInputPanel.add(new JLabel("File: "));
			formInputPanel.add(fileField);
			formInputPanel.add(new JLabel("Playtime(mm:ss): "));
			formInputPanel.add(timeField);
			
			JPanel sureRemove = new JPanel();
			sureRemove.setLayout(new BoxLayout(sureRemove, BoxLayout.Y_AXIS));
			sureRemove.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			sureRemove.add(new JLabel("Are you sure to remove?"));
			
			if(e.getSource()==addSongButton) {
				
				int result = JOptionPane.showConfirmDialog(null, formInputPanel, "Add Song",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
				if(result == JOptionPane.OK_OPTION) {
					String title = titleField.getText();
					String artist = artistField.getText();
					String file = fileField.getText();
					String playTime = timeField.getText();
					
					int index = playTime.indexOf(':');
					String mm = playTime.substring(0, index);
					String ss = playTime.substring(index + 1, index + 3);
					int minutes = Integer.parseInt(mm);
					int seconds = Integer.parseInt(ss);
					int songLength = (minutes * 60) + seconds;
				
					Song a = new Song(title, artist, songLength, file);
					myTunes.addSong(a);
					songList.setListData(myTunes.getSongArray());
				
					initCurrentSongPanel();
				}
				remove(gridPanel);
				initGridPanel();
				
				} else if(e.getSource()==removeSongButton) {
					
					int result = JOptionPane.showConfirmDialog(null, sureRemove, "Remove Song",
							JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
					
					if(result == JOptionPane.YES_OPTION) {
						myTunes.removeSong(songList.getSelectedIndex());
						songList.setListData(myTunes.getSongArray());
						
						initCurrentSongPanel();
					} 
					remove(gridPanel);
					initGridPanel();
				}
			
		}
	}

	
	private class songSquareListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < songSquare.length; i++) {
				for (int j = 0; j < songSquare.length; j++) {
					if (e.getSource() == songSquareButtons[i][j]) {
						songList.setSelectedValue(songSquare[i][j], true);
						//displaySong(songList.getSelectedValue());
					}
				}
			}
		}
	}
//	
//	private Color getHeatMapColor(int plays) {
//		double minPlays = 0, maxPlays=MAX_PLAYS;
//		double value = (plays-minPlays)/(maxPlays-minPlays);
//		
//		Color[] colors = {Color.cyan, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED};
//		int index1, index2;
//		float dist = 0;
//		
//		if(value<=0) {
//			index1=index2=0;
//		}else if(value>=1) {
//			index1=index2=colors.length-1;
//		} else {
//			value = value*(colors.length-1);
//			index1=(int)Math.floor(value);
//			index2=index1+1;
//			dist=(float)value-index1;
//		}
//		int r = (int)((colors[index2].getRed() - colors[index1].getRed()) * dist)
//                 + colors[index1].getRed();
//        int g = (int)((colors[index2].getGreen() - colors[index1].getGreen()) * dist)
//                 + colors[index1].getGreen();
//        int b = (int)((colors[index2].getBlue() - colors[index1].getBlue()) * dist)
//                 + colors[index1].getBlue();
//
//       return new Color(r, g, b);
//		
//	}
	
	
	

}

