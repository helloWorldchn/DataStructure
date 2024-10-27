package tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    
	// 树的先序遍历
	public void preOrderTraversal (TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.print(root.val + " "); //先输出当前节点（初始的时候是root节点）
		preOrderTraversal(root.left); // 如果左子节点不为空，则递归继续前序遍历
		preOrderTraversal(root.right); // 如果右子节点不为空，则递归继续前序遍历
	}
	
	// 树的中序遍历
	public void inOrderTraversa (TreeNode root) {
		if (root == null) {
			return;
		}
		inOrderTraversa(root.left); // 如果当前节点的左子节点不为空，则递归中序遍历
		System.out.print(root.val + " "); // 输出当前节点
		inOrderTraversa(root.right); // 如果当前的右子节点不为空，则递归中序遍历		
	}
	
	// 树的后序遍历
	public void postOrderTraversal (TreeNode root) {
		if (root == null) {
			return;
		}
		postOrderTraversal(root.left); // 如果当前节点的左子节点不为空，则递归后序遍历
		postOrderTraversal(root.right); // 如果当前节点的右子节点不为空，则递归后序遍历
		System.out.print(root.val + " "); // 输出当前节点		
	}

    // 广度优先遍历，即树的层次遍历，借用队列实现
    public void levelOrderTraversal(TreeNode root) {
    	if(root == null) {
    		return;
    	}    	
    	Queue<TreeNode> queue = new LinkedList<TreeNode>(); // 存放每层操作的根节点
        queue.offer(root);        
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) { // 用for循换可以隔离开每一层的遍历
            	TreeNode rootNode = queue.poll(); // 开始操作后将其从队列移除
            	System.out.print(rootNode.val + " ");
                if (rootNode.left != null) {
    	            TreeNode leftNode = rootNode.left; // 左节点存入队列，下一层遍历它就成了新根节点	            
    	            queue.offer(leftNode);
                }
                if (rootNode.right != null) {
                	TreeNode rightNode = rootNode.right; // 右节点存入队列，下一层遍历它就成了新根节点
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
		System.out.print("先序遍历：");
		binaryTree.preOrderTraversal(root);
		System.out.println();
		System.out.print("中序遍历：");
		binaryTree.inOrderTraversa(root);
		System.out.println();
		System.out.print("后序遍历：");
		binaryTree.postOrderTraversal(root);
		System.out.println();
		System.out.print("层次遍历：");
		binaryTree.levelOrderTraversal(root);
		System.out.println();
	}
}
