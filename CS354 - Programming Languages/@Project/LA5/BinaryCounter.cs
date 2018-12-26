
 using System;
 using System.Drawing;
 using System.Windows.Forms;
 
 // C#  program
 
 public class BinaryCounter : Label
 {
	private int count;

	public BinaryCounter(int count) : base()
	{
		this.Text = Convert.ToString(count,2);
		this.count = count;
	}

	public void OnButtonClick(Object sender, EventArgs e)
	{
		this.Text = Convert.ToString(++count);
	}
 }
