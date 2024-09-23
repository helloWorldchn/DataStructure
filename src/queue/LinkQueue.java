package queue;

// 链式队列
public class LinkQueue {
	// 链式队列的结点构造
	static class QueueNode{	
		int val; 
		QueueNode next;
		// 初始化栈
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

	int size = 0; // 记录当前队列有几个元素
	QueueNode front; // 队头指针front，指向队头元素
	QueueNode rear; // 队尾指针rear，指向下一个入队元素的存储位置
	QueueNode back; // 队尾指针back，指向队尾元素
	
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
