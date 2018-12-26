// Java sum program

public class Sum {

    public static int sum(int[] seq) {
	int s=0;
	for (int v: seq)
	    s+=v;
	return s;
    }

    public static void main(String[] args) {
	int[] ints={5,6,1,8,3,7};
	System.out.println(sum(ints));
    }

}
