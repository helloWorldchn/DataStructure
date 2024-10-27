package tree;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {
	TreeNode root; // 新建根节点
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
	 * 获取树的高度
	 */
	private int height(TreeNode tree) {
	    if (tree != null)
	        return tree.height;

	    return 0;
	}
	
	// 左旋转
	public TreeNode leftRotation(TreeNode k2) {
		TreeNode k1;

	    k1 = k2.left;
	    k2.left = k1.right;
	    k1.right = k2;

	    k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
	    k1.height = Math.max(height(k1.left), k2.height) + 1;

	    return k1;
	}
	// 右旋转
	public TreeNode rightRotation(TreeNode k1) {
		TreeNode k2;

	    k2 = k1.right;
	    k1.right = k2.left;
	    k2.left = k1;

	    k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
	    k2.height = Math.max(height(k2.right), k1.height) + 1;

	    return k2;
	}
	
	// LL: 左左对应的情况(左单旋转)。
	public TreeNode leftLeftRotation(TreeNode k1) {
	    return leftRotation(k1);
	}
	// RR: 右右对应的情况(右单旋转)。
	public TreeNode rightRightRotation(TreeNode k1) {
	    return rightRotation(k1);
	}
	// LR: 左右对应的情况(左双旋转)。
	public TreeNode leftRightRotation(TreeNode k1) {
		k1.left = rightRotation(k1.left);
	    return leftRotation(k1);
	}
	// RL: 右左对应的情况(右双旋转)。
	public TreeNode rightLeftRotation(TreeNode k1) {
		k1.right = leftRotation(k1.right);
	    return rightRotation(k1);
	}
	
	public void insert(int val) {
	    root = insert(root, val);
	}
	// 将结点插入到AVL树中，并返回根节点
	private TreeNode insert(TreeNode tree, int val) {
		if (tree == null) {
	        // 新建节点
	        tree = new TreeNode(val);
	        if (tree==null) {
	            System.out.println("ERROR: create avltree node failed!");
	            return null;
	        }
		} else {
	
			if (val< tree.val) {    // 应该将key插入到"tree的左子树"的情况
	        tree.left = insert(tree.left, val);
	        // 插入节点后，若AVL树失去平衡，则进行相应的调节。
	        if (height(tree.left) - height(tree.right) == 2) {
	            if (val < tree.left.val)
	                tree = leftLeftRotation(tree);
	            else
	                tree = leftRightRotation(tree);
	        }
	        } else if (val > tree.val) {    // 应该将key插入到"tree的右子树"的情况
	            tree.right = insert(tree.right, val);
	            // 插入节点后，若AVL树失去平衡，则进行相应的调节。
	            if (height(tree.right) - height(tree.left) == 2) {
	                if (val > tree.right.val)
	                    tree = rightRightRotation(tree);
	                else
	                    tree = rightLeftRotation(tree);
	            }
	        } else {    // val == tree.val
	            System.out.println("添加失败: 不允许添加相同的节点！");
	        }
	    }
	    tree.height = Math.max( height(tree.left), height(tree.right)) + 1;
	    return tree;
	}
	
    // 查找与val数值相等的节点
    public TreeNode get(int val) {
    	return get(root, val);
    }
    public TreeNode get(TreeNode currentNode, int val) {
    	TreeNode findTreeNode = new TreeNode();
    	while (currentNode != null) {
			if (currentNode.val > val) { // 当前节点比将要插入的值大
				currentNode = currentNode.left; // 去根节点左子树中继续寻找
			} else if (currentNode.val < val) { // 当前节点比将要插入的值小
				currentNode = currentNode.right; // 去根节点右子树中继续寻找
			} else if (currentNode.val == val)  {
				findTreeNode = currentNode; //相等，则currentNode是要寻找的节点。
				System.out.println(findTreeNode.val + " has been found!");
				return findTreeNode;
			}
    	}
    	System.out.println("Error! " + val + " does not exist!");
    	return null;
    }

    // 删除结点(z)，返回根节点 
    public void remove(int val) {
	    TreeNode z; 
	    if ((z = get(root, val)) != null)
	        root = remove(root, z);
    }
    private TreeNode remove(TreeNode tree, TreeNode z) {
        // 根为空 或者 没有要删除的节点，直接返回null。
        if (tree==null || z==null)
            return null;

        if (z.val < tree.val) {        // 待删除的节点在"tree的左子树"中
            tree.left = remove(tree.left, z);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.right) - height(tree.left) == 2) {
                TreeNode r =  tree.right;
                if (height(r.left) > height(r.right))
                    tree = rightLeftRotation(tree);
                else
                    tree = rightRightRotation(tree);
            }
        } else if (z.val > tree.val) {    // 待删除的节点在"tree的右子树"中
            tree.right = remove(tree.right, z);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.left) - height(tree.right) == 2) {
                TreeNode l =  tree.left;
                if (height(l.right) > height(l.left))
                    tree = leftRightRotation(tree);
                else
                    tree = leftLeftRotation(tree);
            }
        } else {    // tree是对应要删除的节点。
            // tree的左右孩子都非空
            if ((tree.left!=null) && (tree.right!=null)) {
                if (height(tree.left) > height(tree.right)) {
                    // 如果tree的左子树比右子树高；
                    // 则(01)找出tree的左子树中的最大节点(直接前驱)
                    //   (02)将该最大节点的值赋值给tree。
                    //   (03)删除该最大节点。
                    // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                    TreeNode max = getMaxNode(tree.left);
                    tree.val = max.val;
                    tree.left = remove(tree.left, max);
                } else {
                    // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出tree的右子树中的最小节点(直接后继)
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
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

    // 查找最小结点: 返回tree为根结点的AVL树的最小结点。
    private TreeNode getMinNode(TreeNode tree) {
        if (tree == null)
            return null;
        while(tree.left != null)
            tree = tree.left;
        return tree;
    }
 
    // 查找最大结点: 返回tree为根结点的AVL树的最大结点。
    private TreeNode getMaxNode(TreeNode tree) {
        if (tree == null)
            return null;

        while(tree.right != null)
            tree = tree.right;
        return tree;
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
