package sort;

import java.util.Arrays;

/*
 * ð������Bubble Sort��
 * 1.�Ƚ����ڵ�Ԫ�أ������һ���ȵڶ����󣬾ͽ�����������
 * 2.��ÿһ������Ԫ����ͬ���Ĺ������ӿ�ʼ��һ�Ե���β�����һ�ԡ��ⲽ���������Ԫ�ػ�����������
 * 3.������е�Ԫ���ظ����ϵĲ��裬�������һ����
 * 4.����ÿ�ζ�Խ��Խ�ٵ�Ԫ���ظ�����Ĳ��裬ֱ��û���κ�һ��������Ҫ�Ƚϡ�
 */
public class BubbleSortAlgorithm {

	public void bubbleSort(int[] arrays) {
		for (int i = 0; i < arrays.length; i++) {
			for (int j = 0; j+1 < arrays.length-i; j++) {
				if (arrays[j] > arrays[j+1]) {
					int temp = arrays[j+1];
					arrays[j+1] = arrays[j];
					arrays[j] = temp;
				}
			}
//			System.out.println("��"+(i+1)+"��֮��"+Arrays.toString(arrays));
		}
	}
	
	public static void main(String[] args) {
		int arrays[] = {6,2,13,27,49,74,12,64,5,4,-62,99,5,17,18,23,34,15,35,25,53,51};
		System.out.println("ԭʼ���ݼ���"+Arrays.toString(arrays));
		new BubbleSortAlgorithm().bubbleSort(arrays);
		System.out.println("ð�������"+Arrays.toString(arrays));
	}
}
