import java.text.DecimalFormat;

public class CuckooClock extends Clock{

	public CuckooClock() {
		
		super(ClockType.mechanical, 0.000694444);
	}

	@Override
	public void display() {
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		String s = "";
		s += "mechanical cuckoo clock: time ";
		s += "[" + time.formattedTime() + "], ";
		s += "total drift = ";
		s += df.format(time.getTotalDrift());
		s += " seconds";
		System.out.println(s);
		
	}	
	
}
