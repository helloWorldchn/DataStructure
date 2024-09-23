package stack;

// ��ջ
public class LinkStack {
	
	// ��ջ�Ľ�㹹��
	class StackNode{
		int val; // ÿ���ڵ��ŵ�ֵ
		StackNode next; // ָ����һ���ڵ�
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
	
	// ��ջ����������Ԫ�ز���ջ������ͷ�巨��ͬ
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
	
	// ��ջ������
	public void pop () {
		if (topNode == null) {
			System.out.println("Error! The stack is empty!");
		} else {
			int popValue = topNode.val; // ����һ�³�ջ��Ԫ�أ���������ӡ����
			topNode = topNode.next; // ջ��ָ�루������ͷ��㣩ֱ��ָ����һ��Ԫ�ؼ���ɾ��
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
