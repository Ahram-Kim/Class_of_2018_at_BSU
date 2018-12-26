import java.text.DecimalFormat;

public class WristWatch extends Clock {
	
	public WristWatch() {
		
		super(ClockType.digital, 0.000034722);
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		String s = "";
		s += "digital wrist watch: time ";
		s += "[" + time.formattedTime() + "], ";
		s += "total drift = ";
		s += df.format(time.getTotalDrift());
		s += " seconds";
		System.out.println(s);
		
	}	

}
