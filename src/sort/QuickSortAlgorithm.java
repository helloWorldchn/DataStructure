package sort;

import java.util.Arrays;
/*
 * ��������Quick Sort������˼��Ϊ���η�
 * 1.������������һ��Ԫ�أ���Ϊ����׼��
 * 2.�����������У����бȻ�׼ֵС��Ԫ�ذ��ڻ�׼��ǰ�棬���бȻ�׼���Ԫ�ذ��ڻ�׼�ĺ��档
 * 3.�ݹ�ذ�С�ڻ�׼ֵԪ�ص������кʹ��ڻ�׼ֵԪ�ص�����������
 */

public class QuickSortAlgorithm {
	//��������
	public void quickSort(int arrays[], int left, int right) {
	    if (right < left) { //Ԫ�ظ���С��һ��
	        return;
	    }
	    int pivotKey  = arrays[left];
	    int l = left; //��߽�
	    int r = right; //�ұ߽�
	    while (l < r) {
	    	//�ұߵ�ֵ���ڻ���ʱ����ָ������1λ�����ҳ��ұ�С�ڻ�����ֵ
	    	while (l < r && arrays[r] >= pivotKey) {
	    		r--;
	    	}	    	
	    	if (l < r) {
				arrays[l] = arrays[r]; //����ָ���ӦС�ڻ�����ֵ�ŵ���ָ����ָ��λ��
				l++; //��ָ�뱻��ֵΪ�ұ�С�ڻ�����ֵ������
			}	
	    	
	    	//��ߵ�ֵС�ڻ���ʱ����ָ������1λ�����ҳ���ߴ��ڻ�����ֵ
	    	while (l < r && arrays[l] <= pivotKey) {
	    		l++;
	    	}
	    	if (l < r) { //�෴���ҵ����ڻ������±�
				arrays[r] = arrays[l];
				r--;
			}
		}
	    arrays[l] = pivotKey;
	    //����ǰ�������벿�֣��������±�Ϊpivot��Ԫ��
	    quickSort(arrays, left, r-1);
	    //����ǰ������Ұ벿�֣��������±�Ϊpivot��Ԫ��
	    quickSort(arrays, l+1, right);	    
	}
	
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,-62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("ԭʼ���ݼ���"+Arrays.toString(arrays));
		new QuickSortAlgorithm().quickSort(arrays, 0, arrays.length-1);
		System.out.println("���������"+Arrays.toString(arrays));
	}
	
}
