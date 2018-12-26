import java.applet.AudioClip;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Ahram Kim
 *
 */
public class PlayList implements MyTunesPlayListInterface {


	ArrayList<Song> songList;
	private String name;
	private Song playing;

	/**
	 * @param name;
	 *            the name of the playList
	 */
	public PlayList(String name) {
		playing = null;
		this.name = name;
		songList = new ArrayList<Song>();
	}

	public PlayList(File file) {
		playing = null;
		songList = new ArrayList<Song>();

		try {
			Scanner scan = new Scanner(file);
			name = scan.nextLine().trim();

			while (scan.hasNextLine()) {
				String title = scan.nextLine().trim();
				String artist = scan.nextLine().trim();
				String playtime = scan.nextLine().trim();
				String songPath = scan.nextLine().trim();

				int colon = playtime.indexOf(':');
				int minutes = Integer.parseInt(playtime.substring(0, colon));
				int seconds = Integer.parseInt(playtime.substring(colon + 1));
				int playtimesecs = (minutes * 60) + seconds;

				Song song = new Song(title, artist, playtimesecs, songPath);
				this.addSong(song);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.err.println("Failed to load playlist. " + e.getMessage());
		}
	}

	/**
	 * @param name;
	 *            the name of the playList
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param song;
	 *            the song of the playList
	 */
	public void addSong(Song song) {
		songList.add(song);
	}

	/**
	 * @param index;
	 *            the removed song of the playList
	 * @return null;
	 */
	public Song removeSong(int index) {
		if (index >= 0 && index < songList.size()) {
			return songList.remove(index);
		} else {
			return null;
		}
	}

	/**
	 * @return songList.size
	 */
	public int getNumSongs() {
		return songList.size();
	}

	/**
	 * @return totalPlayTime
	 */
	public int getTotalPlayTime() {
		int totalPlayTime = 0;
		for (int i = 0; i < songList.size(); i++) {
			totalPlayTime += songList.get(i).getPlayTime();
		}
		return totalPlayTime;
	}

	/**
	 * @param index;
	 *            the get song of the playList.
	 * @return null;
	 */
	public Song getSong(int index) {
		if (index >= 0 && index < songList.size()) {
			return songList.get(index);
		} else {
			return null;
		}
	}

	/**
	 * @param index;
	 *            the play song of the playList
	 */
	public void playSong(int index) {
		if (getSong(index) != null) {
			getSong(index).play();
			playing = getSong(index);
		}
	}

	/**
	 * @return info
	 */
	public String getInfo() {
		if (songList.size() == 0) {
			return "There are no songs.";
		}
		String info = "";
		info += String.format("The average play time is: %.2f\n" + "seconds", averagePlayTime());
		info += String.format("The shortest song is: %s\n", shortest());
		info += String.format("The longest song is: %s\n", longest());
		info += String.format("Total play time: %s", getTotalPlayTime());
		return info;
	}

	/**
	 * @return avgTime
	 */
	private double averagePlayTime() {
		double avgTime = 0;
		for (int i = 0; i < songList.size(); i++) {
			avgTime += songList.get(i).getPlayTime();

		}
		return avgTime / songList.size();

	}

	/**
	 * @return shortsong
	 */
	private Song shortest() {
		int shortest = 11111110;
		Song shortsong = null;
		for (int i = 0; i < songList.size(); i++) {
			if (songList.get(i).getPlayTime() < shortest) {
				shortest = songList.get(i).getPlayTime();
				shortsong = songList.get(i);
			}
		}
		return shortsong;
	}

	/**
	 * @return longsong
	 */
	private Song longest() {
		int longest = 0;
		Song longsong = null;
		for (int i = 0; i < songList.size(); i++) {
			if (songList.get(i).getPlayTime() > longest) {
				longest = songList.get(i).getPlayTime();
				longsong = songList.get(i);
			}
		}
		return longsong;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return playing
	 */
	public Song getPlaying() {
		return playing;
	}

	/**
	 * @return songList
	 */
	public ArrayList<Song> getsongList() {
		return songList;
	}

	public String toString() {
		if (songList.size() == 0) {

			String s = "";
			s += "------------------\n";
			s += name + " (" + songList.size() + " songs)\n";
			s += "------------------\n";
			s += "There are no songs.\n";
			s += "------------------\n";

			return s;

		} else {
			String s1 = "";
			s1 += "------------------\n";
			s1 += name + " (" + songList.size() + " songs)\n";
			s1 += "------------------\n";
			for (int i = 0; i < songList.size(); i++) {
				s1 += ("(" + i + ") ") + songList.get(i) + "\n";
			}
			s1 += "------------------\n";
			return s1;
		}
	}

	@Override
	public ArrayList<Song> getSongList() {
		
		this.songList = new ArrayList<Song>();
		return null;
	}

	@Override
	public void playSong(Song song) {
		
		stop();
		playing = song;
		song.play();
		
	}
	
	@Override
	public void stop() {
	
		if(playing !=null) {
			playing.stop();
		}
		playing=null;
	}

	@Override
	public Song[] getSongArray() {
		

		Song[] copy = new Song[songList.size()];

		for (int i = 0; i < songList.size(); i++) {
			copy[i] = songList.get(i);
		}
		return copy;
	}

	@Override
	public int moveUp(int index) {
		
		Song remove = songList.remove(index);
		if(index == 0) {
			songList.add(songList.size(), remove);
			return songList.size()-1;
		} else {
			songList.add(index-1, remove);
			return index-1;
		}		
	}

	@Override
	public int moveDown(int index) {
		
		Song remove = songList.remove(index);
		 if(index == songList.size()) {
			 songList.add(0, remove);
			 return 0;
		 } else {
			 songList.add(index+1, remove);
			 return index+1;
		 }
	}

	@Override
	public Song[][] getMusicSquare() {
		
		int x = (int) Math.ceil(Math.sqrt(songList.size()));

		Song[][] songSquare = new Song[x][x];

		for (int row = 0; row < songSquare.length; row++) {
			for (int col = 0; col < songSquare[row].length; col++) {
				songSquare[row][col] = songList.get((col + (x * row)) % songList.size());
			}
		}
		return songSquare;
	}
	
}