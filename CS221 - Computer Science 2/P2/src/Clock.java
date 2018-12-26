

public abstract class Clock implements TimePiece{
	
	protected Time time;
	private ClockType type;
	
	public enum ClockType {
		
		natural, mechanical, digital, quantum
		
	}
	
	public Clock(ClockType clockType, double clockDrift) {
		
		time = new Time(0, 0, 0, clockDrift);
		type = clockType;
				
	}
	
	public void setType(ClockType clockType) {
		
		type = clockType;
		
	}
	
	public ClockType getType() {
		
		return type;
		
	}
	
	@Override
	public void reset() {
		
		time.resetToStartTime();
		
	}

	@Override
	public void tick() {
		
		time.incrementTime();
		
	}

	@Override
	public abstract void display();
	

}
