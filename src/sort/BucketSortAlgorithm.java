package sort;

import java.util.Arrays;
/*
  * 桶排序（BucketSort）
 * 1. 寻找数组中最大元素和最小元素并计算桶的大小*bucketSize*和个数*bucketCount；
 * 2. 将元素均匀地分散到多个桶中；
 * 3. 对每个桶中的元素进行排序（通常使用的是插入排序等简单排序算法）；
 * 4. 合并各个桶中的元素，得到有序序列。
 */
public class BucketSortAlgorithm {
	
	public void bucketSort(int[] arrays) {
        if (arrays.length == 0) {
            return;
        }
        // 寻找最大和最小元素
        int minValue = arrays[0];
        int maxValue = arrays[0];
        for (int value : arrays) {
            if (value < minValue) {
                minValue = value;
            } else if (value > maxValue) {
                maxValue = value;
            }
        }
        int bucketSize = (maxValue - minValue) / arrays.length + 1; // 计数桶存储数的范围大小
        int bucketCount = (int) Math.floor((maxValue - minValue) / bucketSize) + 1; // 计数桶的个数，
        int[][] buckets = new int[bucketCount][0];

        // 利用映射函数将数据分配到各个桶中
        for (int i = 0; i < arrays.length; i++) {
            int index = (int) Math.floor((arrays[i] - minValue) / bucketSize);
            buckets[index] = arraysAppend(buckets[index], arrays[i]);
        }

        int arraysIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length <= 0) {
                continue;
            }
            // 对每个桶进行排序，这里使用了库函数的快速排序
            Arrays.sort(bucket);
            for (int value : bucket) {
                arrays[arraysIndex++] = value;
            }
        }
	}
	
	// 自动扩容
    private int[] arraysAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }
    
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,-62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("原始数据集："+Arrays.toString(arrays));
		new BucketSortAlgorithm().bucketSort(arrays);
		System.out.println("   桶排序后："+Arrays.toString(arrays));
	}
}
