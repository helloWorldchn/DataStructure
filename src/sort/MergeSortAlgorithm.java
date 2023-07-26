package sort;

import java.util.Arrays;

/*
 * �鲢����Merge Sort��
 * 1.�ӵ�һ��Ԫ�ؿ�ʼ����Ԫ�ؿ�����Ϊ�Ѿ�������
 * 2.ȡ����һ��Ԫ�أ����Ѿ������Ԫ�������дӺ���ǰɨ�裨�ӵ�2��Ԫ�ؿ�ʼ��ǰ���Ѿ�����õıȽϣ�
 * 3.�����Ԫ�أ������򣩴�����Ԫ�أ�����Ԫ���Ƶ���һλ��
 * 4.�ظ�����3��ֱ���ҵ��������Ԫ��С�ڻ��ߵ�����Ԫ�ص�λ��
 * 5.����Ԫ�ز��뵽��λ�ú�
 * 6.�ظ�����2~5
 */
public class MergeSortAlgorithm {
	public void mergeSort(int[] array) {
	    if(array == null || array.length <= 1) {
	        return;
	    }
	    int[] newArray = new int[array.length];
	    sort(array, 0, array.length-1, newArray);
	}
	// ����
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

    	// �鲢
        int i = left;
        int j = mid + 1;
        int k = left;
        while(i <= mid && j <= right) {
            if(newArray[i] <= newArray[j]) { // �ȺŻ�Ӱ���㷨���ȶ���
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
		System.out.println("ԭʼ���ݼ���"+Arrays.toString(arrays));
		new MergeSortAlgorithm().mergeSort(arrays);
		System.out.println("�鲢�����"+Arrays.toString(arrays));	
	}
}
