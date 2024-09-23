package linkedList;



public class DoubleLinkedList {
	//定义节点
	static class ListNode {
		int val; // 链表存储的值
		ListNode next; // 指针，指向下一个节点
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
	
	int size; // 链表长度
	ListNode head; //链表头结点
//	ListNode dummyHead = new ListNode(0);
	public DoubleLinkedList() {
		head = null;
		size = 0;
	}
}
