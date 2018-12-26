
import java.text.DecimalFormat;
import java.util.Scanner;

public class PlayList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		
		// 1. User input
		System.out.print("Enter title: ");
		String title = in.nextLine();
		System.out.print("Enter artist: " );
		String artist = in.nextLine();
		System.out.print("Enter play time (mm:ss): " );
		String playTime = in.nextLine();
		System.out.print("Enter file name: ");
		String filePath = in.nextLine();
		System.out.println();
		
		// 2. Creating Song object from input
		int index = playTime.indexOf(':');
		String mm = playTime.substring(0, index);
		String ss = playTime.substring(index + 1, index + 3);
		int minutes = Integer.parseInt(mm);
		int seconds = Integer.parseInt(ss);
		int songLength = (minutes * 60) + seconds;
		
		Song song = new Song(title, artist, songLength, filePath);
		
		System.out.print("Enter title: ");
		String title2 = in.nextLine();
		System.out.print("Enter artist: " );
		String artist2 = in.nextLine();
		System.out.print("Enter play time (mm:ss): " );
		String playTime2 = in.nextLine();
		System.out.print("Enter file name: ");
		String filePath2 = in.nextLine();
		System.out.println();
		
		int index2 = playTime2.indexOf(':');
		String mm2 = playTime2.substring(0, index2);
		String ss2 = playTime2.substring(index2 + 1, index2 + 3);
		int minutes2 = Integer.parseInt(mm2);
		int seconds2 = Integer.parseInt(ss2);
		int songLength2 = (minutes2 * 60) + seconds2;
		
		Song song2 = new Song(title2, artist2, songLength2, filePath2);
		
		System.out.print("Enter title: ");
		String title3 = in.nextLine();
		System.out.print("Enter artist: " );
		String artist3 = in.nextLine();
		System.out.print("Enter play time (mm:ss): " );
		String playTime3 = in.nextLine();
		System.out.print("Enter file name: ");
		String filePath3 = in.nextLine();
		System.out.println();
		
		int index3 = playTime3.indexOf(':');
		String mm3 = playTime3.substring(0, index3);
		String ss3 = playTime3.substring(index3 + 1, index3 + 3);
		int minutes3 = Integer.parseInt(mm3);
		int seconds3 = Integer.parseInt(ss3);
		int songLength3 = (minutes3 * 60) + seconds3;
		
		Song song3 = new Song(title3, artist3, songLength3, filePath3);
		//song3.getTitle() = title3;

		// 3. Calculate the average play time
		double average = (double)(song.getPlayTime() + song2.getPlayTime() + song3.getPlayTime())/3.0;
		DecimalFormat dFormat = new DecimalFormat("0.00");
		String aveTime = dFormat.format(average);
		System.out.println("Average play time: " + aveTime);
		System.out.println();
		
		
		// 4. Find the song with play time closest to 4 minutes.
		int a = Math.abs(240-song.getPlayTime());
		int b = Math.abs(240-song2.getPlayTime());
		int c = Math.abs(240-song3.getPlayTime());
		
		Song closestToFour = song;
		
		if(b < a && b < c)  {
			closestToFour = song2;
		}
		if(c < b && c < a)  {
			closestToFour = song3;
		}
		System.out.println("Song with play time closest to 240 secs is : " + closestToFour.getTitle());
		System.out.println();
		
		// 5. Build a sorted play list
		System.out.println("==============================================================================");
		System.out.println("Title                Artist               File Name                  Play Time");
		System.out.println("==============================================================================");
		System.out.println(song.toString());
		System.out.println(song2.toString());
		System.out.println(song3.toString());
		System.out.println("==============================================================================");
		
		//System.out.println("");
		
		in.close();
	}

}
