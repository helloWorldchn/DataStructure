package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
        EdgeNode(String vertex, EdgeNode next, int weight) { 
        	this.vertex = vertex;
        	this.next = next; 
        	this.weight = weight;
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
				System.out.print("-->"+edgeNode.weight+"\t"+  "[" + headNode[edgeNode.adjvex].data + "|" + edgeNode.weight + "]");
				EdgeNode temp = edgeNode.next;
                while (temp != null){
                    System.out.print("-->"+edgeNode.weight +"\t"+ "[" + headNode[temp.adjvex].data + "|" + temp.weight + "]");
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
    
    
	public static void main(String[] args) {
		AdjacencyListGraph adjacencyListGraph = new AdjacencyListGraph(5);

		adjacencyListGraph.insertVertex("A");
		adjacencyListGraph.insertVertex("B");
		adjacencyListGraph.insertVertex("C");
		adjacencyListGraph.insertVertex("D");
		adjacencyListGraph.insertVertex("E");
		
		adjacencyListGraph.insertEdge("A", "B", 15);
		adjacencyListGraph.insertEdge("A", "E", 9);
		adjacencyListGraph.insertEdge("B", "C", 3);
		adjacencyListGraph.insertEdge("C", "D", 2);
		adjacencyListGraph.insertEdge("D", "A", 11);
		adjacencyListGraph.insertEdge("D", "B", 7);
		adjacencyListGraph.insertEdge("E", "C", 21);
		System.out.println("==========Adjacency List==========");
		adjacencyListGraph.display();
		System.out.println("==========Deep First Search==========");
		List<Object> dfsList = adjacencyListGraph.deepFirstSearch();
		System.out.println(dfsList);

		System.out.println("==========Breadth First Search==========");
		List<Object> bfsList = adjacencyListGraph.breadthFirstSearch();
		System.out.println(bfsList);
		/*
		 * A-->15	[B|15]-->15	[E|9]
		 * B-->3	[C|3]
		 * C-->2	[D|2]
		 * D-->11	[A|11]-->11	[B|7]
		 * E-->21	[C|21]
		 */
	}
}
