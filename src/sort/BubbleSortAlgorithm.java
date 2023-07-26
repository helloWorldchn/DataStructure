package sort;

import java.util.Arrays;

/*
 * 冒泡排序（Bubble Sort）
 * 1.比较相邻的元素，如果第一个比第二个大，就交换他们两个
 * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * 3.针对所有的元素重复以上的步骤，除了最后一个。
 * 4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 */
public class BubbleSortAlgorithm {

	public void bubbleSort(int[] arrays) {
		for (int i = 0; i < arrays.length; i++) {
			for (int j = 0; j+1 < arrays.length-i; j++) {
				if (arrays[j] > arrays[j+1]) {
					int temp = arrays[j+1];
					arrays[j+1] = arrays[j];
					arrays[j] = temp;
				}
			}
//			System.out.println("第"+(i+1)+"趟之后："+Arrays.toString(arrays));
		}
	}
	
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,-62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("原始数据集："+Arrays.toString(arrays));
		new BubbleSortAlgorithm().bubbleSort(arrays);
		System.out.println("冒泡排序后："+Arrays.toString(arrays));
	}
}
