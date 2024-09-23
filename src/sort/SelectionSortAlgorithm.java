package sort;

import java.util.Arrays;

/*
 * ѡ������ Selection Sort
 * 1.������δ�����������ҵ���С����Ԫ�أ���ŵ��������е���ʼλ�á�
 * 2.�ٴ�ʣ��δ����Ԫ���м���Ѱ����С����Ԫ�أ�Ȼ��ŵ����������е�ĩβ��
 * 3.�ظ��ڶ�����ֱ������Ԫ�ؾ�������ϡ�
 */
public class SelectionSortAlgorithm {
	// ѡ������
	public static void selectionSort(int arrays[]) {
        // �ܹ�Ҫ���� N-1 �ֱȽ�
        for (int i = 0; i < arrays.length-1; i++) {
            int min = i;

            // ÿ����Ҫ�ȽϵĴ��� N-i
            for (int j = i + 1; j < arrays.length; j++) {
                if (arrays[j] < arrays[min]) {
                    // ��¼Ŀǰ���ҵ�����СֵԪ�ص��±�
                    min = j;
                }
            }

            // ���ҵ�����Сֵ��iλ�����ڵ�ֵ���н���
            if (i != min) {
                int temp = arrays[i];
                arrays[i] = arrays[min];
                arrays[min] = temp;
            }

        }
	}
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,-62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("ԭʼ���ݼ���"+Arrays.toString(arrays));
		selectionSort(arrays);
		System.out.println("ѡ�������"+Arrays.toString(arrays));
	}
}
