package linkedList;

public class SingleLinkedList {

	//定义节点
	static class ListNode {
		int val; // 链表存储的值
		ListNode next; // 指针，指向下一个节点
		ListNode() {
			
		}
		ListNode(int val) { 
			this.val = val; 
		}
		ListNode(int val, ListNode next) { 
			this.val = val;
			this.next = next; 
		}
	}
	
	int size; // 链表长度
	ListNode headNode; //链表头结点
//	ListNode dummyHead = new ListNode(0);
	public SingleLinkedList() {
		headNode = null;
		size = 0;
	}
	
	//头插法
	public void headInsertion(int val) {
		ListNode newListNode = new ListNode(); // 新建一个对象，即为要插入的节点
		newListNode.val = val; // 对节点存入要插入的值
		if (headNode == null) { //头结点为空即是个空链表，头结点直接指向要插入的节点即可
			headNode = newListNode;
		} else {
			newListNode.next = headNode; // 要插入的节点应放在第一个，所以它的下一个节点指向head
			headNode = newListNode; // 插入后新插入的节点是新的头结点
		}
		size ++;
	}
	
	//头插法（使用虚拟头结点的方法）
//	public void headInsertionVirtual(int val) {
//		ListNode dummyHead = new ListNode(0, this.head);
//		ListNode newListNode = new ListNode();
//		newListNode.val = val;
//		ListNode currentNode = dummyHead;
//		currentNode.val = val;
//		currentNode.next = dummyHead.next;
//		size ++;
//	}
	
	//任意位置插入
	public void insert(int index, int val) {
		if (index > size) {
			System.out.println("越界");
			return;
		}
		ListNode newListNode = new ListNode(); // 存放添加元素的链表节点
		newListNode.val = val;
		if (index == 0) { // 插入到第0位，和头插法相同
			if (headNode == null) {
				headNode = newListNode;
			} else {
				newListNode.next = headNode;
				headNode = newListNode;
			}
			size ++;		
		} else {
			ListNode prevNode = headNode; // 创建一个指向头结点的指针
			for (int i = 0; i < index-1; i++) {
				prevNode = prevNode.next; // 用该指针找到要添加元素位置的前一个
			}
			newListNode.next = prevNode.next; // 添加的节点的next指针指向要添加位置的下一个
			prevNode.next = newListNode; //前一个节点的next指针指向添加的节点
			size ++;
		}
	}
	
	//尾插法
	public void tailInsertion(int val) {
		ListNode newListNode = new ListNode();
		newListNode.val = val; 
		ListNode prevNode = headNode; // 创建一个指向头结点的指针
		for (int i = 0; i < size-1; i++) {
			prevNode = prevNode.next;// 用该指针找到要最后一个节点的前一个节点
		}
		newListNode.next = prevNode.next;
		prevNode.next = newListNode;
		size ++;
	}
	
	//删除第index个元素
	public void deleteByIndex(int index) {
		if (index > size) {
			System.out.println("越界");
			return;
		}
		if (headNode == null) {
			System.out.println("空链表");
		} else {
			if (index == 0) { // 删除第一个元素，直接把头结点指向第二个元素即可
				headNode = headNode.next;
				size --;
			} else {
				ListNode prevNode = headNode; // 创建一个指向头结点的指针
				for (int i = 0; i < index-1; i++) {
					prevNode = prevNode.next; // 用该指针找到要删除元素位置的前一个
				}
				System.out.println("delete："+prevNode.next.val);
				// 要删除的元素的前一个节点的next指针指向下一个节点，完成删除
				prevNode.next = prevNode.next.next; 
				size --;
			}
		}
	}
	
	//删除val元素
	public void deleteByValue(int val) {
		if (headNode == null) {
			System.out.println("空链表");
		} else {
			while(val == headNode.val){// 如果头结点是要删除的元素
				headNode = headNode.next;
				size --;
			}
			ListNode prevNode = headNode; // 创建指针，指向被操作的节点的前一位
			ListNode currentNode = headNode; // 创建指针，指向被操作的节点
			while(prevNode != null && currentNode != null){
				if(currentNode.val == val) {
					prevNode.next = currentNode.next;
					size--;
				} else {
					prevNode = currentNode;
				}
				currentNode = currentNode.next;
			}
		}
	}
	
	public void getElum(int index) {
		if (index > size) {
			System.out.println("越界");
			return;
		}
		ListNode currentNode = headNode;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		int result = currentNode.val;
		System.out.println(result);
	}
	
	public void update(int index, int val) {
		if (index > size) {
			System.out.println("越界");
			return;
		}
		ListNode currentNode = headNode;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		currentNode.val = val;
	}
	
	// 创建一个链表  1->2
	public void creatLinkList() {
		ListNode node1 = new ListNode();
		node1.val = 2;
		ListNode node2 = new ListNode();
		node2.val = 1;
		node1.next = node2;
		headNode = node1;
		size++;
		size++;
	}
	
	
	//打印链表
	public void display(){
		ListNode current = headNode;
		while(current != null){
			System.out.print(current.val);
			if (current.next != null) {
				System.out.print("->");
			}
			current = current.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		SingleLinkedList singleLinkedList = new SingleLinkedList();

		singleLinkedList.creatLinkList();
		singleLinkedList.headInsertion(5);
		singleLinkedList.headInsertion(8);
		singleLinkedList.headInsertion(9);
		singleLinkedList.headInsertion(5);
		singleLinkedList.display();
		
		singleLinkedList.insert(0,3);
		singleLinkedList.insert(0,3);
		singleLinkedList.insert(2,3);
		singleLinkedList.insert(2,3);
		singleLinkedList.insert(2,7);
		singleLinkedList.display();
		
		singleLinkedList.tailInsertion(1);
		singleLinkedList.display();
		
		singleLinkedList.deleteByIndex(1);
		singleLinkedList.display();
		
		singleLinkedList.deleteByValue(3);
		singleLinkedList.display();
		singleLinkedList.getElum(0);

		singleLinkedList.update(1, 6);
		singleLinkedList.display();
	}

}
