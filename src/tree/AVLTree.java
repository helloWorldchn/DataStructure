package tree;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {
	TreeNode root; // �½����ڵ�
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		int height;
		TreeNode(int val) {
			this.val = val;
		}
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	
	/*
	 * ��ȡ���ĸ߶�
	 */
	private int height(TreeNode tree) {
	    if (tree != null)
	        return tree.height;

	    return 0;
	}
	
	// ����ת
	public TreeNode leftRotation(TreeNode k2) {
		TreeNode k1;

	    k1 = k2.left;
	    k2.left = k1.right;
	    k1.right = k2;

	    k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
	    k1.height = Math.max(height(k1.left), k2.height) + 1;

	    return k1;
	}
	// ����ת
	public TreeNode rightRotation(TreeNode k1) {
		TreeNode k2;

	    k2 = k1.right;
	    k1.right = k2.left;
	    k2.left = k1;

	    k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
	    k2.height = Math.max(height(k2.right), k1.height) + 1;

	    return k2;
	}
	
	// LL: �����Ӧ�����(����ת)��
	public TreeNode leftLeftRotation(TreeNode k1) {
	    return leftRotation(k1);
	}
	// RR: ���Ҷ�Ӧ�����(�ҵ���ת)��
	public TreeNode rightRightRotation(TreeNode k1) {
	    return rightRotation(k1);
	}
	// LR: ���Ҷ�Ӧ�����(��˫��ת)��
	public TreeNode leftRightRotation(TreeNode k1) {
		k1.left = rightRotation(k1.left);
	    return leftRotation(k1);
	}
	// RL: �����Ӧ�����(��˫��ת)��
	public TreeNode rightLeftRotation(TreeNode k1) {
		k1.right = leftRotation(k1.right);
	    return rightRotation(k1);
	}
	
	public void insert(int val) {
	    root = insert(root, val);
	}
	// �������뵽AVL���У������ظ��ڵ�
	private TreeNode insert(TreeNode tree, int val) {
		if (tree == null) {
	        // �½��ڵ�
	        tree = new TreeNode(val);
	        if (tree==null) {
	            System.out.println("ERROR: create avltree node failed!");
	            return null;
	        }
		} else {
	
			if (val< tree.val) {    // Ӧ�ý�key���뵽"tree��������"�����
	        tree.left = insert(tree.left, val);
	        // ����ڵ����AVL��ʧȥƽ�⣬�������Ӧ�ĵ��ڡ�
	        if (height(tree.left) - height(tree.right) == 2) {
	            if (val < tree.left.val)
	                tree = leftLeftRotation(tree);
	            else
	                tree = leftRightRotation(tree);
	        }
	        } else if (val > tree.val) {    // Ӧ�ý�key���뵽"tree��������"�����
	            tree.right = insert(tree.right, val);
	            // ����ڵ����AVL��ʧȥƽ�⣬�������Ӧ�ĵ��ڡ�
	            if (height(tree.right) - height(tree.left) == 2) {
	                if (val > tree.right.val)
	                    tree = rightRightRotation(tree);
	                else
	                    tree = rightLeftRotation(tree);
	            }
	        } else {    // val == tree.val
	            System.out.println("���ʧ��: �����������ͬ�Ľڵ㣡");
	        }
	    }
	    tree.height = Math.max( height(tree.left), height(tree.right)) + 1;
	    return tree;
	}
	
    // ������val��ֵ��ȵĽڵ�
    public TreeNode get(int val) {
    	return get(root, val);
    }
    public TreeNode get(TreeNode currentNode, int val) {
    	TreeNode findTreeNode = new TreeNode();
    	while (currentNode != null) {
			if (currentNode.val > val) { // ��ǰ�ڵ�Ƚ�Ҫ�����ֵ��
				currentNode = currentNode.left; // ȥ���ڵ��������м���Ѱ��
			} else if (currentNode.val < val) { // ��ǰ�ڵ�Ƚ�Ҫ�����ֵС
				currentNode = currentNode.right; // ȥ���ڵ��������м���Ѱ��
			} else if (currentNode.val == val)  {
				findTreeNode = currentNode; //��ȣ���currentNode��ҪѰ�ҵĽڵ㡣
				System.out.println(findTreeNode.val + " has been found!");
				return findTreeNode;
			}
    	}
    	System.out.println("Error! " + val + " does not exist!");
    	return null;
    }

    // ɾ�����(z)�����ظ��ڵ� 
    public void remove(int val) {
	    TreeNode z; 
	    if ((z = get(root, val)) != null)
	        root = remove(root, z);
    }
    private TreeNode remove(TreeNode tree, TreeNode z) {
        // ��Ϊ�� ���� û��Ҫɾ���Ľڵ㣬ֱ�ӷ���null��
        if (tree==null || z==null)
            return null;

        if (z.val < tree.val) {        // ��ɾ���Ľڵ���"tree��������"��
            tree.left = remove(tree.left, z);
            // ɾ���ڵ����AVL��ʧȥƽ�⣬�������Ӧ�ĵ��ڡ�
            if (height(tree.right) - height(tree.left) == 2) {
                TreeNode r =  tree.right;
                if (height(r.left) > height(r.right))
                    tree = rightLeftRotation(tree);
                else
                    tree = rightRightRotation(tree);
            }
        } else if (z.val > tree.val) {    // ��ɾ���Ľڵ���"tree��������"��
            tree.right = remove(tree.right, z);
            // ɾ���ڵ����AVL��ʧȥƽ�⣬�������Ӧ�ĵ��ڡ�
            if (height(tree.left) - height(tree.right) == 2) {
                TreeNode l =  tree.left;
                if (height(l.right) > height(l.left))
                    tree = leftRightRotation(tree);
                else
                    tree = leftLeftRotation(tree);
            }
        } else {    // tree�Ƕ�ӦҪɾ���Ľڵ㡣
            // tree�����Һ��Ӷ��ǿ�
            if ((tree.left!=null) && (tree.right!=null)) {
                if (height(tree.left) > height(tree.right)) {
                    // ���tree�����������������ߣ�
                    // ��(01)�ҳ�tree���������е����ڵ�(ֱ��ǰ��)
                    //   (02)�������ڵ��ֵ��ֵ��tree��
                    //   (03)ɾ�������ڵ㡣
                    // ����������"tree�������������ڵ�"��"tree"������
                    TreeNode max = getMaxNode(tree.left);
                    tree.val = max.val;
                    tree.left = remove(tree.left, max);
                } else {
                    // ���tree��������������������(��������ȣ�������������������1)
                    // ��(01)�ҳ�tree���������е���С�ڵ�(ֱ�Ӻ��)
                    //   (02)������С�ڵ��ֵ��ֵ��tree��
                    //   (03)ɾ������С�ڵ㡣
                    // ����������"tree������������С�ڵ�"��"tree"������
                    TreeNode min = getMinNode(tree.right);
                    tree.val = min.val;
                    tree.right = remove(tree.right, min);
                }
            } else {
                TreeNode tmp = tree;
                tree = (tree.left!=null) ? tree.left : tree.right;
                tmp = null;
            }
        }
        return tree;
    }

    // ������С���: ����treeΪ������AVL������С��㡣
    private TreeNode getMinNode(TreeNode tree) {
        if (tree == null)
            return null;
        while(tree.left != null)
            tree = tree.left;
        return tree;
    }
 
    // ���������: ����treeΪ������AVL��������㡣
    private TreeNode getMaxNode(TreeNode tree) {
        if (tree == null)
            return null;

        while(tree.right != null)
            tree = tree.right;
        return tree;
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
		AVLTree avlTree = new AVLTree();
		int arr[]= {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};
		for (int i = 0; i < arr.length; i++) {
			avlTree.insert(arr[i]);
		}
		avlTree.preOrderTraversal(avlTree.root);
		System.out.println();
		avlTree.inOrderTraversa(avlTree.root);
		System.out.println();
		avlTree.postOrderTraversal(avlTree.root);
		System.out.println();
		avlTree.levelOrderTraversal(avlTree.root);
		System.out.println();
		avlTree.get(3);
		System.out.println();
		avlTree.remove(1);
		System.out.println();
		avlTree.inOrderTraversa(avlTree.root);
		
    }
}
