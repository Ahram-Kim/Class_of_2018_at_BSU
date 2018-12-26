

public class TrueFalse {

	public static void main(String[] args) {
		
		int size = Integer.parseInt(args[0]);
		boolean[] flags = new boolean[size];
		
		for (int index=0; index<flags.length; index++) {
			if(index%2 != 0) {
				flags[index] = false;
			} else {
				flags[index] = true;
			}
		}
		
		System.out.println("length: " +size);
		System.out.println("Flags:\n" + printArray(flags));
		
		boolean[] copy = copyArray(flags);
		System.out.println("Copy:\n" + printArray(copy));
	
		
		flags[0] = false;
		flags[2] = false;
		
		System.out.println("Copy after flags was changed:\n" + printArray(copy));
		
	}	
		
	public static boolean[] copyArray(boolean[] original) {
		
		for(int i=0; i<original.length; i++) {
			boolean[] temp = new boolean[original.length];
			original[i]=temp[i];
			return temp;
		}
		return null;
	}
	
	public static String printArray(boolean[] printArray) {
		int size = printArray.length;
		String returnString = "";
		returnString +="[";
		for(int j=0; j<size-1; j++) {
			returnString = printArray[j] + " , ";
		}
		returnString += printArray[size-1] +"]\n";
		return returnString;
	}
}


//import java.util.Arrays;
//import java.util.Scanner;
//
//public class Prelab_TrueFalse {
//
//	static boolean[] flags = null;
//	
//	public static void main(String[] args) {
//		
//		Scanner scanner = new Scanner(System.in);
//		
//		System.out.println("length: ");
//		
//		while(true) {
//			int length = scanner.nextInt();
//			
//			if(length>0) {
//				flags = new boolean[length];
//				
//				for(int i=0; i<flags.length; i++) {
//					if(i==0) {
//						flags[0] = true;
//					} else if(i%2==0) {
//						flags[i] = true;
//					} else {
//						flags[i] = false;
//					}
//				}
//				System.out.println(Arrays.toString(flags));
//			}
//		}
//	}
//}

