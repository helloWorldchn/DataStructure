package stack;

// 数组栈
public class ArrayStack {
	
	int maxSize;
	int[] list;    //数组模拟栈，数据放在该数组
	int top = 0;   //表示栈顶，初始化为0
	// 初始化栈
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		list = new int[maxSize];
		
	}
	
	// 入栈操作
	public void push(int val) {
		if (top >= maxSize) {
			System.out.println("The stack is max!");
		} else {
			list[top] = val;
			top++;
		}
	}
	
	// 出栈操作
	public void pop() {
		if (top == 0) {
			System.out.println("Error! The stack is empty!");
		} else {
			top--;
		}
	}
	
	// 打印栈
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
	
	// 清空栈
    public void clear() {
    	top = 0;
    }
    
    // 判空
    public boolean isEmpty() {
    	if (top == 0) {
    		return true;
    	} else {
    		return false;   		
    	}
    }
 
    // 返回栈顶元素
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
