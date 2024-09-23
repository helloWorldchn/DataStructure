package linkedList;

public class SingleLinkedList {

	//����ڵ�
	static class ListNode {
		int val; // ����洢��ֵ
		ListNode next; // ָ�룬ָ����һ���ڵ�
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
	
	int size; // ������
	ListNode headNode; //����ͷ���
//	ListNode dummyHead = new ListNode(0);
	public SingleLinkedList() {
		headNode = null;
		size = 0;
	}
	
	//ͷ�巨
	public void headInsertion(int val) {
		ListNode newListNode = new ListNode(); // �½�һ�����󣬼�ΪҪ����Ľڵ�
		newListNode.val = val; // �Խڵ����Ҫ�����ֵ
		if (headNode == null) { //ͷ���Ϊ�ռ��Ǹ�������ͷ���ֱ��ָ��Ҫ����Ľڵ㼴��
			headNode = newListNode;
		} else {
			newListNode.next = headNode; // Ҫ����Ľڵ�Ӧ���ڵ�һ��������������һ���ڵ�ָ��head
			headNode = newListNode; // ������²���Ľڵ����µ�ͷ���
		}
		size ++;
	}
	
	//ͷ�巨��ʹ������ͷ���ķ�����
//	public void headInsertionVirtual(int val) {
//		ListNode dummyHead = new ListNode(0, this.head);
//		ListNode newListNode = new ListNode();
//		newListNode.val = val;
//		ListNode currentNode = dummyHead;
//		currentNode.val = val;
//		currentNode.next = dummyHead.next;
//		size ++;
//	}
	
	//����λ�ò���
	public void insert(int index, int val) {
		if (index > size) {
			System.out.println("Խ��");
			return;
		}
		ListNode newListNode = new ListNode(); // ������Ԫ�ص�����ڵ�
		newListNode.val = val;
		if (index == 0) { // ���뵽��0λ����ͷ�巨��ͬ
			if (headNode == null) {
				headNode = newListNode;
			} else {
				newListNode.next = headNode;
				headNode = newListNode;
			}
			size ++;		
		} else {
			ListNode prevNode = headNode; // ����һ��ָ��ͷ����ָ��
			for (int i = 0; i < index-1; i++) {
				prevNode = prevNode.next; // �ø�ָ���ҵ�Ҫ���Ԫ��λ�õ�ǰһ��
			}
			newListNode.next = prevNode.next; // ��ӵĽڵ��nextָ��ָ��Ҫ���λ�õ���һ��
			prevNode.next = newListNode; //ǰһ���ڵ��nextָ��ָ����ӵĽڵ�
			size ++;
		}
	}
	
	//β�巨
	public void tailInsertion(int val) {
		ListNode newListNode = new ListNode();
		newListNode.val = val; 
		ListNode prevNode = headNode; // ����һ��ָ��ͷ����ָ��
		for (int i = 0; i < size-1; i++) {
			prevNode = prevNode.next;// �ø�ָ���ҵ�Ҫ���һ���ڵ��ǰһ���ڵ�
		}
		newListNode.next = prevNode.next;
		prevNode.next = newListNode;
		size ++;
	}
	
	//ɾ����index��Ԫ��
	public void deleteByIndex(int index) {
		if (index > size) {
			System.out.println("Խ��");
			return;
		}
		if (headNode == null) {
			System.out.println("������");
		} else {
			if (index == 0) { // ɾ����һ��Ԫ�أ�ֱ�Ӱ�ͷ���ָ��ڶ���Ԫ�ؼ���
				headNode = headNode.next;
				size --;
			} else {
				ListNode prevNode = headNode; // ����һ��ָ��ͷ����ָ��
				for (int i = 0; i < index-1; i++) {
					prevNode = prevNode.next; // �ø�ָ���ҵ�Ҫɾ��Ԫ��λ�õ�ǰһ��
				}
				System.out.println("delete��"+prevNode.next.val);
				// Ҫɾ����Ԫ�ص�ǰһ���ڵ��nextָ��ָ����һ���ڵ㣬���ɾ��
				prevNode.next = prevNode.next.next; 
				size --;
			}
		}
	}
	
	//ɾ��valԪ��
	public void deleteByValue(int val) {
		if (headNode == null) {
			System.out.println("������");
		} else {
			while(val == headNode.val){// ���ͷ�����Ҫɾ����Ԫ��
				headNode = headNode.next;
				size --;
			}
			ListNode prevNode = headNode; // ����ָ�룬ָ�򱻲����Ľڵ��ǰһλ
			ListNode currentNode = headNode; // ����ָ�룬ָ�򱻲����Ľڵ�
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
			System.out.println("Խ��");
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
			System.out.println("Խ��");
			return;
		}
		ListNode currentNode = headNode;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		currentNode.val = val;
	}
	
	// ����һ������  1->2
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
	
	
	//��ӡ����
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
