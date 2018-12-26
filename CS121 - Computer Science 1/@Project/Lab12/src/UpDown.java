import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

public class UpDown {

	private int width = 100;
	private int height = 90;
	private int initial_number = 50;
	private JFrame frame;
	private JPanel panel;
	private JLabel numberLabel;
	private JButton UpButton;
	private JButton DownButton;
	private int number;
	
	public UpDown() {
		frame = new JFrame("Random Numbers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		number = initial_number;
		
		numberLabel = new JLabel(String.valueOf(number));
		
		UpButton = new JButton("Up");
		UpButton.addActionListener(new UpListener());
		
		DownButton = new JButton("Down");
		DownButton.addActionListener(new DownListener());
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));
		panel.add(numberLabel);
		panel.add(UpButton);
		panel.add(DownButton);
		
		frame.getContentPane().add(panel);
	}
	
	public void display() {
		frame.pack();
		frame.setVisible(true);
	}
	
	private class UpListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			numberLabel.setText(Integer.toString(++number));
		}
	}
	
	private class DownListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			numberLabel.setText(Integer.toString(--number));
		}
	}	
}
