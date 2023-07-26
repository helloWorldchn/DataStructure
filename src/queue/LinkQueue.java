package queue;

// ��ʽ����
public class LinkQueue {
	// ��ʽ���еĽ�㹹��
	static class QueueNode{	
		int val; 
		QueueNode next;
		// ��ʼ��ջ
		QueueNode() {
		}
		QueueNode(int val) { 
			this.val = val; 
		}
		QueueNode(int val, QueueNode next) {
			this.val = val;
			this.next = next;
		}
	}

	int size = 0; // ��¼��ǰ�����м���Ԫ��
	QueueNode front; // ��ͷָ��front��ָ���ͷԪ��
	QueueNode rear; // ��βָ��rear��ָ����һ�����Ԫ�صĴ洢λ��
	QueueNode back; // ��βָ��back��ָ���βԪ��
	
	public void enQueue(int val) {
		 QueueNode queueNode = new QueueNode();
		 queueNode.val = val;
		 if (front == null) {
			 front = queueNode;
			 back = queueNode;
			 System.out.println(val + " has been queued!");
			 size ++;
		 } else {
			 back.next = queueNode;
			 back = back.next;
			 System.out.println(val + " has been queued!");
			 size ++;
		 }
	}
	
	public void deQueue() {
		if (front == null) {
			System.out.println("Error! The queue is empty!");
		} else {
			front = front.next;
		}
	}
	
	public void display () {
		QueueNode current = front;
		while(current != null){
			System.out.print(current.val);
			if (current.next != null) {
				System.out.print(" ");
			}
			current = current.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		LinkQueue linkQueue = new LinkQueue();
		
		linkQueue.enQueue(1);
		linkQueue.enQueue(2);
		linkQueue.enQueue(3);
		linkQueue.enQueue(4);
		
		linkQueue.display();
		linkQueue.deQueue();
		
		linkQueue.display();
		
	}
	
	
	
}
