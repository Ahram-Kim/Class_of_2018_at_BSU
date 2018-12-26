import java.text.DecimalFormat;

public class GrandfatherClock extends Clock {

	public  GrandfatherClock() {
		
		super(ClockType.mechanical, 0.000347222);
	}

	@Override
	public void display() {
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		String s = "";
		s += "mechanical grandfather clock: time ";
		s += "[" + time.formattedTime() + "], ";
		s += "total drift = ";
		s += df.format(time.getTotalDrift());
		s += " seconds";
		System.out.println(s);
		
	}	
	
}
