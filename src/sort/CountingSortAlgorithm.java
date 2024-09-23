package sort;

import java.io.ObjectInputStream.GetField;
import java.util.Arrays;

/*
 * ��������CountingSort��
 * 1. �ҳ��������������������С��Ԫ�أ�����������СԪ�صĲ�ֵ��Χ���������������顣
 * 2. ͳ��������ÿ��Ԫ�صĳ��ִ���������ͳ�ƽ���洢�ڶ���ļ��������С�
 * 3. ���ݼ��������е�ֵ����ÿ��Ԫ�س��ֵĴ�����������Ӧ��Ԫ�ذ�˳������µ������У�ʹ���������е�Ԫ������
 */
public class CountingSortAlgorithm {

	public void countingSort(int[] arrays, int maxValue) {
        int bucketLen = maxValue + 1;
        int[] bucket = new int[bucketLen];
        for (int value : arrays) {
            bucket[value]++;
        }
        int sortedIndex = 0;
        for (int j = 0; j < bucketLen; j++) {
            while (bucket[j] > 0) {
            	arrays[sortedIndex++] = j;
                bucket[j]--;
            }
        }
    }
	
    private int getMaxValue(int[] arrays) {
        int maxValue = arrays[0];
        for (int value : arrays) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }
	
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("ԭʼ���ݼ���"+Arrays.toString(arrays));
		int maxValue = new CountingSortAlgorithm().getMaxValue(arrays);
		new CountingSortAlgorithm().countingSort(arrays, maxValue);
		System.out.println("���������"+Arrays.toString(arrays));
	}
}
