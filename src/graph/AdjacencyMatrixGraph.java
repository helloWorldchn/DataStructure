package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

// 邻接矩阵
public class AdjacencyMatrixGraph {
	ArrayList<String> vertexList; //存储顶点集合
	int[][] arcs; // 邻接矩阵
    int vertexNumber; // 图的当前顶点的数目
    int edgeNumber; // 图的当前边的数目
	
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

    
    /**
     *  Deep First Search Algorithm: 深度优先搜索算法(dfs)
     *  @return: java.util.List<java.lang.String>
     */
    public List<Object> deepFirstSearch() {
        return deepFirstSearch(0);
    }
    public List<Object> deepFirstSearch(int startIndex) {
    	List<Object> dfsList = new ArrayList<Object>();
        boolean[] isVisited = new boolean[vertexList.size()]; // 定义给数组boolean[], 记录某个结点是否被访问
        //遍历所有的结点，进行dfs[回溯]
        for (int i = startIndex; i < vertexNumber+startIndex; i++) {
        	int index = i%vertexNumber;
            if (!isVisited[index]) {
            	deepFirstSearch(isVisited, index ,dfsList);
            }
        }
        return dfsList;
    }
    public void deepFirstSearch(boolean[] isVisited, int index, List<Object> dfsList) {
    	dfsList.add(vertexList.get(index)); // 首先我们访问该结点,输出
//    	System.out.print(vertexList.get(index) + " "); // 首先我们访问该结点,输出
    	isVisited[index] = true; // index已经在上一行被访问过了，所以变为true
    	// 深度优先搜索，重复找第一个邻接节点，直到找不到了为止
    	int w = getFirstNeighbor(index); // 得到index顶点的第一个邻接结点的下标
    	while (w != -1) { // 如果有邻接顶点就循换，直到没有了为止
    		if (!isVisited[w]) { // 如果w没被访问过
    			deepFirstSearch(isVisited, w, dfsList);
    		}
    		// 如果w结点已经被访问过，说明一轮深度搜索已完成！寻找下一个邻接顶点
    		w = getNextNeighbor(index, w);
    	}
    }
    

    /**
     *  Breadth First Search Algorithm: 广度优先搜索算法(bfs)
     *  @return: java.util.List<java.lang.Object>
     */
    public List<Object> breadthFirstSearch() {
        return breadthFirstSearch(0);
    }
    public List<Object> breadthFirstSearch(int startIndex) {
    	List<Object> bfsList = new ArrayList<Object>();
        boolean[] isVisited = new boolean[vertexList.size()]; // 定义给数组boolean[], 记录某个结点是否被访问
    	isVisited = new boolean[vertexList.size()];
        //遍历所有的结点，依次进行bfs
        for (int i = startIndex; i < vertexNumber+startIndex; i++) {
        	int index = i%vertexNumber;
            if (!isVisited[index]) {
            	breadthFirstSearch(isVisited, index, bfsList);
            }
        }
        return bfsList;
    }
    public void breadthFirstSearch(boolean[] isVisited, int index, List<Object> bfsList) {
    	int u; // u代表队列的头结点对应的下标。
    	int w; // w代表邻接节点
    	Queue<Integer> queue = new ArrayDeque<>(); // 辅助队列
    	bfsList.add(vertexList.get(index));
//    	System.out.print(vertexList.get(index) + " "); // 首先我们访问该结点,输出
    	isVisited[index] = true; // index已经在上一行被访问过了，所以变为true
    	queue.add(index); // 使A进入队列
    	// 对队列中的几个元素依次执行广度遍历
    	while (!queue.isEmpty()) {
            u = queue.poll();// 取出队列的头
            w = getFirstNeighbor(u); // 得到第一个邻接点的下标
            while (w != -1) { // 如果有邻接顶点就循换，直到没有了为止
                if (!isVisited[w]) { // 如果w没被访问过
                    //如果第一个邻接点未被访问则访问第一个邻接节点
                	bfsList.add(getValueByIndex(w));
//                    System.out.print(getValueByIndex(w) + " ");
                    isVisited[w] = true;
                    queue.add(w);
                }
        		// 如果w结点已经被访问过，寻找u的下一个邻接顶点。实现广度遍历
        		w = getNextNeighbor(u, w);
            }
    	}
    }
    
    // 显示图对应的矩阵
    public void display() {

    	for (int i = 0; i < vertexNumber; i++) {
            System.out.print(vertexList.get(i)+"\t");
    	}
        System.out.println();
    	for (int i = 0; i < vertexNumber; i++) {
        	for (int j = 0; j < vertexNumber; j++) {
                System.out.print(arcs[i][j] + "\t");
    		}
        	System.out.println();
		}
	}

    private static final int INF = Integer.MAX_VALUE;

    /**
     *  @descript 最小生成树算法：prim算法
     *  @param: graph
     */
    public void prim(int[][] graph) {
        int vertices = graph.length;
        int[] parent = new int[vertices]; // 用于存储最小生成树的父节点
        int[] key = new int[vertices]; // 用于存储顶点与最小生成树的最小权重
        boolean[] mstSet = new boolean[vertices]; // 用于记录顶点是否已加入最小生成树

        Arrays.fill(key, INF);  // 初始化所有顶点的权重为无穷大
        Arrays.fill(mstSet, false); // 初始化所有顶点均未加入最小生成树

        key[0] = 0; // 初始顶点的权重为0，即将其作为起始节点
        parent[0] = -1;  // 初始节点没有父节点
        
        // 依次加入(n-1)个顶点到最小生成树中
        for (int i = 0; i < vertices - 1; i++) {
            int u = minKey(key, mstSet, vertices); // 选择权重最小的顶点u加入最小生成树
            mstSet[u] = true;
            
            // 更新相邻顶点的权重和父节点信息
            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        
        // 打印最小生成树的边和权重
        System.out.println("Edge \tWeight");
        for (int i = 1; i < graph.length; i++) {
            if (parent[i] != -1) { // 避免打印起始节点的边
            	System.out.println(parent[i] + " - " + i + "\t" + graph[parent[i]][i]);
            }
        }
    }
    // 寻找权重最小的未加入最小生成树的顶点
    private int minKey(int[] key, boolean[] mstSet, int vertices) {
        int min = INF, minIndex = -1;
        for (int v = 0; v < vertices; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    /**
     *  @descript 最小生成树算法：kruskal算法
     *  @param graph
     */
    public void kruskal(int[][] graph) {
        int vertices = graph.length;
        List<int[]> edges = new ArrayList<>();

        // 将图的边存储在edges列表中
        for (int i = 0; i < vertices; i++) {
            for (int j = i + 1; j < vertices; j++) {
                if (graph[i][j] != 0) {
                    edges.add(new int[]{i, j, graph[i][j]});
                }
            }
        }

        // 按权重对边进行排序
        edges.sort(Comparator.comparingInt(a -> a[2]));

        int[] parent = new int[vertices];
        Arrays.fill(parent, -1);

        List<int[]> mst = new ArrayList<>();
        int edgeCount = 0;

        for (int[] edge : edges) {
            int x = find(parent, edge[0]);
            int y = find(parent, edge[1]);

            // 如果边的两个顶点不在同一个连通分量中，则加入最小生成树
            if (x != y) {
                mst.add(edge);
                union(parent, x, y);
                edgeCount++;

                if (edgeCount == vertices - 1) // 已找到n-1条边，则退出循环
                    break;
            }
        }

        System.out.println("Edge \tWeight");
        for (int[] edge : mst) {
            System.out.println(edge[0] + " - " + edge[1] + "\t" + edge[2]);
        }
    }
    private int find(int[] parent, int i) {
        if (parent[i] == -1) {
            return i;
        }
        return find(parent, parent[i]);
    }
    private void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        parent[rootX] = rootY;
    }
    
	public static void main(String[] args) {
		AdjacencyMatrixGraph adjacencyMatrixGraph = new AdjacencyMatrixGraph(5);
		adjacencyMatrixGraph.insertVertex("A");
		adjacencyMatrixGraph.insertVertex("B");
		adjacencyMatrixGraph.insertVertex("C");
		adjacencyMatrixGraph.insertVertex("D");
		adjacencyMatrixGraph.insertVertex("E");
		
		adjacencyMatrixGraph.insertEdge(0, 1, 15);
		adjacencyMatrixGraph.insertEdge(0, 4, 9);
		adjacencyMatrixGraph.insertEdge(1, 2, 3);
		adjacencyMatrixGraph.insertEdge(2, 3, 2);
		adjacencyMatrixGraph.insertEdge(3, 0, 11);
		adjacencyMatrixGraph.insertEdge(3, 1, 7);
		adjacencyMatrixGraph.insertEdge(4, 2, 21 );

		System.out.println("==========Adjacency Matrix==========");
		adjacencyMatrixGraph.display();
//		adjacencyMatrixGraph.getFirstNeighbor(1);
//		adjacencyMatrixGraph.getNextNeighbor(3, 0);
		System.out.println("============Deep First Search============");
		List<Object> dfsList = adjacencyMatrixGraph.deepFirstSearch(); // A B C D E 
		System.out.println(dfsList);
		System.out.println("==========Breadth First Search===========");
		List<Object> bfsList = adjacencyMatrixGraph.breadthFirstSearch(); // A B E C D
		System.out.println(bfsList);
		System.out.println("============Prim============");
		adjacencyMatrixGraph.prim(adjacencyMatrixGraph.arcs);
		System.out.println("============Kruskal============");
		adjacencyMatrixGraph.kruskal(adjacencyMatrixGraph.arcs);
		/*   A B C D E
		 * A 0 1 0 0 1 
		 * B 0 0 1 0 0 
		 * C 0 0 0 1 0 
		 * D 1 1 0 0 0 
		 * E 0 0 1 0 0 
		 */
		
	}
}
