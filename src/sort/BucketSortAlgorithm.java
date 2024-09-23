package sort;

import java.util.Arrays;
/*
  * Ͱ����BucketSort��
 * 1. Ѱ�����������Ԫ�غ���СԪ�ز�����Ͱ�Ĵ�С*bucketSize*�͸���*bucketCount��
 * 2. ��Ԫ�ؾ��ȵط�ɢ�����Ͱ�У�
 * 3. ��ÿ��Ͱ�е�Ԫ�ؽ�������ͨ��ʹ�õ��ǲ�������ȼ������㷨����
 * 4. �ϲ�����Ͱ�е�Ԫ�أ��õ��������С�
 */
public class BucketSortAlgorithm {
	
	public void bucketSort(int[] arrays) {
        if (arrays.length == 0) {
            return;
        }
        // Ѱ��������СԪ��
        int minValue = arrays[0];
        int maxValue = arrays[0];
        for (int value : arrays) {
            if (value < minValue) {
                minValue = value;
            } else if (value > maxValue) {
                maxValue = value;
            }
        }
        int bucketSize = (maxValue - minValue) / arrays.length + 1; // ����Ͱ�洢���ķ�Χ��С
        int bucketCount = (int) Math.floor((maxValue - minValue) / bucketSize) + 1; // ����Ͱ�ĸ�����
        int[][] buckets = new int[bucketCount][0];

        // ����ӳ�亯�������ݷ��䵽����Ͱ��
        for (int i = 0; i < arrays.length; i++) {
            int index = (int) Math.floor((arrays[i] - minValue) / bucketSize);
            buckets[index] = arraysAppend(buckets[index], arrays[i]);
        }

        int arraysIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length <= 0) {
                continue;
            }
            // ��ÿ��Ͱ������������ʹ���˿⺯���Ŀ�������
            Arrays.sort(bucket);
            for (int value : bucket) {
                arrays[arraysIndex++] = value;
            }
        }
	}
	
	// �Զ�����
    private int[] arraysAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }
    
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,-62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("ԭʼ���ݼ���"+Arrays.toString(arrays));
		new BucketSortAlgorithm().bucketSort(arrays);
		System.out.println("   Ͱ�����"+Arrays.toString(arrays));
	}
}
