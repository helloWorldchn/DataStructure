package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
        System.out.println("Edge" + "\t" + "Weight");
        for (int i = 1; i < graph.length; i++) {
            if (parent[i] != -1) { // 避免打印起始节点的边
            	System.out.println(vertexList.get(parent[i]) + " -> " + vertexList.get(i) + "\t" + graph[parent[i]][i]);
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
        List<Edge> edges = new ArrayList<>(); // 用于存储图中的所有边
        // 将图的边存储在edges列表中
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (graph[i][j] != 0) {
                	Edge edge = new Edge(i, j, graph[i][j]);  // 边的起点、终点和权值
                    edges.add(edge);
                }
            }
        }

        // 按权重对边进行排序
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));
        int[] parent = new int[vertices]; // 用于存储每个顶点的父节点
        Arrays.fill(parent, -1);

        List<Edge> mst = new ArrayList<>();  // 用于存储最小生成树的边
        int edgeCount = 0;
        // 遍历所有边
        for (Edge edge : edges) {
            int x = find(parent, edge.src); // 查找边的起点的根节点
            int y = find(parent, edge.dest); // 查找边的终点的根节点

            // 如果边的两个顶点不在同一个连通分量中，则加入最小生成树
            if (x != y) {
                mst.add(edge);  // 将边加入最小生成树
                union(parent, x, y); // 合并两个顶点的连通分量
                edgeCount++;  // 增加边的数量
                if (edgeCount == vertices - 1) { // 已找到n-1条边，则退出循环
                    break;
                }
            }
        }
        // 输出最小生成树的边和权重
        System.out.println("Edge" + "\t" + "Weight");
        for (Edge edge : mst) {
        	System.out.println(vertexList.get(edge.src) + " -> " + vertexList.get(edge.dest) + "\t" +  edge.weight);
        }
    }
    // 查找顶点i的根节点
    private int find(int[] parent, int i) {
        if (parent[i] == -1) {
            return i; // 如果顶点i的父节点为-1，表示i是根节点
        }
        return find(parent, parent[i]); // 递归查找i的根节点
    }
    // 合并两个顶点x和y的连通分量
    private void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);// 查找顶点x的根节点
        int rootY = find(parent, y);// 查找顶点y的根节点
        parent[rootX] = rootY; // 将顶点x的根节点的父节点设为顶点y的根节点
    }
    // 辅助类表示边的信息
    class Edge {
        int src;
        int dest;
        int weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
    
    /**
     *  @descript 最短路径算法：dijkstra算法
     *  @param start
     *  @return int[]
     */
    public int[] dijkstra(int start) {
    	if (start > vertexList.size() || start < 0) {
            System.out.println("Start vertex not found.");
            return new int[] {};
		}
        // 初始化
        boolean[] visited = new boolean[vertexList.size()]; // 存放所有已知实际最短路径值的顶点,true代表已知其最短路径
        int[] path = new int[vertexList.size()]; // 表示从源点到顶点之间的最短路径的前驱结点
        int[] dist = new int[vertexList.size()]; // 表示从源点到顶点之间的弧上的权值
        for (int i = 0; i < vertexList.size(); i++) {
            visited[i] = false; // 顶点i的最短路径还没获取到。
            path[i] = 0;  // 顶点i的前驱顶点为0。
            dist[i] = arcs[start][i]==0 ? INF : arcs[start][i];  // 顶点i的最短路径为起点到顶点i的权值
        }
        visited[start] = true;
        dist[start] = 0;

        // 迭代计算最短路径
        int k=0;
        for (int i = 1; i < vertexList.size(); i++) {
            // 寻找当前最小的路径；
            // 即，在未获取最短路径的顶点中，找到离vs最近的顶点(k)。
            int min = INF;
            for (int j = 0; j < vertexList.size(); j++) {
                if (!visited[j] && dist[j]<min) {
                    min = dist[j];
                    k = j;
                }
            }
            visited[k] = true; // 第k个顶点已找到最短路径

            // 更新最短路径dist和前驱顶点path
            for (int j = 0; j < vertexList.size(); j++) {
                if (!visited[j]&& arcs[k][j] != 0 && min!=INF && (min + arcs[k][j] < dist[j]) ) {
                    dist[j] = (arcs[k][j]==INF ? INF : (min + arcs[k][j]));
                    path[j] = k;
                }
            }
        }
        // 打印最短路径和最短路径长度
        for (int i = 0; i < vertexList.size(); i++) {
        	if (i != start) {
                System.out.print("Shortest Path of "+ vertexList.get(start) +" to " + vertexList.get(i)+ " : " + dist[i]);
                System.out.print("\t" +"Path : ");
                int index = i;
                Stack<Integer> stack = new Stack<>();
                stack.push(index);
                while (path[index] != start) {
                    stack.push(path[index]);
                    index = path[index];
                }
                stack.push(start);
                while (!stack.isEmpty()) {
                    System.out.print(vertexList.get(stack.pop()));
                	if (!stack.isEmpty()) {
                        System.out.print("->");
					}
                }
                System.out.println();
			}
        }
        return dist;
    }
    
    /**
     *  @descript 最短路径算法：Floyd算法
     *  @return int[][]
     */
    public int[][] floyd() {
        // 初始化
    	int[][] dist = new int[vertexNumber][vertexNumber]; // 矩阵 作为距离
    	int[][] path = new int[vertexNumber][vertexNumber]; // 前驱结点
        for (int i = 0; i < vertexNumber; i++) {
            for (int j = 0; j < vertexNumber; j++) {
            	dist[i][j] = arcs[i][j]==0 ? INF: arcs[i][j];
                if (i != j && dist[i][j] < INF) {
                    path[i][j] = i;
                } else {
                    path[i][j] = -1;
                }
            }
        }
        // 动态规划思想迭代计算
        for (int k = 0; k < vertexNumber; k++) {
            for (int i = 0; i < vertexNumber; i++) {
                for (int j = 0; j < vertexNumber; j++) {
                	if (i!=j && dist[i][k] != INF && dist[k][j] != INF) {
                        path[i][j] = dist[i][j]>dist[i][k] + dist[k][j]? path[k][j]: path[i][j];
                    	dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]); // 更新递推公式
					}
                }
            }
        }
        // 打印
        for (int i = 0; i < vertexNumber; i++) {
            for (int j = 0; j < vertexNumber; j++) {
                if (i != j && dist[i][j] != INF) {
                    System.out.print("Shortest Path of " + vertexList.get(i) + " to " + vertexList.get(j) + " : " + dist[i][j]);
                    System.out.print("\t" +"Path : ");
                    int temp = path[i][j];
                    Stack<Integer> stack = new Stack<>();
                    stack.push(j);
                    while (path[i][temp] != -1) {
                        stack.push(temp);
                        temp = path[i][temp];
                    }
                    stack.push(i);
                    while (!stack.isEmpty()) {
                        System.out.print(vertexList.get(stack.pop()));
                        if (!stack.isEmpty()) {
                            System.out.print("->");
                        }
                    }
                    System.out.println();
                }
            }
        }
        return dist;
    }
    
	public static void main(String[] args) {
		AdjacencyMatrixGraph adjacencyMatrixGraph = new AdjacencyMatrixGraph(5);
		adjacencyMatrixGraph.insertVertex("A");
		adjacencyMatrixGraph.insertVertex("B");
		adjacencyMatrixGraph.insertVertex("C");
		adjacencyMatrixGraph.insertVertex("D");
		adjacencyMatrixGraph.insertVertex("E");
		
		adjacencyMatrixGraph.insertEdge(0, 1, 15);
		adjacencyMatrixGraph.insertEdge(0, 2, 9);
		adjacencyMatrixGraph.insertEdge(0, 4, 11);
		adjacencyMatrixGraph.insertEdge(1, 3, 3);
		adjacencyMatrixGraph.insertEdge(1, 4, 7);
		adjacencyMatrixGraph.insertEdge(2, 3, 21);
		adjacencyMatrixGraph.insertEdge(3, 4, 2);
		
		adjacencyMatrixGraph.insertEdge(1, 0, 15);
		adjacencyMatrixGraph.insertEdge(2, 0, 9);
		adjacencyMatrixGraph.insertEdge(4, 0, 11);
		adjacencyMatrixGraph.insertEdge(3, 1, 3);
		adjacencyMatrixGraph.insertEdge(4, 1, 7);
		adjacencyMatrixGraph.insertEdge(3, 2, 21);
		adjacencyMatrixGraph.insertEdge(4, 3, 2);
		
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
		System.out.println("============Dijkstra============");
		adjacencyMatrixGraph.dijkstra(0);
		System.out.println("============Floyd============");
		adjacencyMatrixGraph.floyd();
		/*  
		 *		A	B	C	D	E	
		 *	A	0	15	0	11	9	
		 *	B	15	0	3	7	0	
		 *	C	0	3	0	2	21	
		 *	D	11	7	2	0	0	
		 *	E	9	0	21	0	0	
		 */
		
	}
}
