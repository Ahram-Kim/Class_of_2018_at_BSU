import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BinaryCounter extends JLabel implements ActionListener {

    private int count;
   
    public BinaryCounter(int count) {
        super(Integer.toBinaryString(count));
	this.count=count;
    } 

    public void actionPerformed(ActionEvent e) {
        setText(Integer.toBinaryString(++count));
    }

}
