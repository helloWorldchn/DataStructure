package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

//邻接表
public class AdjacencyListGraph {
	// 邻接表中表对应的链表的顶点
	public class EdgeNode{
        String vertex; // 顶点值
        int weight; // 以该顶点为终点的边的权值
        int adjvex; // 邻接点域，存储该顶点对应的下标
        EdgeNode next; // 指向下一个边的地址域
        EdgeNode() {	
		}
        EdgeNode(String vertex) { 
			this.vertex = vertex; 
		}
        EdgeNode(String vertex, EdgeNode next) { 
			this.vertex = vertex;
			this.next = next; 
		}
        EdgeNode(String vertex, int adjvex, int weight) { 
        	this.vertex = vertex;
        	this.adjvex = adjvex; 
        	this.weight = weight;
        }
        EdgeNode(String vertex, int adjvex,  int weight, EdgeNode next) { 
        	this.vertex = vertex;
        	this.adjvex = adjvex; 
        	this.weight = weight;
        	this.next = next; 
        }
	}
	// 作为某个点的邻接点的顶点信息
	public class VertexNode{
        String data; // 顶点的值
        EdgeNode firstEdge; // 指向第一条依附该顶点的弧
        VertexNode() {	
		}
        VertexNode(String data) { 
			this.data = data; 
		}
	}
	
	ArrayList<String> vertexList; //存储顶点集合
	VertexNode[] headNode; // 邻接表中左侧所有节点，每一行链表的头结点
    int vertexNumber = 0; // 图的当前顶点的数目
    int edgeNumber = 0; // 图的当前边的数目
    
    // 初始化
    public AdjacencyListGraph(int maxVertex) {
    	vertexList = new ArrayList<String>(maxVertex);
    	headNode = new VertexNode[maxVertex];
    }
    
    //插入顶点
    public void insertVertex(String vertex) {
    	insertVertex(vertex, 1); // 默认权值为1
    }
    public void insertVertex(String vertex, int weight) {
    	VertexNode graphNode = new VertexNode(vertex);
    	// 需要将顶点值添加到adjacencyListNode数组中，但是不知道数组空位在哪里，所以需要循换
        for (int i = 0; i < headNode.length; i++){
            if(headNode[i] == null){ // 如果第i个位置是null。说明添加到该位置中
            	headNode[i] = graphNode;// 添加到邻接表中左侧
            	vertexList.add(vertex); // 把要插入的顶点值添加到vertexList中。
            	vertexNumber ++;
            	System.out.println(vertex + " has been entered!");
                break;
            }
        }
    }
    
    /**
     * @descript 创建一条firstNode与secondNode相连的边，其权重为weight
     * @param firstNode 第一个顶点的名称
     * @param secondNode 第二个顶点的名称
     * @param weight 两点之间的边的权重
     */

    public void insertEdge(String firstNode, String secondNode) {
    	insertEdge(firstNode, secondNode, 1);
    }
    public void insertEdge(String firstNode, String secondNode, int weight) {
    	boolean isContainsFirst = vertexList.contains(firstNode);
    	boolean isContainsSecond = vertexList.contains(secondNode);
    	if (isContainsFirst && isContainsSecond) { // 要添加的两个点存在于顶点集合里
    		for (int i = 0; i < headNode.length; i++) { // 遍历所有的头结点
    			if (headNode[i] != null && headNode[i].data.equals(firstNode)) {
    				VertexNode vertexNode = headNode[i]; // 要对哪个头结点操作，先标记出来
    				boolean isExist = false; // 标记要添加的边是否存在
					int adjvex = -1;
		    		for (int j = 0; j < headNode.length; j++) { // 遍历所有的头结点
		    			if (headNode[j].data.equals(secondNode)) {
		    				adjvex = j;
		    			}
		    		}
    				if(vertexNode.firstEdge == null) {
    					EdgeNode edgeNode = new EdgeNode();
    					edgeNode.vertex = secondNode;
    					edgeNode.weight = weight;
    					edgeNode.adjvex = adjvex;
    					vertexNode.firstEdge = edgeNode;
    					System.out.println(firstNode+"->"+secondNode+" have added edges!");
    					continue;
    				}
    				EdgeNode edgeNode = vertexNode.firstEdge;
    				// 以此寻找graphNode为头结点的链表所有节点，看是否有和secondNode相同的
    				// 如果和secondNode名字相同，说明两个节点间的边已经存在
    				while (edgeNode.next != null) {
    					if(edgeNode.vertex.equals(secondNode)) {
    						isExist = true; // 两个节点间的边已经存在
    						break;
    					}
    					edgeNode = edgeNode.next;
    				}
    				
    				// 两个节点之间的边不存在，那么在链表中添加信息
    				if (!isExist) {
    					EdgeNode newEdgeNode = new EdgeNode();
    					newEdgeNode.vertex = secondNode;
    					newEdgeNode.weight = weight;
    					newEdgeNode.adjvex = adjvex;
    					edgeNode.next = newEdgeNode;
    					System.out.println(firstNode+"->"+secondNode+" have added edges!");
    				}
    				break;	
    			}
    		}
    	}
    }
    
    
	//打印
	public void display(){
		for (int i = 0; i < headNode.length; i++) {
			EdgeNode edgeNode = headNode[i].firstEdge;
            System.out.print(headNode[i].data);
			if (edgeNode != null) {
				System.out.print("-->"+"\t"+  "[" + headNode[edgeNode.adjvex].data + "|" + edgeNode.weight + "]");
				EdgeNode temp = edgeNode.next;
                while (temp != null){
                    System.out.print("-->"+"\t"+ "[" + headNode[temp.adjvex].data + "|" + temp.weight + "]");
                    temp = temp.next;
                }
                System.out.println();
			} else {
				break;
			}
		}
	}
	
    /**
     *  Deep First Search Algorithm: 深度优先搜索算法(dfs)
     *  @return: java.util.List<java.lang.String>
     */
    public List<Object> deepFirstSearch() { 
        return deepFirstSearch(0) ; // 默认遍历起始节点为0
    }
    public List<Object> deepFirstSearch(int startIndex) {
    	List<Object> dfsList = new ArrayList<>(); // 存放遍历结果
        int i;
        boolean[] visited = new boolean[vertexNumber]; // 记录每个顶点是否被访问过
        for (i = startIndex; i < vertexNumber+startIndex; i++) {
        	int index = i%vertexNumber;
            if (!visited[index]) {
            	deepFirstSearch(index, visited, dfsList);
            }
        }
        return dfsList;
    }
    public void deepFirstSearch(int index, boolean[] visited, List<Object> dfsList) {
        dfsList.add(headNode[index].data); // 遍历到第index节点
        EdgeNode edgeNode = headNode[index].firstEdge; // 此顶点的第一条边
        visited[index] = true;
        while (edgeNode != null) {
            if (!visited[edgeNode.adjvex]) {
            	deepFirstSearch(edgeNode.adjvex, visited, dfsList);
            }
            edgeNode = edgeNode.next;
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
    	Queue<Integer> queue = new ArrayDeque<>(); // 辅助队列
        if (!isVisited[index]) {
        	isVisited[index] = true;
        	bfsList.add(headNode[index].data);
        	queue.add(index); // 进入队列
        }
    	isVisited[index] = true; // index已经在上一行被访问过了，所以变为true
    	// 对队列中的几个元素依次执行广度遍历
    	while (!queue.isEmpty()) {
    		int j = queue.poll();// 取出队列的头
            EdgeNode edgeNode = headNode[j].firstEdge;
            while (edgeNode != null) {
                int k = edgeNode.adjvex;
                if (!isVisited[k])
                {
                	isVisited[k] = true;
                	bfsList.add(headNode[k].data);
                    queue.add(k);
                }
                edgeNode = edgeNode.next;
            }
    	}
    }
    
    /**
     *  @descript 最小生成树算法：prim算法
     *  @param: graph
     */
    public void prim() {
        int V = vertexNumber;
        boolean[] mstSet = new boolean[V]; // 存储顶点是否已加入最小生成树的集合
        int[] key = new int[V]; // 存储顶点到最小生成树的最小权重
        int[] parent = new int[V]; // 存储最小生成树的边

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0; // 将起始顶点的key值设为0
        parent[0] = -1; // 将起始顶点的父节点设为-1

        for (int count = 0; count < vertexNumber - 1; count++) {
            int u = minKey(key, mstSet, vertexNumber); // 选择key值最小的顶点u
            mstSet[u] = true; // 将顶点u标记为已访问

            // 更新与顶点u相邻的顶点的key值和parent信息
            EdgeNode current = headNode[u].firstEdge;
            while (current != null) {
                int v = vertexList.indexOf(current.vertex);
                int weight = current.weight;
                if (!mstSet[v] && weight < key[v]) {
                    parent[v] = u;
                    key[v] = weight;
                }
                current = current.next;
            }
        }

        // 输出最小生成树的边和权重
        System.out.println("Edge" + "\t" + "Weight");
        for (int i = 1; i < vertexNumber; i++) {
        	if (headNode[i].firstEdge.vertex == vertexList.get(parent[i])) {
				// 不是头结点，直接确定对应权值
                System.out.println(vertexList.get(parent[i]) + " -> " + vertexList.get(i) +  "\t"  + headNode[i].firstEdge.weight);
			} else {
				// 不是头结点，顺着链表遍历，寻找对应权值
				EdgeNode currentEdge = headNode[i].firstEdge;
				while (currentEdge!=null) {
					if (currentEdge.vertex == vertexList.get(parent[i])) {
		                System.out.println(vertexList.get(parent[i]) + " -> " + vertexList.get(i) +  "\t"  + currentEdge.weight);
						break;
					}
					currentEdge = currentEdge.next;
				}
			}
        }
    }

    // 选择key值最小的顶点
    private int minKey(int[] key, boolean[] mstSet, int V) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    
    /**
     *  @descript 最小生成树算法：kruskal算法
     */
    public void kruskal() {
        List<Edge> edges = new ArrayList<>();// 用于存储图中的所有边
        // 将图的边存储在edges列表中
        for (int i = 0; i < vertexNumber; i++) {
            EdgeNode current = headNode[i].firstEdge;
            while (current != null) {
            	Edge edge = new Edge(i, vertexList.indexOf(current.vertex), current.weight);
                edges.add(edge);
                current = current.next;
            }
        }
        // 将图的边存储在edges列表中
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));

        int[] parent = new int[vertexNumber]; // 用于存储每个顶点的父节点
        Arrays.fill(parent, -1);
        List<Edge> mst = new ArrayList<>();  // 用于存储最小生成树的边

        int edgeCount = 0;
        // 遍历所有边
        for (Edge edge : edges) {
            int x = find(parent, edge.src);
            int y = find(parent, edge.dest);
            // 如果边的两个顶点不在同一个连通分量中，则加入最小生成树
            if (x != y) {
                mst.add(edge); // 将边加入最小生成树
                union(parent, x, y); // 合并两个顶点的连通分量
                edgeCount++;  // 增加边的数量
                if (edgeCount == vertexNumber - 1) {// 已找到n-1条边，则退出循环
                    break;
                }
            }
        }
        // 输出最小生成树的边和权重
        for (Edge edge : mst) {
            System.out.println(vertexList.get(edge.src) + " - " + vertexList.get(edge.dest) + " : " + edge.weight);
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
    
	private static final int INF = Integer.MAX_VALUE;
    
    /**
     *  @descript 最短路径算法：dijkstra算法
     *  @param start
     *  @return int[]
     */
    public int[] dijkstra(String startVertex) {
        int start = vertexList.indexOf(startVertex);
        if (start == -1) {
            System.out.println("Start vertex not found.");
            return new int[] {};
        }

        int[] dist = new int[vertexNumber];  // 表示从源点到顶点之间的弧上的权值
        boolean[] visited = new boolean[vertexNumber];// 存放所有已知实际最短路径值的顶点,true代表已知其最短路径
        int[] path = new int[vertexNumber]; // 表示从源点到顶点之间的最短路径的前驱结点
        Arrays.fill(dist, INF);
        Arrays.fill(path, -1);
        dist[start] = 0;

        for (int i = 0; i < vertexNumber; i++) {
            int u = -1;
            int minDist = INF;
            for (int j = 0; j < vertexNumber; j++) {
                if (!visited[j] && dist[j] < minDist) {
                    u = j;
                    minDist = dist[j];
                }
            }

            if (u == -1) break; // 所有可达顶点已访问完毕
            visited[u] = true;

            EdgeNode edgeNode = headNode[u].firstEdge;
            while (edgeNode != null) {
                int v = edgeNode.adjvex;
                int weight = edgeNode.weight;
                if (!visited[v] && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    path[v] = u;
                }
                edgeNode = edgeNode.next;
            }
        }

        // 打印最短路径和最短路径长度
        for (int i = 0; i < vertexNumber; i++) {
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
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
            path[i][i] = -1;
        }
        for (int i = 0; i < vertexNumber; i++) {
            EdgeNode edgeNode = headNode[i].firstEdge;
            while (edgeNode != null) {
                dist[i][edgeNode.adjvex] = edgeNode.weight;
                path[i][edgeNode.adjvex] = i;
                edgeNode = edgeNode.next;
            }
        }
        
        // 动态规划思想迭代计算
        for (int k = 0; k < vertexNumber; k++) {
            for (int i = 0; i < vertexNumber; i++) {
                for (int j = 0; j < vertexNumber; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        path[i][j] = dist[i][j]>dist[i][k] + dist[k][j]? path[k][j]: path[i][j];
                    	dist[i][j] = dist[i][k] + dist[k][j];
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
		AdjacencyListGraph adjacencyListGraph = new AdjacencyListGraph(5);

		adjacencyListGraph.insertVertex("A");
		adjacencyListGraph.insertVertex("B");
		adjacencyListGraph.insertVertex("C");
		adjacencyListGraph.insertVertex("D");
		adjacencyListGraph.insertVertex("E");
		
		
		adjacencyListGraph.insertEdge("A", "B", 15);
		adjacencyListGraph.insertEdge("A", "C", 9);
		adjacencyListGraph.insertEdge("A", "E", 11);
		adjacencyListGraph.insertEdge("B", "D", 3);
		adjacencyListGraph.insertEdge("B", "E", 7);
		adjacencyListGraph.insertEdge("C", "D", 21);
		adjacencyListGraph.insertEdge("D", "E", 2);
		
		adjacencyListGraph.insertEdge("B", "A", 15);
		adjacencyListGraph.insertEdge("C", "A", 9);
		adjacencyListGraph.insertEdge("E", "A", 11);
		adjacencyListGraph.insertEdge("D", "B", 3);
		adjacencyListGraph.insertEdge("E", "B", 7);
		adjacencyListGraph.insertEdge("D", "C", 21);
		adjacencyListGraph.insertEdge("E", "D", 2);
		
		System.out.println("==========Adjacency List==========");
		adjacencyListGraph.display();
		System.out.println("==========Deep First Search==========");
		List<Object> dfsList = adjacencyListGraph.deepFirstSearch();
		System.out.println(dfsList);

		System.out.println("==========Breadth First Search==========");
		List<Object> bfsList = adjacencyListGraph.breadthFirstSearch();
		System.out.println(bfsList);

		System.out.println("============Prim============");
		adjacencyListGraph.prim();
		System.out.println("============Kruskal============");
		adjacencyListGraph.kruskal();
		System.out.println("============dijkstra============");
		adjacencyListGraph.dijkstra("A");
		System.out.println("============Floyd============");
		adjacencyListGraph.floyd();
		/*
		 * A-->	[B|15]-->	[E|9]-->	[D|11]
		 * B-->	[C|3]-->	[A|15]-->	[D|7]
		 * C-->	[D|2]-->	[B|3]-->	[E|21]
		 * D-->	[A|11]-->	[B|7]-->	[C|2]
		 * E-->	[C|21]-->	[A|9]
		 */
	}
}
