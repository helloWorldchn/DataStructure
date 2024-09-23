package sort;

import java.util.Arrays;

/*
  * 堆排序 Heap Sort
 * 1.创建一个堆Heap[0……n-1]；
 * 2.把堆首（最大值）和堆尾互换；
 * 3.把堆的尺寸缩小1，并调用 shift_down(0)，目的是把新的数组顶端数据调整到相应位置；
 * 4.重复步骤 2，直到堆的尺寸为 1。
 */
public class HeapSortAlgorithm {


    /**
     * 堆排序的主要入口方法，共两步。
     */
    public void heapSort(int[] arrays) {
        /*
         *  第一步：将数组堆化
         *  beginIndex = 第一个非叶子节点。
         *  从第一个非叶子节点开始即可。无需从最后一个叶子节点开始。
         *  叶子节点可以看作已符合堆要求的节点，根节点就是它自己且自己以下值为最大。
         */
        int len = arrays.length - 1;
        int beginIndex = (arrays.length >> 1)- 1;
        for (int i = beginIndex; i >= 0; i--) {
            maxHeapify(i, len, arrays);
        }
        /*
         * 第二步：对堆化数据排序
         * 每次都是移出最顶层的根节点A[0]，与最尾部节点位置调换，同时遍历长度 - 1。
         * 然后从新整理被换到根节点的末尾元素，使其符合堆的特性。
         * 直至未排序的堆长度为 0。
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
         * 调整索引为 index 处的数据，使其符合堆的特性。
     *
     * @param index 需要堆化处理的数据的索引
     * @param len 未排序的堆（数组）的长度
     */
    private void maxHeapify(int index, int len, int[] arrays) {
        int li = (index << 1) + 1; // 左子节点索引
        int ri = li + 1;           // 右子节点索引
        int cMax = li;             // 子节点值最大索引，默认左子节点。
        if (li > len) return;      // 左子节点索引超出计算范围，直接返回。
        if (ri <= len && arrays[ri] > arrays[li]) // 先判断左右子节点，哪个较大。
            cMax = ri;
        if (arrays[cMax] > arrays[index]) {
            swap(cMax, index, arrays);      // 如果父节点被子节点调换，
            maxHeapify(cMax, len, arrays);  // 则需要继续判断换下后的父节点是否符合堆的特性。
        }
    }

<<<<<<< HEAD
    /**
     * 测试用例
     *
     * 输出：
     * [0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9]
     */
=======
>>>>>>> 210cfec (add the SortAlgorithm)
    public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,-62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("原始数据集："+Arrays.toString(arrays));
		new HeapSortAlgorithm().heapSort(arrays);
        System.out.println("  堆排序后："+Arrays.toString(arrays));
    }
}