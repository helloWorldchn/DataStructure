package tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    
	// �����������
	public void preOrderTraversal (TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.print(root.val + " "); //�������ǰ�ڵ㣨��ʼ��ʱ����root�ڵ㣩
		preOrderTraversal(root.left); // ������ӽڵ㲻Ϊ�գ���ݹ����ǰ�����
		preOrderTraversal(root.right); // ������ӽڵ㲻Ϊ�գ���ݹ����ǰ�����
	}
	
	// �����������
	public void inOrderTraversa (TreeNode root) {
		if (root == null) {
			return;
		}
		inOrderTraversa(root.left); // �����ǰ�ڵ�����ӽڵ㲻Ϊ�գ���ݹ��������
		System.out.print(root.val + " "); // �����ǰ�ڵ�
		inOrderTraversa(root.right); // �����ǰ�����ӽڵ㲻Ϊ�գ���ݹ��������		
	}
	
	// ���ĺ������
	public void postOrderTraversal (TreeNode root) {
		if (root == null) {
			return;
		}
		postOrderTraversal(root.left); // �����ǰ�ڵ�����ӽڵ㲻Ϊ�գ���ݹ�������
		postOrderTraversal(root.right); // �����ǰ�ڵ�����ӽڵ㲻Ϊ�գ���ݹ�������
		System.out.print(root.val + " "); // �����ǰ�ڵ�		
	}

    // ������ȱ����������Ĳ�α��������ö���ʵ��
    public void levelOrderTraversal(TreeNode root) {
    	if(root == null) {
    		return;
    	}    	
    	Queue<TreeNode> queue = new LinkedList<TreeNode>(); // ���ÿ������ĸ��ڵ�
        queue.offer(root);        
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) { // ��forѭ�����Ը��뿪ÿһ��ı���
            	TreeNode rootNode = queue.poll(); // ��ʼ��������Ӷ����Ƴ�
            	System.out.print(rootNode.val + " ");
                if (rootNode.left != null) {
    	            TreeNode leftNode = rootNode.left; // ��ڵ������У���һ��������ͳ����¸��ڵ�	            
    	            queue.offer(leftNode);
                }
                if (rootNode.right != null) {
                	TreeNode rightNode = rootNode.right; // �ҽڵ������У���һ��������ͳ����¸��ڵ�
                	queue.offer(rightNode);
                }
            }
        }
    }
	
	public static void main(String[] args) {
		TreeNode root;
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node2 = new TreeNode(2, node4, node5);
		TreeNode node3 = new TreeNode(3, node6, node7);
		TreeNode node1 = new TreeNode(1, node2, node3);
		root = node1;

		BinaryTree binaryTree = new BinaryTree();
		System.out.print("���������");
		binaryTree.preOrderTraversal(root);
		System.out.println();
		System.out.print("���������");
		binaryTree.inOrderTraversa(root);
		System.out.println();
		System.out.print("���������");
		binaryTree.postOrderTraversal(root);
		System.out.println();
		System.out.print("��α�����");
		binaryTree.levelOrderTraversal(root);
		System.out.println();
	}
}
