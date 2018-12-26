import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Animated program with a ball bouncing off the program boundaries
 * @author mvail
 * @author Ahram Kim, Seonghoon Son
 */
public class BouncyBall extends JPanel
{
	private final int INIT_WIDTH = 600;
	private final int INIT_HEIGHT = 400;
	private final int DELAY = 50; // milliseconds between Timer events
	private Random rand; //random number generator
	private int x, y; //anchor point coordinates
	private int xDelta, yDelta; //change in x and y from one step to the next
	private final int DELTA_RANGE = 20; //range for xDelta and yDelta
	private int radius = 20; //circle radius
	int rdelta=2;
	int minr=50;
	int maxr=5;
	int startr;
	int n1;
	int n2;
	int n3;
	/**
	 * Draws a filled oval with random color and dimensions.
	 *
	 * @param g Graphics context
	 * @return none
	 */
	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		
		//clear canvas
		g.setColor(getBackground());
		g.fillRect(0, 0, width, height);

		
		//CALCULATE NEW X
		radius += rdelta;	
		//TODO: needs more to stay in-bounds
		x += xDelta;
		if(x+radius>=width) {
			
			xDelta = -xDelta;
			x = width - radius;
		}
		if(x-radius<=0) {
			
			xDelta= -xDelta;
			x = 0 + radius;
		}
		//CALCULATE NEW Y
		y += yDelta;
		//TODO: needs more to stay in-bounds
		if(y+radius>=height) {
			yDelta = -yDelta;
			y = height - radius;
		}
		if(y-radius<=0) {
			
			yDelta= -yDelta;
			y = 0 + radius;
		}


		
		//NOW PAINT THE OVAL
		
		Color newColor = new Color(n1, n2, n3);
		g.setColor(newColor);
		g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
		//3D BOX
		
		
		if(radius>=maxr) {
			
			rdelta = -rdelta;
		}
		if(radius<=minr) {
			
			rdelta= -rdelta;
		}
		//Makes the animation smoother
		Toolkit.getDefaultToolkit().sync();
	}

	/**
	 * Constructor for the display panel initializes
	 * necessary variables. Only called once, when the
	 * program first begins.
	 * This method also sets up a Timer that will call
	 * paint() with frequency specified by the DELAY
	 * constant.
	 */
	public BouncyBall()
	{
		setPreferredSize(new Dimension(INIT_WIDTH, INIT_HEIGHT));
		this.setDoubleBuffered(true);
		setBackground(Color.black);

		rand = new Random(); //instance variable for reuse in paint()

		//initial ball location within panel bounds
		x = rand.nextInt(INIT_WIDTH);
		y = rand.nextInt(INIT_HEIGHT);
		//TODO: replace centered starting point with a random
		// position anywhere in-bounds - the ball should never
		// extend out of bounds, so you'll need to take RADIUS
		// into account
		
		//deltas for x and y
		xDelta = rand.nextInt(DELTA_RANGE)-DELTA_RANGE/2;
		yDelta = rand.nextInt(DELTA_RANGE)-DELTA_RANGE/2;
		//TODO: replace with random deltas from -DELTA_RANGE/2
		// to +DELTA_RANGE/2
		
		n1=rand.nextInt(255);
		n2=rand.nextInt(255);
		n3=rand.nextInt(255);
		//Random radius
		startr= rand.nextInt(50);
	
		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically
	 * DO NOT MODIFY
	 */
	private void startAnimation() {
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				repaint();
			}
		};
		new Timer(DELAY, taskPerformer).start();
	}

	/**
	 * Starting point for the BouncyBall program
	 * DO NOT MODIFY
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Bouncy Ball");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BouncyBall());
		frame.pack();
		frame.setVisible(true);
	}

}