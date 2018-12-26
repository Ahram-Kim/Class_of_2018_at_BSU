import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ToggleButton extends JButton implements ActionListener {

    private String label1, label2;
   
    public ToggleButton(String label1, String label2) {
        super(label1);
        this.label1=label1; 
        this.label2=label2;
        addActionListener(this);
    } 

    public void actionPerformed(ActionEvent e) {
        String s=label1;
        label1=label2;
        label2=s;
        setText(label1);
    }

}
