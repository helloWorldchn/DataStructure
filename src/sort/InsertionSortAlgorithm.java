package sort;

import java.util.Arrays;
/*
 * ��������Insertion Sort��
 * 1.�ӵ�һ��Ԫ�ؿ�ʼ����Ԫ�ؿ�����Ϊ�Ѿ�������
 * 2.ȡ����һ��Ԫ�أ����Ѿ������Ԫ�������дӺ���ǰɨ�裨�ӵ�2��Ԫ�ؿ�ʼ��ǰ���Ѿ�����õıȽϣ�
 * 3.�����Ԫ�أ������򣩴�����Ԫ�أ�����Ԫ���Ƶ���һλ��
 * 4.�ظ�����3��ֱ���ҵ��������Ԫ��С�ڻ��ߵ�����Ԫ�ص�λ��
 * 5.����Ԫ�ز��뵽��λ�ú�
 * 6.�ظ�����2~5
 */

public class InsertionSortAlgorithm {	
	public void insertionSort(int arrays[]) {
		// �ӵڶ���Ԫ�ؿ�ʼ����ǰ����������бȽ�
		int j;
		for (int i = 1; i < arrays.length; i++) {
			int insertVal = arrays[i]; // ��¼Ҫ�����ֵ����������temp�У���ֹ�����ƶ�ʱ��Ԫ�ض�ʧ
			for (j = i; j > 0 && arrays[j-1] > insertVal; j--) {
				arrays[j] = arrays[j-1];
			}
			arrays[j] = insertVal;
//			System.out.println("��"+i+"��֮��"+Arrays.toString(arrays));
		}
	}
	
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,-62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("ԭʼ���ݼ���"+Arrays.toString(arrays));
		new InsertionSortAlgorithm().insertionSort(arrays);
		System.out.println("���������"+Arrays.toString(arrays));	
	}
}
