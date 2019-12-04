import java.util.Arrays;
import java.util.Comparator;

public class sort {

    public static void main(String[] args) {
	// write your code here
        int[] a = {6,1,3,7,2,9,3,4,5,8};
        Arrays.sort(a);
        for(int i : a)
            System.out.print(i + " ");
    }
}
