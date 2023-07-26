package sort;

import java.util.Arrays;
/*
 * 快速排序（Quick Sort）基本思想为分治法
 * 1.从数列中挑出一个元素，称为“基准”
 * 2.重新排序数列，所有比基准值小的元素摆在基准的前面，所有比基准大的元素摆在基准的后面。
 * 3.递归地把小于基准值元素的子数列和大于基准值元素的子数列排序
 */

public class QuickSortAlgorithm {
	//快速排序法
	public void quickSort(int arrays[], int left, int right) {
	    if (right < left) { //元素个数小于一个
	        return;
	    }
	    int pivotKey  = arrays[left];
	    int l = left; //左边界
	    int r = right; //右边界
	    while (l < r) {
	    	//右边的值大于基数时，右指针左移1位。即找出右边小于基数的值
	    	while (l < r && arrays[r] >= pivotKey) {
	    		r--;
	    	}	    	
	    	if (l < r) {
				arrays[l] = arrays[r]; //将右指针对应小于基数的值放到左指针所指的位置
				l++; //左指针被赋值为右边小于基数的值后，自增
			}	
	    	
	    	//左边的值小于基数时，左指针右移1位。即找出左边大于基数的值
	    	while (l < r && arrays[l] <= pivotKey) {
	    		l++;
	    	}
	    	if (l < r) { //相反，找到大于基数的下标
				arrays[r] = arrays[l];
				r--;
			}
		}
	    arrays[l] = pivotKey;
	    //处理当前数组的左半部分，不包括下标为pivot的元素
	    quickSort(arrays, left, r-1);
	    //处理当前数组的右半部分，不包括下标为pivot的元素
	    quickSort(arrays, l+1, right);	    
	}
	
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,-62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("原始数据集："+Arrays.toString(arrays));
		new QuickSortAlgorithm().quickSort(arrays, 0, arrays.length-1);
		System.out.println("快速排序后："+Arrays.toString(arrays));
	}
	
}
