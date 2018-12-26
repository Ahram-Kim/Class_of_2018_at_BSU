import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Draws gradients across the width of the panel
 * @author ?
 */
@SuppressWarnings("serial")
public class GradientLooperFourColors extends JPanel {
	/* This method draws on the Graphics context.
	 * This is where your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics canvas) 
	{
		//ready to paint
		super.paintComponent(canvas);
		
		//account for changes to window size
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height
		
		final int GRADIENT_DIVISIONS = 256;
		final int NUM_GRADIENT_BARS = 1;

		//TODO: Your code goes here

		int red = 0;
		int green = 0;
		int blue = 0;
		int x=0;
		int y=0;
		int rectwidth = width/GRADIENT_DIVISIONS+1;
		int rectheight = height/4;
		
		for(int j=0; j<4; j++) {
			red = 0;
			green = 0;
			blue = 0;
			
			for(int i=0; i<256; i++) {
			canvas.setColor(new Color(red, green, blue));
			canvas.fillRect(i*rectwidth, j*rectheight, rectwidth, height);
	
				switch (j) {
					case 0:
						red++;
						green++;
						blue++;
						break;
					case 1:
						red++;
						break;
					case 2:
						green++;
						break;
					case 3:
						blue++;
						break;
				}
		
			}
		
		}
		
		
		
		
	}

	/**
	 * DO NOT MODIFY
	 * Constructor for the display panel initializes
	 * necessary variables. Only called once, when the
	 * program first begins.
	 */
	public GradientLooperFourColors() 
	{
		setBackground(Color.black);
		int initWidth = 768;
		int initHeight = 512;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);
	}

	/**
	 * DO NOT MODIFY
	 * Starting point for the program
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("GradientLooperFourColors");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GradientLooperFourColors());
		frame.pack();
		frame.setVisible(true);
	}
}