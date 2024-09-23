package sort;

import java.util.Arrays;

public class ArraysSort {
	public static void main(String[] args) {
        int arrays[]=new int[]{6,2,9,4,7,6,1,3,5};
		System.out.print("≈≈–Ú«∞£∫");
		System.out.println(Arrays.toString(arrays));
		
		Arrays.sort(arrays,1,7);
		System.out.println(Arrays.toString(arrays));
		
		Arrays.sort(arrays);
		System.out.print("≈≈–Ú∫Û£∫");
		System.out.println(Arrays.toString(arrays));
	}
}
