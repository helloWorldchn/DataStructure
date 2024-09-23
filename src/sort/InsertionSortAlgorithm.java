package sort;

import java.util.Arrays;
/*
 * 插入排序（Insertion Sort）
 * 1.从第一个元素开始，该元素可以认为已经被排序
 * 2.取出下一个元素，在已经排序的元素序列中从后向前扫描（从第2个元素开始和前面已经排序好的比较）
 * 3.如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * 5.将新元素插入到该位置后
 * 6.重复步骤2~5
 */

public class InsertionSortAlgorithm {	
	public void insertionSort(int arrays[]) {
		// 从第二个元素开始，和前面的有序表进行比较
		int j;
		for (int i = 1; i < arrays.length; i++) {
			int insertVal = arrays[i]; // 记录要插入的值，并保存在temp中，防止数据移动时该元素丢失
			for (j = i; j > 0 && arrays[j-1] > insertVal; j--) {
				arrays[j] = arrays[j-1];
			}
			arrays[j] = insertVal;
//			System.out.println("第"+i+"趟之后："+Arrays.toString(arrays));
		}
	}
	
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,-62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("原始数据集："+Arrays.toString(arrays));
		new InsertionSortAlgorithm().insertionSort(arrays);
		System.out.println("插入排序后："+Arrays.toString(arrays));	
	}
}
