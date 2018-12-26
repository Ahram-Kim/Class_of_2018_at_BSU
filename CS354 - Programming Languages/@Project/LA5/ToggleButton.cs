
 using System;
 using System.Drawing;
 using System.Windows.Forms;

 // ToggleButton.cs

 public class ToggleButton : Button
 {
	private string label1, label2;
	
	public ToggleButton(string label1, string label2) : base()
 	{
		this.label1 = label1;
		this.label2 = label2;
		this.Text = label1;
		this.Click += new EventHandler(OnClick);
	}

	public void OnClick(Object sender, EventArgs e)
	{
		String s = label1;
		label1 = label2;
		label2 = s;
		this.Text = label1;
	}
 }
