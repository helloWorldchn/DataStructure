package sort;

import java.io.ObjectInputStream.GetField;
import java.util.Arrays;

/*
 * 计数排序（CountingSort）
 * 1. 找出待排序的数组中最大和最小的元素，根据最大和最小元素的差值范围，申请额外计数数组。
 * 2. 统计数组中每个元素的出现次数，并将统计结果存储在额外的计数数组中。
 * 3. 根据计数数组中的值（即每个元素出现的次数），将对应的元素按顺序放入新的数组中，使得新数组中的元素有序。
 */
public class CountingSortAlgorithm {

	public void countingSort(int[] arrays, int maxValue) {
        int bucketLen = maxValue + 1;
        int[] bucket = new int[bucketLen];
        for (int value : arrays) {
            bucket[value]++;
        }
        int sortedIndex = 0;
        for (int j = 0; j < bucketLen; j++) {
            while (bucket[j] > 0) {
            	arrays[sortedIndex++] = j;
                bucket[j]--;
            }
        }
    }
	
    private int getMaxValue(int[] arrays) {
        int maxValue = arrays[0];
        for (int value : arrays) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }
	
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("原始数据集："+Arrays.toString(arrays));
		int maxValue = new CountingSortAlgorithm().getMaxValue(arrays);
		new CountingSortAlgorithm().countingSort(arrays, maxValue);
		System.out.println("计数排序后："+Arrays.toString(arrays));
	}
}
