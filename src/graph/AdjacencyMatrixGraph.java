package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// 邻接矩阵
public class AdjacencyMatrixGraph {
	ArrayList<String> vertexList; //存储顶点集合
	int[][] arcs; // 邻接矩阵
    int vertexNumber; // 图的当前顶点的数目
    int edgeNumber; // 图的当前边的数目
    boolean[] isVisited; // 定义给数组boolean[], 记录某个结点是否被访问
	
    // 初始化
    public AdjacencyMatrixGraph(int maxVertex) {
    	vertexList = new ArrayList<String>(maxVertex);
    	arcs = new int[maxVertex][maxVertex];
    	vertexNumber = 0;
    	edgeNumber = 0;
    	for (int i = 0; i < maxVertex; i++) {
        	for (int j = 0; j < maxVertex; j++) {
    			arcs[i][j] = 0;
    		}
		}
    }
	
    //插入顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex); // 把要插入的值添加到vertexList中即可。
        vertexNumber ++;
        System.out.println(vertex + " has been entered!");
    }
    
    /**
         * 添加边（a->b的边）
     * @param a 
     * @param b
     * @param weight 权重（不带权的图即weight=1）
     */
    public void insertEdge(int a, int b, int weight) {
    	if(a < vertexNumber && b < vertexNumber) {
        	if (arcs[a][b] == 0) {
            	arcs[a][b] = weight; // 将权重添加到arcs[a][b]中即可
            	System.out.println(vertexList.get(a)+"->"+vertexList.get(b)+" connect edge!");
        	}
        	edgeNumber ++;
    	}
    }

    // 得到index顶点的第一个邻接结点的下标
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexNumber; j++) {
            if (arcs[index][j] > 0) { // arcs[index][j]即为第一个邻接点的权重
//            	System.out.println(vertexList.get(j) + " is the first neighbor");
                return j;
            }
        }
        return -1;
    }
    
    //根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor (int a, int b) {
        for (int j = b+1; j < vertexNumber; j++) {
            if (arcs[a][j] > 0) { // arcs[a][j]即为下一个邻接点的权重
//            	System.out.println(vertexList.get(j) + " is the next neighbor");
                return j;
            }
        }     
    	return -1;
    }
    
    
    // 返回结点对应的顶点值 0:"A" 1:"B" 2:"C" 3:"D" 4:"E"
    public String getValueByIndex(int index) {
    	return vertexList.get(index);
    }
    // 获取a->b的权值
    public int getWeight(int a, int b) {
    	return arcs[a][b];
    }

    
    // 深度优先搜索 DFS(deep-first search)
    public void deepFirstSearch(boolean[] isVisited, int index) {
    	System.out.print(vertexList.get(index) + " "); // 首先我们访问该结点,输出
    	isVisited[index] = true; // index已经在上一行被访问过了，所以变为true
    	// 深度优先搜索，重复找第一个邻接节点，直到找不到了为止
    	int w = getFirstNeighbor(index); // 得到index顶点的第一个邻接结点的下标
    	while (w != -1) { // 如果有邻接顶点就循换，直到没有了为止
    		if (!isVisited[w]) { // 如果w没被访问过
    			deepFirstSearch(isVisited, w);
    		}
    		// 如果w结点已经被访问过，说明一轮深度搜索已完成！寻找下一个邻接顶点
    		w = getNextNeighbor(index, w);
    	}
    }
    // 对deepFirstSearch进行一个重载, 遍历我们所有的结点，并进行deepFirstSearch
    public void deepFirstSearch() {
    	isVisited = new boolean[vertexList.size()];
        //遍历所有的结点，进行dfs[回溯]
        for (int i = 0; i < vertexNumber; i++) {
            if (!isVisited[i]) {
            	deepFirstSearch(isVisited, i);
            }
        }
    }
    
    /*
     * 1）A 是第一个遍历的顶点，标记A并且使A进入队列，寻找A的第一个邻接顶点可知没有，故寻找A的下一个邻接顶点，A与B、D都有边，但根据顶点顺序可知下一个邻接顶点为 B，所以标记B并且使B进入队列；
     * 2）此时A作为队列的头，将A出队列，继续以顶点A进行遍历，寻找B以后的邻接顶点可知为D，故标记D，使D进入队列，A所有有关联的顶点皆已经遍历完；
     * 3）此时B作为队列的头，将B出队列，以顶点B进行遍历，寻找B的第一个邻接顶点可知为A，但A已经被遍历，因此寻找下一个邻接顶点为E，所以标记E，使E进入队列，B所有有关联的顶点皆已经遍历完；
     */
    // 广度优先搜索 BFS(breadth-first search)
    public void breadthFirstSearch(boolean[] isVisited, int index) {
    	int u; // u代表队列的头结点对应的下标。
    	int w; // w代表邻接节点
    	Queue<Integer> queue = new ArrayDeque<>();
    	System.out.print(vertexList.get(index) + " "); // 首先我们访问该结点,输出
    	isVisited[index] = true; // index已经在上一行被访问过了，所以变为true
    	queue.add(index); // 使A进入队列
    	
    	// 对队列中的几个元素依次执行广度遍历
    	while (!queue.isEmpty()) {
            u = queue.poll();// 取出队列的头
            w = getFirstNeighbor(u); // 得到第一个邻接点的下标
            while (w != -1) { // 如果有邻接顶点就循换，直到没有了为止
                if (!isVisited[w]) { // 如果w没被访问过
                    //如果第一个邻接点未被访问则访问第一个邻接节点
                    System.out.print(getValueByIndex(w) + " ");
                    isVisited[w] = true;
                    queue.add(w);
                }
        		// 如果w结点已经被访问过，寻找u的下一个邻接顶点。实现广度遍历
        		w = getNextNeighbor(u, w);
            }
    	}	
    }
    public void breadthFirstSearch() {
    	isVisited = new boolean[vertexList.size()];
        //遍历所有的结点，进行dfs[回溯]
        for (int i = 0; i < vertexNumber; i++) {
            if (!isVisited[i]) {
            	breadthFirstSearch(isVisited, i);
            }
        }
    }
    
    
    // 显示图对应的矩阵
    public void display() {
    	for (int i = 0; i < vertexNumber; i++) {
        	for (int j = 0; j < vertexNumber; j++) {
    			System.out.print(arcs[i][j] + " ");
    		}
        	System.out.println();
		}
	}
	public static void main(String[] args) {
		AdjacencyMatrixGraph adjacencyMatrixGraph = new AdjacencyMatrixGraph(6);
		adjacencyMatrixGraph.insertVertex("A");
		adjacencyMatrixGraph.insertVertex("B");
		adjacencyMatrixGraph.insertVertex("C");
		adjacencyMatrixGraph.insertVertex("D");
		adjacencyMatrixGraph.insertVertex("E");
		
		adjacencyMatrixGraph.insertEdge(0, 1, 1);
		adjacencyMatrixGraph.insertEdge(0, 4, 1);
		adjacencyMatrixGraph.insertEdge(1, 2, 1);
		adjacencyMatrixGraph.insertEdge(2, 3, 1);
		adjacencyMatrixGraph.insertEdge(3, 0, 1);
		adjacencyMatrixGraph.insertEdge(3, 1, 1);
		adjacencyMatrixGraph.insertEdge(4, 2, 1);
		
		adjacencyMatrixGraph.display();
//		adjacencyMatrixGraph.getFirstNeighbor(1);
//		adjacencyMatrixGraph.getNextNeighbor(3, 0);
		adjacencyMatrixGraph.deepFirstSearch(); // A B C D E 
		System.out.println();
		adjacencyMatrixGraph.breadthFirstSearch(); // A B E C D
		System.out.println();
		/*   A B C D E
		 * A 0 1 0 0 1 
		 * B 0 0 1 0 0 
		 * C 0 0 0 1 0 
		 * D 1 1 0 0 0 
		 * E 0 0 1 0 0 
		 */
		
	}
}
