
public interface TimePiece {
	/*
	 * 
	 */
	
	public abstract void reset();
	/*
	 * resets the TimePiece to its starting time
	 * (00:00:00 - midnight by default
	 * @return
	 */
	

	public abstract void tick();
	/*
	 * simulates one second of time passing
	 * @return 
	 */
	

	public abstract void display();
	/*
	 * displays the current time
	 * @return
	 */
	
}
