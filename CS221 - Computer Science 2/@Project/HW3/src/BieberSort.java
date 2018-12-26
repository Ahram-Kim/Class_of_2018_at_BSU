import java.util.Random;

/**
 * Sort a-la-Bieber 
 * 
 * @author Shane K. Panter
 */
public class BieberSort {
	private Biebz[] theBiebz;
		
	/**
	 * Inner class for the BieberSort
	 * @author Shane K. Panter
	 */
	public class Biebz implements Comparable<Biebz>{
		private int b;
		public Biebz(int b){
			this.b = b;
		}
		@Override
		public int compareTo(Biebz o) {
			return this.b - o.b;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return String.valueOf(this.b);
		}
	}

	/**
	 * Construct a new BieberSort class with n fans
	 * @param n
	 */
	public BieberSort(int n) {
		this.theBiebz = new Biebz[n];
		Random rdm = new Random(System.currentTimeMillis());
		for(int i = 0; i< n;i++){
			theBiebz[i] = new Biebz(rdm.nextInt(n));
		}
	}
	
	/**
	 * Prints all the Biebers that we have ... 
	 */
	public void printBiebers(){
		for(Biebz bz:theBiebz){
			System.out.println(bz);
		}
	}

	/**
	 * Sorts a collection of Bieber objects
	 */
	public void sort(){
		for(int i =0;i<theBiebz.length;i++){
			for(int j = theBiebz.length-1;j>i;j--){
				if(theBiebz[i].compareTo(theBiebz[j]) > 0 ){
					swap(i,j);
				}
			}
		}
	}
	
	private void swap(int i, int j){
		Biebz b =theBiebz[i];
		theBiebz[i] = theBiebz[j];
		theBiebz[j]=b;
	}
}
