
 // C# LightSwitch Program

 using System;
 using System.Drawing;
 using System.Windows.Forms;

 public class LightSwitch : Form
 {
	public LightSwitch()
	{
		Text = "Light Switch";
		Size = new Size(500, 200);

		ToggleButton button = new ToggleButton("off", "on");
		button.Text = "Off";
		button.Location = new Point(100, 80);
		
		BinaryCounter counter = new BinaryCounter(0);
		button.Click += new EventHandler(counter.OnButtonClick);
		

		CenterToScreen();
	}

	static public void Main()
	{
		Application.Run(new LightSwitch());
	}
 }
