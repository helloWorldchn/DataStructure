package stack;

// 链栈
public class LinkStack {
	
	// 链栈的结点构造
	class StackNode{
		int val; // 每个节点存放的值
		StackNode next; // 指向下一个节点
		StackNode() {
		}
		StackNode(int val) { 
			this.val = val; 
		}
		StackNode(int val, StackNode next) {
			this.val = val;
			this.next = next; 
		}		
	}
	int size = 0;
	StackNode topNode;
	
	// 入栈操作！即把元素插入栈顶，和头插法相同
	public void push (int val) {
		StackNode newStackNode = new StackNode();
		newStackNode.val = val;
		if (topNode == null) {
			topNode = newStackNode;
			size ++;
		} else {
			newStackNode.next = topNode;
			topNode = newStackNode;
			size ++;
		}
		System.out.println(val + " has been stacked!");
	}
	
	// 出栈操作！
	public void pop () {
		if (topNode == null) {
			System.out.println("Error! The stack is empty!");
		} else {
			int popValue = topNode.val; // 接收一下出栈的元素，方便后面打印出来
			topNode = topNode.next; // 栈顶指针（即链表头结点）直接指向下一个元素即可删除
			size--;
			System.out.println(popValue + " is out of stack!");
		}
	}
	
	public void display () {
		StackNode current = topNode;
		while(current != null){
			System.out.print(current.val);
			if (current.next != null) {
				System.out.print(" ");
			}
			current = current.next;
		}
		System.out.println();
	}
	
	public int peek() {
		if(topNode == null) {
			System.out.println("The stack is empty!");
		}
		return topNode.val;		
	}
	
	public void clear () {
		if(topNode == null) {
			System.out.println("The stack is empty!");
		} else {
			topNode = null;
			System.out.println("Stack has been emptied!");
		}
	}
	
	public boolean isEmpty () {
		if (topNode == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		LinkStack linkStack = new LinkStack();
		
		linkStack.push(2);
		linkStack.push(3);
		linkStack.display();
		
		linkStack.pop();
		
		linkStack.display();
		System.out.println("The topNode is "+linkStack.peek());
		linkStack.clear();
		System.out.println("The stack is null? "+linkStack.isEmpty());
		linkStack.push(8);
		linkStack.push(7);
		linkStack.push(5);
		linkStack.display();
		System.out.println("The stack is null? "+linkStack.isEmpty());
	}
	
}
