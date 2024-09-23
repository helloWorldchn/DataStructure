package sort;

import java.util.Arrays;

/*
 * ��������Radix Sort��
 * 1.��������λ���и�ɲ�ͬ������
 * 2.Ȼ��ÿ��λ���ֱ�Ƚϡ�
 */

public class RadixSortAlgorithm {
	//��������
	public static void radixSort(int arrays[]) {
        int maxValue = Arrays.stream(arrays).max().getAsInt(); // �ҳ�����arrays�е����ֵ
        int exp = 1; // ָ��
        int[] buf = new int[arrays.length]; // �洢"����������"����ʱ����
        // �Ӹ�λ��ʼ��������a��"ָ��"��������
        while (maxValue >= exp) {
            int[] buckets = new int[10]; // �����ֳ��ֵĴ����洢��buckets[]��
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
            // ������õ����ݸ�ֵ��a[]
            for (int i = 0; i < arrays.length; i++) {
            	arrays[i] = buf[i];
            }
            exp *= 10;
        }
	}
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("ԭʼ���ݼ���"+Arrays.toString(arrays));
		radixSort(arrays);
		System.out.println("���������"+Arrays.toString(arrays));
	}
}
