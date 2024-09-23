package sort;

import java.util.Arrays;

/*
 * 归并排序（Merge Sort）
 * 1.从第一个元素开始，该元素可以认为已经被排序
 * 2.取出下一个元素，在已经排序的元素序列中从后向前扫描（从第2个元素开始和前面已经排序好的比较）
 * 3.如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * 5.将新元素插入到该位置后
 * 6.重复步骤2~5
 */
public class MergeSortAlgorithm {
	public void mergeSort(int[] array) {
	    if(array == null || array.length <= 1) {
	        return;
	    }
	    int[] newArray = new int[array.length];
	    sort(array, 0, array.length-1, newArray);
	}
	// 排序
	public void sort(int arrays[], int left, int right, int[] newArray) {
	    if(left >= right) {
	        return;
	    }
        int mid = left + (right - left)/2;;
        sort(arrays, left, mid, newArray);
        sort(arrays, mid + 1, right, newArray);
        for(int i = left; i <= right; i++) {
            newArray[i] = arrays[i];
        }

    	// 归并
        int i = left;
        int j = mid + 1;
        int k = left;
        while(i <= mid && j <= right) {
            if(newArray[i] <= newArray[j]) { // 等号会影响算法的稳定性
                arrays[k++] = newArray[i++];
            } else {
                arrays[k++] = newArray[j++];
            }
        }
        if(i <= mid) {
            arrays[k++] = newArray[i++];
        }
	}

	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,-62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("原始数据集："+Arrays.toString(arrays));
		new MergeSortAlgorithm().mergeSort(arrays);
		System.out.println("归并排序后："+Arrays.toString(arrays));	
	}
}
