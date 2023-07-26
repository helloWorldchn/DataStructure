package sort;

import java.util.Arrays;

/*
  * 希尔排序（Shell Sort）
  * 希尔排序也是一种插入排序
 * 1.确定一个元素间隔数step。
 * 2.将参加排序的序列按此间隔数从第1个元素开始依次分成若干个子序列。
 * 3.在各个子序列中采用某种排序算法（例如插入排序算法）进行排序。
 * 4.减少间隔数，重复2-4步，直到间隔数step= 1。
 */
public class ShellSortAlgorithm {
	public void shellSort(int arrays[]) {
        int length = arrays.length;
        int temp;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                temp = arrays[i];
                int j = i - step;
                while (j >= 0 && arrays[j] > temp) {
                	arrays[j + step] = arrays[j];
                    j -= step;
                }
                arrays[j + step] = temp;
            }
        }
	}
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,-62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("原始数据集："+Arrays.toString(arrays));
		new ShellSortAlgorithm().shellSort(arrays);
		System.out.println("归并排序后："+Arrays.toString(arrays));	
	}
}
