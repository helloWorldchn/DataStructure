package queue;

// ����
public class ArrayQueue {
	
	int maxSize; // �����������
	int[] list; //����ģ����У����ݷ��ڸ�����
	int front = 0; //��ͷָ��front��ָ���ͷԪ��
	int rear = 0; //��βָ��rear��ָ����һ�����Ԫ�صĴ洢λ��
	int currentSize; // ��¼��ǰ�����м���Ԫ��
	// ��ʼ������
	public ArrayQueue(int maxSize) {
		this.maxSize = maxSize;
		list = new int[maxSize];
        front = 0;
        rear = 0;
        currentSize = 0;
	}
	
	// ���
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
	
	// ����
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
