
import java.util.ArrayList;

/**
 * @author Ahram Kim
 *
 */
public class PlayList {

		ArrayList<Song> songList;
		private String name;
		private Song playing;
		

		/**
		 * @param name; the name of the playList
		 */
		public PlayList (String name) {
			playing = null;
			this.name = name;
			songList = new ArrayList<Song>();
		}
	
		/**
		 * @param name; the name of the playList
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * @param song; the song of the playList
		 */
		public void addSong(Song song) {
			songList.add(song);
		}
		
		/**
		 * @param index; the removed song of the playList
		 * @return null; 
		 */
		public Song removeSong(int index) {
			if(index>=0 && index<songList.size()) {
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
			for(int i=0; i<songList.size(); i++) {
					totalPlayTime += songList.get(i).getPlayTime();
			}
			return totalPlayTime;
		}
		
		/**
		 * @param index; the get song of the playList.
		 * @return null;
		 */
		public Song getSong(int index) {
			if(index>=0 && index<songList.size()) {
				return songList.get(index);
			} else {
				return null;
			}
		}
		
		/**
		 * @param index; the play song of the playList
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
			if (songList.size() ==0) {
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
			for (int i=0; i<songList.size(); i++) {
				avgTime+=songList.get(i).getPlayTime();
				
			}
			return avgTime/songList.size();
			
		}
		
		/**
		 * @return shortsong
		 */
		private Song shortest() {
			int shortest =11111110 ;
			Song shortsong = null;
			for(int i=0; i<songList.size(); i++) {
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
			for(int i=0; i<songList.size(); i++) {
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
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
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
				for (int i=0; i<songList.size(); i++) {
					s1 += ("("+i+") ") +songList.get(i) + "\n";
				}
				s1 += "------------------\n";
			return s1;
			}
		}
}
