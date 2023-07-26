package search;

public class BinarySearch {
    public int binarySearch(int[] nums, int target){
        int mid = 0;
        int left = 0;
        int right = nums.length-1;
        while (left <= right) {
            mid = (left+right)/2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] >= target) {
                right = mid-1;
            }
        }
        return left;
    }
}
/**
 * ��Χ��ѯ����
 * ��ʼ��:
 *   int left = 0;
 *   int right = nums.length - 1;
 * ѭ������
 *   left <= right
 * �ұ�ȡֵ
 *   right = mid - 1
 * ���ȡֵ
 *   left = mid + 1
 * ��ѯ����
 *   >= targetֵ, �� nums[mid] >= targetʱ, ����right = mid - 1
 *   >  targetֵ, �� nums[mid] >  targetʱ, ����right = mid - 1
 *   <= targetֵ, �� nums[mid] <= targetʱ, ����left = mid + 1
 *   <  targetֵ, �� nums[mid] <  targetʱ, ����left = mid + 1
 * ���
 *   �����(������), ����left
 *   ��С��(������), ����right
 * ����˼��: Ҫ��ĳ��ֵ, �����ʱ������ֵʱ, ��ǰָ��(����rightָ��)Ҫ�����, ������һ��ָ��(leftָ��)�����(������left <= right�е�=��), ���ҵ���
 */
