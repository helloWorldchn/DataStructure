package stack;

// ����ջ
public class ArrayStack {
	
	int maxSize;
	int[] list;    //����ģ��ջ�����ݷ��ڸ�����
	int top = 0;   //��ʾջ������ʼ��Ϊ0
	// ��ʼ��ջ
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		list = new int[maxSize];
		
	}
	
	// ��ջ����
	public void push(int val) {
		if (top >= maxSize) {
			System.out.println("The stack is max!");
		} else {
			list[top] = val;
			top++;
		}
	}
	
	// ��ջ����
	public void pop() {
		if (top == 0) {
			System.out.println("Error! The stack is empty!");
		} else {
			top--;
		}
	}
	
	// ��ӡջ
	public void display() {
        if(top == 0){
            System.out.println("The stack is empty!");
           return;
        }
		for(int i = 0; i < top; i++) {
			System.out.print(list[i]+" ");
		}
		System.out.println();
	}
	
	// ���ջ
    public void clear() {
    	top = 0;
    }
    
    // �п�
    public boolean isEmpty() {
    	if (top == 0) {
    		return true;
    	} else {
    		return false;   		
    	}
    }
 
    // ����ջ��Ԫ��
    public Object peek() {
    	if (top != 0) {
    		return list[top-1];    		
    	} else {
    		return null;
    	}
    }
	
	public static void main(String[] args) {
		ArrayStack arrayStack = new ArrayStack(5);

		System.out.println(arrayStack.isEmpty());
		arrayStack.push(1);
		arrayStack.push(2);
		arrayStack.push(3);
		arrayStack.push(4);
		arrayStack.display();
		
		arrayStack.pop();
		arrayStack.display();
		
		arrayStack.push(5);
		arrayStack.display();
		System.out.println(arrayStack.peek());
		System.out.println(arrayStack.isEmpty());
		arrayStack.clear();
		arrayStack.display();
		System.out.println(arrayStack.peek());
		System.out.println(arrayStack.isEmpty());
	}
	
	
}
