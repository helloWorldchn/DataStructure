package queue;

// 队列
public class ArrayQueue {
	
	int maxSize; // 队列最大容量
	int[] list; //数组模拟队列，数据放在该数组
	int front = 0; //队头指针front，指向队头元素
	int rear = 0; //队尾指针rear，指向下一个入队元素的存储位置
	int currentSize; // 记录当前队列有几个元素
	// 初始化队列
	public ArrayQueue(int maxSize) {
		this.maxSize = maxSize;
		list = new int[maxSize];
        front = 0;
        rear = 0;
        currentSize = 0;
	}
	
	// 入队
	public void enQueue (int val) {
		if (currentSize >= maxSize) {
			System.out.println("Error! The queue is full!");
		} else {
			list[rear % maxSize] = val;
			rear ++;
			currentSize ++;
			System.out.println(val + " has been queued!");
		}		
	}
	
	// 出队
	public void deQueue () {
		if (currentSize == 0) {
			System.out.println("Error! The queue is empty!");
		} else {
			int dequeueValue = list[front];
			front = (front + 1) % maxSize;
			currentSize --;
			System.out.println(dequeueValue + " is dequeued!");
		}
	}
	
	public void display () {
        if(rear == front){
            System.out.println("The queue is empty!");
           return;
        }
		for(int i = front; i < rear; i++) {
			System.out.print(list[i % maxSize]+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		ArrayQueue arrayQueue = new ArrayQueue(9);
		
		arrayQueue.enQueue(1);
		arrayQueue.enQueue(2);
		arrayQueue.enQueue(3);
		arrayQueue.enQueue(4);
		arrayQueue.enQueue(5);
		arrayQueue.enQueue(6);
		arrayQueue.enQueue(7);
		arrayQueue.enQueue(8);
		arrayQueue.enQueue(9);
		
		arrayQueue.display();
		
		arrayQueue.deQueue();
		arrayQueue.enQueue(1);
		arrayQueue.display();
	}
}
