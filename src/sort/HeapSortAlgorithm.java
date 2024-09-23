package sort;

import java.util.Arrays;

/*
  * ������ Heap Sort
 * 1.����һ����Heap[0����n-1]��
 * 2.�Ѷ��ף����ֵ���Ͷ�β������
 * 3.�Ѷѵĳߴ���С1�������� shift_down(0)��Ŀ���ǰ��µ����鶥�����ݵ�������Ӧλ�ã�
 * 4.�ظ����� 2��ֱ���ѵĳߴ�Ϊ 1��
 */
public class HeapSortAlgorithm {


    /**
     * ���������Ҫ��ڷ�������������
     */
    public void heapSort(int[] arrays) {
        /*
         *  ��һ����������ѻ�
         *  beginIndex = ��һ����Ҷ�ӽڵ㡣
         *  �ӵ�һ����Ҷ�ӽڵ㿪ʼ���ɡ���������һ��Ҷ�ӽڵ㿪ʼ��
         *  Ҷ�ӽڵ���Կ����ѷ��϶�Ҫ��Ľڵ㣬���ڵ�������Լ����Լ�����ֵΪ���
         */
        int len = arrays.length - 1;
        int beginIndex = (arrays.length >> 1)- 1;
        for (int i = beginIndex; i >= 0; i--) {
            maxHeapify(i, len, arrays);
        }
        /*
         * �ڶ������Զѻ���������
         * ÿ�ζ����Ƴ����ĸ��ڵ�A[0]������β���ڵ�λ�õ�����ͬʱ�������� - 1��
         * Ȼ����������������ڵ��ĩβԪ�أ�ʹ����϶ѵ����ԡ�
         * ֱ��δ����Ķѳ���Ϊ 0��
         */
        for (int i = len; i > 0; i--) {
            swap(0, i, arrays);
            maxHeapify(0, i - 1, arrays);
        }
    }

    private void swap(int i, int j, int arrays[]) {
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }

    /**
         * ��������Ϊ index �������ݣ�ʹ����϶ѵ����ԡ�
     *
     * @param index ��Ҫ�ѻ���������ݵ�����
     * @param len δ����Ķѣ����飩�ĳ���
     */
    private void maxHeapify(int index, int len, int[] arrays) {
        int li = (index << 1) + 1; // ���ӽڵ�����
        int ri = li + 1;           // ���ӽڵ�����
        int cMax = li;             // �ӽڵ�ֵ���������Ĭ�����ӽڵ㡣
        if (li > len) return;      // ���ӽڵ������������㷶Χ��ֱ�ӷ��ء�
        if (ri <= len && arrays[ri] > arrays[li]) // ���ж������ӽڵ㣬�ĸ��ϴ�
            cMax = ri;
        if (arrays[cMax] > arrays[index]) {
            swap(cMax, index, arrays);      // ������ڵ㱻�ӽڵ������
            maxHeapify(cMax, len, arrays);  // ����Ҫ�����жϻ��º�ĸ��ڵ��Ƿ���϶ѵ����ԡ�
        }
    }

<<<<<<< HEAD
    /**
     * ��������
     *
     * �����
     * [0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9]
     */
=======
>>>>>>> 210cfec (add the SortAlgorithm)
    public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,-62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("ԭʼ���ݼ���"+Arrays.toString(arrays));
		new HeapSortAlgorithm().heapSort(arrays);
        System.out.println("  �������"+Arrays.toString(arrays));
    }
}