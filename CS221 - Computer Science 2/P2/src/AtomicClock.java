import java.text.DecimalFormat;

public class AtomicClock extends Clock{

	public AtomicClock() {
		
		super(ClockType.quantum, 0.0);
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		String s = "";
		s += "quantum atomic clock: time ";
		s += "[" + time.formattedTime() + "], ";
		s += "total drift = ";
		s += df.format(time.getTotalDrift());
		s += " seconds";
		System.out.println(s);
		
	}
	
}
