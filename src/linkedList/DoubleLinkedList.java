package linkedList;



public class DoubleLinkedList {
	//����ڵ�
	static class ListNode {
		int val; // ����洢��ֵ
		ListNode next; // ָ�룬ָ����һ���ڵ�
		ListNode prev;
		ListNode() {
			
		}
		ListNode(int val) { 
			this.val = val; 
		}
		ListNode(int val,ListNode prev, ListNode next) { 
			this.val = val;
			this.prev = prev;
			this.next = next;
		}
	}
	
	int size; // ������
	ListNode head; //����ͷ���
//	ListNode dummyHead = new ListNode(0);
	public DoubleLinkedList() {
		head = null;
		size = 0;
	}
}
