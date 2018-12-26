import javax.swing.*;

public class LightSwitch {

    private ToggleButton button;
    private BinaryCounter counter;

    public LightSwitch() {
        button=new ToggleButton("off","on");
        counter=new BinaryCounter(0);
        button.addActionListener(counter);
	JPanel contentPane=new JPanel();
	contentPane.add(button);
	contentPane.add(counter);
        JFrame frame=new JFrame("LightSwitch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setContentPane(contentPane);
        frame.pack();
        frame.setSize(500,200);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    new LightSwitch();
		}
	    });
    }

}
