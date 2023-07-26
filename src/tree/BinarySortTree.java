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
	
	
    TreeNode root; // �½����ڵ�
    /*
     * ���������Ԫ��ֱ�Ӵ���������
     */
    public void createTree(int[] nums) {
    	root = new TreeNode(nums[0]); // �����һ��Ϊ���ڵ�
        for (int i = 1; i < nums.length; i++) { // �ӵڶ���Ԫ�ؿ�ʼ����
            TreeNode treeNode = new TreeNode();
            treeNode.val = nums[i];            
            TreeNode currentNode = root; // Ҫ����λ�õĽڵ㣬��ʼΪroot���Ӹ��ڵ�����Ѱ��
            TreeNode parentNode = null; // Ҫ����λ�õĸ��ڵ�
            while (currentNode != null) {
            	parentNode = currentNode;
            	if (currentNode.val > nums[i]) { // ��ǰ�ڵ�Ƚ�Ҫ�����ֵ��
            		currentNode = currentNode.left; // ����ڵ��������Ƚ�
            		if (currentNode == null) { // ����ڵ�Ϊ�� ��ֱ�Ӳ��뼴��
            			parentNode.left = treeNode;
            			System.out.println(nums[i] + " has been inserted into the binary tree!");
            			break;
            		}
            	} else if (currentNode.val < nums[i]) { // ��ǰ�ڵ�Ƚ�Ҫ�����ֵС
            		currentNode = currentNode.right; // ����ڵ��������Ƚ�
            		if (currentNode == null) { // ���ҽڵ�Ϊ�� ��ֱ�Ӳ��뼴��
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
     * �ڶ������в���ڵ�
     * Ҫ����ڵ㣬�������ҵ������λ�á�
     * ���ڶ����������������ԣ�������Ľڵ���Ҫ�Ӹ��ڵ㿪ʼ���бȽ�
     * С�ڸ��ڵ�������ڵ��������Ƚϣ���֮�����������Ƚϣ�ֱ��������Ϊ�ջ�������Ϊ��
     * ����뵽��ӦΪ�յ�λ��
     * �ڱȽϵĹ�����Ҫע�Ᵽ�游�ڵ����Ϣ���������λ���Ǹ��ڵ������������������
     * @param val
     */
    public void insert(int val) {
    	TreeNode treeNode = new TreeNode();
    	treeNode.val = val;
    	if (root == null) { // ������ڵ�Ϊ�գ�˵���Ǹ�����
    		root = treeNode;
    		System.out.println(val + " has been inserted into the binary tree!");
    	} else {
    		TreeNode currentNode = root; // Ҫ����λ�õĽڵ㣬��ʼΪroot���Ӹ��ڵ�����Ѱ��
			TreeNode parentNode = null; // Ҫ����λ�õĸ��ڵ�
    		while (currentNode != null) {
    			parentNode = currentNode;
    			if (currentNode.val > val) { // ��ǰ�ڵ�Ƚ�Ҫ�����ֵ��
    				currentNode = currentNode.left; // ����ڵ��������Ƚ�
    				if (currentNode == null) { // ����ڵ�Ϊ�� ��ֱ�Ӳ��뼴��
    					parentNode.left = treeNode;
    					System.out.println(val + " has been inserted into the binary tree!");
    					break;
    				}	
    			} else if (currentNode.val < val) { // ��ǰ�ڵ�Ƚ�Ҫ�����ֵС
    				currentNode = currentNode.right; // ����ڵ��������Ƚ�
    				if (currentNode == null) { // ���ҽڵ�Ϊ�� ��ֱ�Ӳ��뼴��
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
	
    // ������val��ֵ��ȵĽڵ�
    public void get(int val) {
    	TreeNode currentNode = root;
    	TreeNode findTreeNode = new TreeNode();
    	while (currentNode != null) {
			if (currentNode.val > val) { // ��ǰ�ڵ�Ƚ�Ҫ�����ֵ��
				currentNode = currentNode.left; // ȥ���ڵ��������м���Ѱ��
			} else if (currentNode.val < val) { // ��ǰ�ڵ�Ƚ�Ҫ�����ֵС
				currentNode = currentNode.right; // ȥ���ڵ��������м���Ѱ��
			} else if (currentNode.val == val)  {
				findTreeNode = currentNode; //��ȣ���currentNode��ҪѰ�ҵĽڵ㡣
				System.out.println(findTreeNode.val + " has been found!");
				return;
			}
    	}
    	System.out.println("Error! " + val + " does not exist!");
    }
    
    
    /**
     * ɾ����valֵ��ͬ�Ľڵ�
     * ɾ�����������
     * 1 �ýڵ���Ҷ�ӽڵ�
     * 2 �ýڵ���һ��Ҷ�ӽڵ�
     * 3 �ýڵ�������Ҷ�ӽڵ�
     * @param val
     */
    public void remove(int val) {
    	TreeNode currentNode = root;
    	TreeNode parentNode = null; // Ҫ����λ�õĸ��ڵ�
		boolean isLeftChild = false; // �ж��Ǹ��ڵ������������������
		TreeNode findTreeNode = new TreeNode();
		//Ѱ��ɾ���Ľڵ�
    	while (currentNode != null && currentNode.val != val) {
    		parentNode = currentNode;
			if (currentNode.val > val) { // ��ǰ�ڵ�Ƚ�Ҫ�����ֵ��
				currentNode = currentNode.left; // ȥ���ڵ��������м���Ѱ��
				isLeftChild = true;
			} else if (currentNode.val < val) { // ��ǰ�ڵ�Ƚ�Ҫ�����ֵС
				currentNode = currentNode.right; // ȥ���ڵ��������м���Ѱ��
				isLeftChild = false;
			}
    	}
    	findTreeNode = currentNode;
    	
//    	System.out.println(currentNode.val + " has been find!");
    	// 1 ����ýڵ���Ҷ�ӽڵ㣬ֻ�ý���Ҷ�ӽڵ�ɾ�����ɡ�
    	if (currentNode.left == null && currentNode.right == null) {
    		if (currentNode == root) { // �Ǹ��ڵ㣬������ֻ��һ���ڵ�
    			currentNode = null;
    		} else if (isLeftChild) { // ���丸�ڵ���������ڵ�
    			parentNode.left = null;
    		} else if (!isLeftChild) { // ���丸�ڵ���������ڵ�
    			parentNode.right = null;
    		}
    	} else if(currentNode.left != null && currentNode.right == null) {
    	// 2 �ýڵ���һ��Ҷ�ӽڵ㣬Ҷ�ӽ��Ϊ��ڵ�
    		if (currentNode == root) { // �Ǹ��ڵ㣬������ֻ��һ���ڵ�
    			currentNode = currentNode.left;
    		} else if (isLeftChild) { // ���丸�ڵ���������ڵ�
    			parentNode.left = currentNode.left;
    		} else if (!isLeftChild) { // ���丸�ڵ���������ڵ�
    			parentNode.right = currentNode.left;
    		}
    	} else if(currentNode.left == null && currentNode.right != null) {	
    	// 2 �ýڵ���һ��Ҷ�ӽڵ㣬Ҷ�ӽ��Ϊ�ҽڵ�
    		if (currentNode == root) { // �Ǹ��ڵ㣬������ֻ��һ���ڵ�
    			currentNode = currentNode.right;
    		} else if (isLeftChild) { // ���丸�ڵ���������ڵ�
    			parentNode.left = currentNode.right;
    		} else if (!isLeftChild) { // ���丸�ڵ���������ڵ�
    			parentNode.right = currentNode.right;
    		}
    	} else {
    	// 3 �ýڵ�������Ҷ�ӽڵ�
    	//ɾ���ڵ��������������ֵ��㣨ǰ���ڵ㣩���棬����������Сֵ�ڵ㣨��̽ڵ㣩����
    		// Ѱ����������Сֵ�ڵ㣬����̽ڵ�
    		TreeNode successorNode = currentNode; // ��̽ڵ�
    		TreeNode successorParent  = currentNode; // ��̽ڵ�ĸ��ڵ�
    		TreeNode rightCurrentNode  = currentNode.right; // �Ƚ��뵱ǰ�ڵ��������
    		while (rightCurrentNode != null) {
    			successorParent = successorNode;
    			successorNode = rightCurrentNode;
    			rightCurrentNode = rightCurrentNode.left; // Ѱ������������������Ѱ����Сֵ��
    		}
    		//successorNode����������Ϊ�丸�ڵ��������
    		// Ȼ��������������ָ��currentNode��������
    		if(successorNode != currentNode.right) {
    			successorParent.left = successorNode.right;
    			successorNode.right = currentNode.right;
    		}
    		
    		// ��ʼɾ��
    		if (currentNode == root) { // �Ǹ��ڵ㣬������ֻ��һ���ڵ�
    			currentNode = successorNode;
    		} else if (isLeftChild) { // ���丸�ڵ���������ڵ�
    			parentNode.left = successorNode;
    		} else if (!isLeftChild) { // ���丸�ڵ���������ڵ�
    			parentNode.right = successorNode;
    		}
    		successorNode.left = currentNode.left; // ������ڵ�
    	}
    	System.out.println(findTreeNode.val + " has been removed!");
    	
    }
    
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
		BinarySortTree binarySortTree = new BinarySortTree();
		
		binarySortTree.createTree(new int[]{7, 1, 5, 9, 3, 0, 2, 6, 8});

		binarySortTree.insert(4);
		
		binarySortTree.get(5);
		
		System.out.print("���������");
		binarySortTree.preOrderTraversal(binarySortTree.root);
		System.out.println();
		System.out.print("���������");
		binarySortTree.inOrderTraversa(binarySortTree.root);
		System.out.println();
		System.out.print("���������");
		binarySortTree.postOrderTraversal(binarySortTree.root);
		System.out.println();
		System.out.print("��α�����");
		binarySortTree.levelOrderTraversal(binarySortTree.root);
		System.out.println();
		
		binarySortTree.remove(3);
		
		System.out.print("���������");
		binarySortTree.preOrderTraversal(binarySortTree.root);
		System.out.println();
		System.out.print("���������");
		binarySortTree.inOrderTraversa(binarySortTree.root);
		System.out.println();
		System.out.print("���������");
		binarySortTree.postOrderTraversal(binarySortTree.root);
		System.out.println();
		System.out.print("��α�����");
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
