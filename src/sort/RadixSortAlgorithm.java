package sort;

import java.util.Arrays;

/*
 * 基数排序（Radix Sort）
 * 1.将整数按位数切割成不同的数字
 * 2.然后按每个位数分别比较。
 */

public class RadixSortAlgorithm {
	//基数排序法
	public static void radixSort(int arrays[]) {
        int maxValue = Arrays.stream(arrays).max().getAsInt(); // 找出数组arrays中的最大值
        int exp = 1; // 指数
        int[] buf = new int[arrays.length]; // 存储"被排序数据"的临时数组
        // 从个位开始，对数组a按"指数"进行排序
        while (maxValue >= exp) {
            int[] buckets = new int[10]; // 将数字出现的次数存储在buckets[]中
            for (int i = 0; i < arrays.length; i++) {
                int digit = (arrays[i] / exp) % 10;
                buckets[digit]++;
            }
            for (int i = 1; i < 10; i++) {
            	buckets[i] += buckets[i - 1];
            }
            for (int i = arrays.length - 1; i >= 0; i--) {
                int digit = (arrays[i] / exp) % 10;
                buf[buckets[digit] - 1] = arrays[i];
                buckets[digit]--;
            }
            // 将排序好的数据赋值给a[]
            for (int i = 0; i < arrays.length; i++) {
            	arrays[i] = buf[i];
            }
            exp *= 10;
        }
	}
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("原始数据集："+Arrays.toString(arrays));
		radixSort(arrays);
		System.out.println("基数排序后："+Arrays.toString(arrays));
	}
}
