package graph;

import java.util.ArrayList;

//邻接表
public class AdjacencyListGraph {

	// 作为某个点的邻接点的顶点信息（一条链表）
	public class GraphNode{
        String vertex; // 顶点
        int weight; // 以该顶点为终点的边的权值
        GraphNode next; // 指向下一个结点的地址域
        GraphNode() {	
		}
        GraphNode(String vertex) { 
			this.vertex = vertex; 
		}
        GraphNode(String vertex, GraphNode next) { 
			this.vertex = vertex;
			this.next = next; 
		}
        GraphNode(String vertex, GraphNode next, int weight) { 
        	this.vertex = vertex;
        	this.next = next; 
        	this.weight = weight;
        }
	}
	
	ArrayList<String> vertexList; //存储顶点集合
	GraphNode[] headNode; // 邻接表中左侧所有节点，每一行链表的头结点
    int vertexNumber = 0; // 图的当前顶点的数目
    int edgeNumber = 0; // 图的当前边的数目
    
    // 初始化
    public AdjacencyListGraph(int maxVertex) {
    	vertexList = new ArrayList<String>(maxVertex);
    	headNode = new GraphNode[maxVertex];
    }
    
    //插入顶点
    public void insertVertex(String vertex) {
    	GraphNode graphNode = new GraphNode(vertex);
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
     * 创建一条firstNode与secondNode相连的边，其权重为weight
     * @param firstNode 第一个顶点的名称
     * @param secondNode 第二个顶点的名称
     * @param weight 两点之间的边的权重
     */
    public void insertEdge(String firstNode, String secondNode, int weight) {
    	boolean isContainsFirst = vertexList.contains(firstNode);
    	boolean isContainsSecond = vertexList.contains(secondNode);
    	if (isContainsFirst && isContainsSecond) { // 要添加的两个点存在于顶点集合里
    		for (int i = 0; i < headNode.length; i++) { // 遍历所有的头结点
    			if (headNode[i] != null && headNode[i].vertex.equals(firstNode)) {
    				GraphNode graphNode = headNode[i]; // 要对哪个头结点操作，先标记出来
    				boolean isExist = false; // 标记要添加的边是否存在
    				
    				// 以此寻找graphNode为头结点的链表所有节点，看是否有和secondNode相同的
    				// 如果和secondNode名字相同，说明两个节点间的边已经存在
    				while (graphNode.next != null) {
    					if(graphNode.vertex.equals(secondNode)) {
    						isExist = true; // 两个节点间的边已经存在
    						break;
    					}
    					graphNode = graphNode.next;
    				}
    				
    				// 两个节点之间的边不存在，那么在链表中添加信息
    				if (!isExist) {
    					GraphNode newGraphNode = new GraphNode();
    					newGraphNode.vertex = secondNode;
    					newGraphNode.weight = weight;
    					graphNode.next = newGraphNode;
    					System.out.println(firstNode+"->"+secondNode+" have added edges!");
    				}
    				break;	
    			}
    		}
    	}
    }
    
    
	//打印
	public void display(){
		GraphNode current = null;
		for (int i = 0; i < headNode.length; i++) {
			if (headNode[i] != null) {
				current = headNode[i];
				System.out.print(current.vertex);
				GraphNode temp = current.next;
                while (temp != null){
                    System.out.print(" -> "+"[" + temp.vertex + "|" + temp.weight + "]");
                    temp = temp.next;
                }
                System.out.println();
			} else {
				break;
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
		
		adjacencyListGraph.insertEdge("A", "B", 1);
		adjacencyListGraph.insertEdge("A", "E", 1);
		adjacencyListGraph.insertEdge("B", "C", 1);
		adjacencyListGraph.insertEdge("C", "D", 1);
		adjacencyListGraph.insertEdge("D", "A", 1);
		adjacencyListGraph.insertEdge("D", "B", 1);
		adjacencyListGraph.insertEdge("E", "C", 1);
		
		adjacencyListGraph.display();
		
		/*
		 * A -> B 1 -> E 1
		 * B -> C 1 
		 * C -> D 1
		 * D -> A 1 -> B 1
		 * E -> C 1
		 */
	}
}
