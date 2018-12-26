import java.text.DecimalFormat;

public class Sundial extends Clock{

	public Sundial() {
		
		super(ClockType.natural, 0.00);
	}

	@Override
	public void display() {
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		String s = "";
		s += "natural sun dial: time ";
		s += "[" + time.formattedTime() + "], ";
		s += "total drift = ";
		s += df.format(time.getTotalDrift());
		s += " seconds";
		System.out.println(s);
		
	}	
	
}
