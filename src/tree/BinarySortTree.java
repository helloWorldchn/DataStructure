package tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySortTree {
	
	// Definition for a binary tree node.
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) {
			this.val = val;
		}
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
	
	
    TreeNode root; // 新建根节点
    /*
     * 根据数组的元素直接创建二叉树
     */
    public void createTree(int[] nums) {
    	root = new TreeNode(nums[0]); // 数组第一个为根节点
        for (int i = 1; i < nums.length; i++) { // 从第二个元素开始迭代
            TreeNode treeNode = new TreeNode();
            treeNode.val = nums[i];            
            TreeNode currentNode = root; // 要插入位置的节点，初始为root，从根节点依次寻找
            TreeNode parentNode = null; // 要插入位置的父节点
            while (currentNode != null) {
            	parentNode = currentNode;
            	if (currentNode.val > nums[i]) { // 当前节点比将要插入的值大
            		currentNode = currentNode.left; // 与根节点左子树比较
            		if (currentNode == null) { // 若左节点为空 则直接插入即可
            			parentNode.left = treeNode;
            			System.out.println(nums[i] + " has been inserted into the binary tree!");
            			break;
            		}
            	} else if (currentNode.val < nums[i]) { // 当前节点比将要插入的值小
            		currentNode = currentNode.right; // 与根节点右子树比较
            		if (currentNode == null) { // 若右节点为空 则直接插入即可
            			parentNode.right = treeNode;
            			System.out.println(nums[i] + " has been inserted into the binary tree!");
            			break;
                    }
            	} else {
            		System.out.println(nums[i]+" repeats!");
            		break;
                }
            }            
        }
    }
       
    /**
     * 在二叉树中插入节点
     * 要插入节点，必须先找到插入的位置。
     * 由于二叉搜索树的特殊性，待插入的节点需要从根节点开始进行比较
     * 小于根节点则与根节点左子树比较，反之则与右子树比较，直到左子树为空或右子树为空
     * 则插入到相应为空的位置
     * 在比较的过程中要注意保存父节点的信息及待插入的位置是父节点的左子树还是右子树
     * @param val
     */
    public void insert(int val) {
    	TreeNode treeNode = new TreeNode();
    	treeNode.val = val;
    	if (root == null) { // 如果根节点为空，说明是个空树
    		root = treeNode;
    		System.out.println(val + " has been inserted into the binary tree!");
    	} else {
    		TreeNode currentNode = root; // 要插入位置的节点，初始为root，从根节点依次寻找
			TreeNode parentNode = null; // 要插入位置的父节点
    		while (currentNode != null) {
    			parentNode = currentNode;
    			if (currentNode.val > val) { // 当前节点比将要插入的值大
    				currentNode = currentNode.left; // 与根节点左子树比较
    				if (currentNode == null) { // 若左节点为空 则直接插入即可
    					parentNode.left = treeNode;
    					System.out.println(val + " has been inserted into the binary tree!");
    					break;
    				}	
    			} else if (currentNode.val < val) { // 当前节点比将要插入的值小
    				currentNode = currentNode.right; // 与根节点右子树比较
    				if (currentNode == null) { // 若右节点为空 则直接插入即可
    					parentNode.right = treeNode;
    					System.out.println(val + " has been inserted into the binary tree!");
    					break;
    				}
    			} else {
    				System.out.println(val + " repeats!");
    				break;
    			}
    		}
    	}
    }
	
    // 查找与val数值相等的节点
    public void get(int val) {
    	TreeNode currentNode = root;
    	TreeNode findTreeNode = new TreeNode();
    	while (currentNode != null) {
			if (currentNode.val > val) { // 当前节点比将要插入的值大
				currentNode = currentNode.left; // 去根节点左子树中继续寻找
			} else if (currentNode.val < val) { // 当前节点比将要插入的值小
				currentNode = currentNode.right; // 去根节点右子树中继续寻找
			} else if (currentNode.val == val)  {
				findTreeNode = currentNode; //相等，则currentNode是要寻找的节点。
				System.out.println(findTreeNode.val + " has been found!");
				return;
			}
    	}
    	System.out.println("Error! " + val + " does not exist!");
    }
    
    
    /**
     * 删除与val值相同的节点
     * 删除共三种情况
     * 1 该节点是叶子节点
     * 2 该节点有一个叶子节点
     * 3 该节点有两个叶子节点
     * @param val
     */
    public void remove(int val) {
    	TreeNode currentNode = root;
    	TreeNode parentNode = null; // 要插入位置的父节点
		boolean isLeftChild = false; // 判断是父节点的左子树还是右子树
		TreeNode findTreeNode = new TreeNode();
		//寻找删除的节点
    	while (currentNode != null && currentNode.val != val) {
    		parentNode = currentNode;
			if (currentNode.val > val) { // 当前节点比将要插入的值大
				currentNode = currentNode.left; // 去根节点左子树中继续寻找
				isLeftChild = true;
			} else if (currentNode.val < val) { // 当前节点比将要插入的值小
				currentNode = currentNode.right; // 去根节点右子树中继续寻找
				isLeftChild = false;
			}
    	}
    	findTreeNode = currentNode;
    	
//    	System.out.println(currentNode.val + " has been find!");
    	// 1 如果该节点是叶子节点，只用将其叶子节点删掉即可。
    	if (currentNode.left == null && currentNode.right == null) {
    		if (currentNode == root) { // 是根节点，即该树只有一个节点
    			currentNode = null;
    		} else if (isLeftChild) { // 是其父节点的左子树节点
    			parentNode.left = null;
    		} else if (!isLeftChild) { // 是其父节点的右子树节点
    			parentNode.right = null;
    		}
    	} else if(currentNode.left != null && currentNode.right == null) {
    	// 2 该节点有一个叶子节点，叶子结点为左节点
    		if (currentNode == root) { // 是根节点，即该树只有一个节点
    			currentNode = currentNode.left;
    		} else if (isLeftChild) { // 是其父节点的左子树节点
    			parentNode.left = currentNode.left;
    		} else if (!isLeftChild) { // 是其父节点的右子树节点
    			parentNode.right = currentNode.left;
    		}
    	} else if(currentNode.left == null && currentNode.right != null) {	
    	// 2 该节点有一个叶子节点，叶子结点为右节点
    		if (currentNode == root) { // 是根节点，即该树只有一个节点
    			currentNode = currentNode.right;
    		} else if (isLeftChild) { // 是其父节点的左子树节点
    			parentNode.left = currentNode.right;
    		} else if (!isLeftChild) { // 是其父节点的右子树节点
    			parentNode.right = currentNode.right;
    		}
    	} else {
    	// 3 该节点有两个叶子节点
    	//删除节点用左子树中最大值结点（前驱节点）代替，或右子树最小值节点（后继节点）代替
    		// 寻找右子树最小值节点，即后继节点
    		TreeNode successorNode = currentNode; // 后继节点
    		TreeNode successorParent  = currentNode; // 后继节点的父节点
    		TreeNode rightCurrentNode  = currentNode.right; // 先进入当前节点的右子树
    		while (rightCurrentNode != null) {
    			successorParent = successorNode;
    			successorNode = rightCurrentNode;
    			rightCurrentNode = rightCurrentNode.left; // 寻找右子树的左子树（寻找最小值）
    		}
    		//successorNode的左子树成为其父节点的右子树
    		// 然后其右子树更新指向currentNode的右子树
    		if(successorNode != currentNode.right) {
    			successorParent.left = successorNode.right;
    			successorNode.right = currentNode.right;
    		}
    		
    		// 开始删除
    		if (currentNode == root) { // 是根节点，即该树只有一个节点
    			currentNode = successorNode;
    		} else if (isLeftChild) { // 是其父节点的左子树节点
    			parentNode.left = successorNode;
    		} else if (!isLeftChild) { // 是其父节点的右子树节点
    			parentNode.right = successorNode;
    		}
    		successorNode.left = currentNode.left; // 更新左节点
    	}
    	System.out.println(findTreeNode.val + " has been removed!");
    	
    }
    
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
		BinarySortTree binarySortTree = new BinarySortTree();
		
		binarySortTree.createTree(new int[]{7, 1, 5, 9, 3, 0, 2, 6, 8});

		binarySortTree.insert(4);
		
		binarySortTree.get(5);
		
		System.out.print("先序遍历：");
		binarySortTree.preOrderTraversal(binarySortTree.root);
		System.out.println();
		System.out.print("中序遍历：");
		binarySortTree.inOrderTraversa(binarySortTree.root);
		System.out.println();
		System.out.print("后序遍历：");
		binarySortTree.postOrderTraversal(binarySortTree.root);
		System.out.println();
		System.out.print("层次遍历：");
		binarySortTree.levelOrderTraversal(binarySortTree.root);
		System.out.println();
		
		binarySortTree.remove(3);
		
		System.out.print("先序遍历：");
		binarySortTree.preOrderTraversal(binarySortTree.root);
		System.out.println();
		System.out.print("中序遍历：");
		binarySortTree.inOrderTraversa(binarySortTree.root);
		System.out.println();
		System.out.print("后序遍历：");
		binarySortTree.postOrderTraversal(binarySortTree.root);
		System.out.println();
		System.out.print("层次遍历：");
		binarySortTree.levelOrderTraversal(binarySortTree.root);
		System.out.println();
		
		/*
		 *       7
		 *    /     \
		 *   1       9
		 *  / \     /
		 * 0   5   8
		 *    / \
		 *   3   6
		 *  / \
		 * 2   4
		 * 
		 */

	}
}
