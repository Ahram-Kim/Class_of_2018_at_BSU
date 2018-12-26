
public class ClockSimulation {

	public static void main(String[] args) {
		
		Bag<Clock> bag = new Bag<Clock>();
				
		bag.add(new Sundial());
		bag.add(new CuckooClock());
		bag.add(new GrandfatherClock());
		bag.add(new AtomicClock());
		bag.add(new WristWatch());
		
		
		System.out.println("before the simulation begins: ");
		
		for(int i=0; i<bag.getSize(); i++) {
			bag.get(i).display();
			bag.get(i).reset();
		}
		
		System.out.println();
		System.out.println("after one day: ");
		
		
		for(int i = 0; i < bag.getSize(); i++) {
			for(long j = 0; j < 86400; j++) {
				bag.get(i).tick();
			}
			bag.get(i).display();
			bag.get(i).reset();
		}
		
		System.out.println();
		System.out.println("after one week: ");
		
		for(int i = 0; i < bag.getSize(); i++) {
			for(long j = 0; j < 604800; j++) {
				bag.get(i).tick();
			}
			bag.get(i).display();
			bag.get(i).reset();
		}
		
		
		System.out.println();
		System.out.println("after one month: ");
		
		for(int i = 0; i < bag.getSize(); i++) {
			for(long j = 0; j < 2592000; j++) {
				bag.get(i).tick();
			}
			bag.get(i).display();
			bag.get(i).reset();
		}
		
		System.out.println();
		System.out.println("after one year: " );
		
		for(int i = 0; i < bag.getSize(); i++) {
			for(long j = 0; j < 31536000; j++) {
				bag.get(i).tick();
			}
			bag.get(i).display();
			bag.get(i).reset();
		}
		

		
	}

}
