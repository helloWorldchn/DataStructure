package sort;

import java.util.Arrays;

/*
 * 选择排序 Selection Sort
 * 1.首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
 * 2.再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 3.重复第二步，直到所有元素均排序完毕。
 */
public class SelectionSortAlgorithm {
	// 选择排序法
	public static void selectionSort(int arrays[]) {
        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arrays.length-1; i++) {
            int min = i;

            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arrays.length; j++) {
                if (arrays[j] < arrays[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }

            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int temp = arrays[i];
                arrays[i] = arrays[min];
                arrays[min] = temp;
            }

        }
	}
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,-62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("原始数据集："+Arrays.toString(arrays));
		selectionSort(arrays);
		System.out.println("选择排序后："+Arrays.toString(arrays));
	}
}
