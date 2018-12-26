import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * CS 121 Project 1: Traffic Animation
 *
 * Animates two cars traveling in different directions in separate lanes 
 * with one boy wearing a hat and looking for two cars.
 *
 * @author BSU CS 121 Luke Hindman
 * @author Ahram Kim
 */
@SuppressWarnings("serial")
public class TrafficAnimation extends JPanel
{
	// This is where you declare constants and variables that need to keep their
	// values between calls	to paintComponent(). Any other variables should be
	// declared locally, in the method where they are used.

	/**
	 * A constant to regulate the frequency of Timer events.
	 * Note: 100ms is 10 frames per second - you should not need
	 * a faster refresh rate than this
	 */
	private final int DELAY = 100; //milliseconds

	/**
	 * The anchor coordinate for drawing / animating. All of your vehicle's
	 * coordinates should be relative to this offset value.
	 */
	private int xOffset = -50;
	private int xOffset2 = 550;

	/**
	 * The number of pixels added to xOffset each time paintComponent() is called.
	 */
	private int stepSize = 10;

	private final Color BACKGROUND_COLOR = new Color(224, 255, 255);

	/* This method draws on the panel's Graphics context.
	 * This is where the majority of your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		// Get the current width and height of the window.
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height

		// Fill the graphics page with the background color.
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);

		// Calculate the new xOffset position of the moving object.
		xOffset  = (xOffset + stepSize) % width;
		xOffset2 = (xOffset2 - stepSize) % width;
		
		
		// TODO: Use width, height, and xOffset to draw your scalable objects
		// at their new positions on the screen
		
		g.setColor(new Color(255, 215, 0));
		g.setFont(new Font("Arial", Font.BOLD, 45));
		g.drawString("Happy Day!", width/30, height/10);
		
		g.setColor(new Color(192, 192, 192));
		g.fillRect(0, height/3, width, height/3);
		g.fillRect(width/2, 0, width/4, height);
		
		g.setColor(Color.white);
		g.fillRect(0, height/2-10, width/10, height/30);
		g.fillRect(width/8+8, height/2-10, width/5, height/30);
		g.fillRect(width*3/8,  height/2-10,  width/5,  height/30);
		g.fillRect(width*5/8,  height/2-10, width/5, height/30);
		g.fillRect(width*7/8,  height/2-10,  width/8,  height/30);
		
		g.setColor(Color.yellow);
		g.fillOval(width*5/6, height/6-20, width/25, width/25);
		g.setColor(new Color(255, 69, 0));
		g.fillOval(width*5/6, height/6-45, width/25, width/18);
		g.fillOval(width*5/6-25, height/6-20, width/18, width/25);
		g.fillOval(width*5/6, height/6-4, width/25, width/18);
		g.fillOval(width*5/6+17, height/6-20, width/18, width/25);
		
		g.setColor(Color.black);
		g.drawArc(width*5/6, height/6, width/18, width/5, 0, 75);
		
		int x = 200;
		int y = 300;
		
		g.setColor(Color.black);
		g.fillOval(x, y, x/5,  x/5); 
		
		g.setColor(Color.black);
		g.drawLine(x+19, y+35, x+19, y+75);
		g.drawLine(x+19, y+39, x-10, y+50);
		g.drawLine(x+19, y+39, x+50, y+50);
		g.drawLine(x+19, y+75, x-10, y+100);
		g.drawLine(x+19, y+75, x+50, y+100);
		
		g.setColor(new Color(240, 128, 128));
		g.fillArc(width/3-30, height/2+35, width/6, height/5, 245, 50);
		
		
		// This draws a green square. Replace it with your own object.
		int squareSide = height/7;
		int squareY = height/2 - squareSide/2;
		g.setColor(new Color(0, 0, 205));
		g.fillRoundRect(xOffset, squareY, squareSide, squareSide, width/20, height/20);
		g.fillRoundRect(xOffset-40, squareY+29, squareSide*5/2, squareSide/2, width/25, height/25);
				
		g.setColor(Color.yellow);
		g.fillOval(xOffset+50,  squareY+35, squareSide-20, squareSide-20);
		g.fillOval(xOffset-30, squareY+35, squareSide-20, squareSide-20);
		
		g.setColor(Color.black);
		g.fillOval(xOffset+60, squareY+42, squareSide-40, squareSide-33);
		g.fillOval(xOffset-20, squareY+42, squareSide-40, squareSide-33);
		
		int squareSide2 = height/6;
		int squareY2 = height/5;
		g.setColor(new Color(255, 0, 255));
		g.fillRect(xOffset2, squareY2, squareSide2+60, squareSide2);
		g.fillRect(xOffset2-50, squareY2+20, squareSide2, squareSide2-20);
		
		g.setColor(Color.black);
		g.fillOval(xOffset2+50, squareY2+35, squareSide2-20, squareSide2-20);
		g.fillOval(xOffset2-30, squareY2+35, squareSide2-20, squareSide2-20);
		
		g.setColor(new Color(152, 251, 152));
		g.fillOval(xOffset2+60, squareY2+42, squareSide2-40, squareSide2-33);
		g.fillOval(xOffset2-20, squareY2+42, squareSide2-40, squareSide2-33);
	
		
		Toolkit.getDefaultToolkit().sync();
	}


	//==============================================================
	// You don't need to modify anything beyond this point.
	//==========================
	//====================================


	/**
	 * Starting point for this program. Your code will not go in the
	 * main method for this program. It will go in the paintComponent
	 * method above.
	 *
	 * DO NOT MODIFY this method!
	 *
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		// DO NOT MODIFY THIS CODE.
		JFrame frame = new JFrame ("Traffic Animation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TrafficAnimation());
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Constructor for the display panel initializes necessary variables.
	 * Only called once, when the program first begins. This method also
	 * sets up a Timer that will call paint() with frequency specified by
	 * the DELAY constant.
	 */
	public TrafficAnimation()
	{
		// Do not initialize larger than 800x600. I won't be able to
		// grade your project if you do.
		int initWidth = 600;
		int initHeight = 400;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically.
	 * DO NOT MODIFY this method!
	 */
	private void startAnimation()
	{
		ActionListener timerListener = new TimerListener();
		Timer timer = new Timer(DELAY, timerListener);
		timer.start();
	}

	/**
	 * Repaints the graphics panel every time the timer fires.
	 * DO NOT MODIFY this class!
	 */
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
}