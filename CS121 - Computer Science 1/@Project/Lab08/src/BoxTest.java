import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author akim
 *
 */
public class BoxTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		double width;
		double height;
		double depth;
		
		
		Box smallBox = new Box (4, 5, 2);
		System.out.println(smallBox.toString());
		System.out.println();
		
		System.out.println("=======Change smallbox using it's setters=====");
		System.out.println();
		
		Box smallBox2 = new Box (2, 3, 1);
		System.out.println(smallBox2.toString());
		System.out.println();
		
		System.out.println("=======Create 5 boxes=======");
		System.out.println();
		
		ArrayList <Box> BoxTest = new ArrayList<Box>();
		Random rand = new Random();
		
		for (int i=0; i<5; i++) {
			Box A = new Box(rand.nextDouble()*10, rand.nextDouble()*10, rand.nextDouble()*10);
			
			width = A.getWidth();
			height = A.getHeight();
			depth = A.getDepth();
			
			boolean B = rand.nextBoolean();
			A.setFull(B);
			BoxTest.add(A);
			
//			DecimalFormat dFormat = new DecimalFormat("0.00");
//			String width2 = dFormat.format(width);
//			String height2 = dFormat.format(height);
//			String depth2 = dFormat.format(depth);
//			System.out.println("Box " + i + ": " + width2 + " * " + height2 + " * " + depth2 + " box");
			
			System.out.println("Box " + i + ": " + A);
		}
			
		
		System.out.println();
		System.out.println("=======Find the largest box=======");
		System.out.println();
		
		System.out.println("Largest Box");
		Box largestBox = new Box (0, 0, 0);
		for (Box box2 : BoxTest) {
			if (box2.getVolume()>largestBox.getVolume()) {
				largestBox = box2;
			}
		
		}
		
		System.out.println(largestBox );
		
	}
}

