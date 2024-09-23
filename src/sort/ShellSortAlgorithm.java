package sort;

import java.util.Arrays;

/*
  * ϣ������Shell Sort��
  * ϣ������Ҳ��һ�ֲ�������
 * 1.ȷ��һ��Ԫ�ؼ����step��
 * 2.���μ���������а��˼�����ӵ�1��Ԫ�ؿ�ʼ���ηֳ����ɸ������С�
 * 3.�ڸ����������в���ĳ�������㷨��������������㷨����������
 * 4.���ټ�������ظ�2-4����ֱ�������step= 1��
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
		System.out.println("ԭʼ���ݼ���"+Arrays.toString(arrays));
		new ShellSortAlgorithm().shellSort(arrays);
		System.out.println("�鲢�����"+Arrays.toString(arrays));	
	}
}
