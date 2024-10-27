package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//�ڽӱ�
public class AdjacencyListGraph {
	// �ڽӱ��б��Ӧ������Ķ���
	public class EdgeNode{
        String vertex; // ����ֵ
        int weight; // �Ըö���Ϊ�յ�ıߵ�Ȩֵ
        int adjvex; // �ڽӵ��򣬴洢�ö����Ӧ���±�
        EdgeNode next; // ָ����һ���ߵĵ�ַ��
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
	// ��Ϊĳ������ڽӵ�Ķ�����Ϣ
	public class VertexNode{
        String data; // �����ֵ
        EdgeNode firstEdge; // ָ���һ�������ö���Ļ�
        VertexNode() {	
		}
        VertexNode(String data) { 
			this.data = data; 
		}
	}
	
	ArrayList<String> vertexList; //�洢���㼯��
	VertexNode[] headNode; // �ڽӱ���������нڵ㣬ÿһ�������ͷ���
    int vertexNumber = 0; // ͼ�ĵ�ǰ�������Ŀ
    int edgeNumber = 0; // ͼ�ĵ�ǰ�ߵ���Ŀ
    
    // ��ʼ��
    public AdjacencyListGraph(int maxVertex) {
    	vertexList = new ArrayList<String>(maxVertex);
    	headNode = new VertexNode[maxVertex];
    }
    
    //���붥��
    public void insertVertex(String vertex) {
    	insertVertex(vertex, 1); // Ĭ��ȨֵΪ1
    }
    public void insertVertex(String vertex, int weight) {
    	VertexNode graphNode = new VertexNode(vertex);
    	// ��Ҫ������ֵ��ӵ�adjacencyListNode�����У����ǲ�֪�������λ�����������Ҫѭ��
        for (int i = 0; i < headNode.length; i++){
            if(headNode[i] == null){ // �����i��λ����null��˵����ӵ���λ����
            	headNode[i] = graphNode;// ��ӵ��ڽӱ������
            	vertexList.add(vertex); // ��Ҫ����Ķ���ֵ��ӵ�vertexList�С�
            	vertexNumber ++;
            	System.out.println(vertex + " has been entered!");
                break;
            }
        }
    }
    
    /**
     * @descript ����һ��firstNode��secondNode�����ıߣ���Ȩ��Ϊweight
     * @param firstNode ��һ�����������
     * @param secondNode �ڶ������������
     * @param weight ����֮��ıߵ�Ȩ��
     */

    public void insertEdge(String firstNode, String secondNode) {
    	insertEdge(firstNode, secondNode, 1);
    }
    public void insertEdge(String firstNode, String secondNode, int weight) {
    	boolean isContainsFirst = vertexList.contains(firstNode);
    	boolean isContainsSecond = vertexList.contains(secondNode);
    	if (isContainsFirst && isContainsSecond) { // Ҫ��ӵ�����������ڶ��㼯����
    		for (int i = 0; i < headNode.length; i++) { // �������е�ͷ���
    			if (headNode[i] != null && headNode[i].data.equals(firstNode)) {
    				VertexNode vertexNode = headNode[i]; // Ҫ���ĸ�ͷ���������ȱ�ǳ���
    				boolean isExist = false; // ���Ҫ��ӵı��Ƿ����
					int adjvex = -1;
		    		for (int j = 0; j < headNode.length; j++) { // �������е�ͷ���
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
    				// �Դ�Ѱ��graphNodeΪͷ�����������нڵ㣬���Ƿ��к�secondNode��ͬ��
    				// �����secondNode������ͬ��˵�������ڵ��ı��Ѿ�����
    				while (edgeNode.next != null) {
    					if(edgeNode.vertex.equals(secondNode)) {
    						isExist = true; // �����ڵ��ı��Ѿ�����
    						break;
    					}
    					edgeNode = edgeNode.next;
    				}
    				
    				// �����ڵ�֮��ı߲����ڣ���ô�������������Ϣ
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
    
    
	//��ӡ
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
     *  Deep First Search Algorithm: ������������㷨(dfs)
     *  @return: java.util.List<java.lang.String>
     */
    public List<Object> deepFirstSearch() { 
        return deepFirstSearch(0) ; // Ĭ�ϱ�����ʼ�ڵ�Ϊ0
    }
    public List<Object> deepFirstSearch(int startIndex) {
    	List<Object> dfsList = new ArrayList<>(); // ��ű������
        int i;
        boolean[] visited = new boolean[vertexNumber]; // ��¼ÿ�������Ƿ񱻷��ʹ�
        for (i = startIndex; i < vertexNumber+startIndex; i++) {
        	int index = i%vertexNumber;
            if (!visited[index]) {
            	deepFirstSearch(index, visited, dfsList);
            }
        }
        return dfsList;
    }
    public void deepFirstSearch(int index, boolean[] visited, List<Object> dfsList) {
        dfsList.add(headNode[index].data); // ��������index�ڵ�
        EdgeNode edgeNode = headNode[index].firstEdge; // �˶���ĵ�һ����
        visited[index] = true;
        while (edgeNode != null) {
            if (!visited[edgeNode.adjvex]) {
            	deepFirstSearch(edgeNode.adjvex, visited, dfsList);
            }
            edgeNode = edgeNode.next;
        }
    }

    /**
     *  Breadth First Search Algorithm: ������������㷨(bfs)
     *  @return: java.util.List<java.lang.Object>
     */
    public List<Object> breadthFirstSearch() {
        return breadthFirstSearch(0);
    }
    public List<Object> breadthFirstSearch(int startIndex) {
    	List<Object> bfsList = new ArrayList<Object>();
        boolean[] isVisited = new boolean[vertexList.size()]; // ���������boolean[], ��¼ĳ������Ƿ񱻷���
    	isVisited = new boolean[vertexList.size()];
        //�������еĽ�㣬���ν���bfs
        for (int i = startIndex; i < vertexNumber+startIndex; i++) {
        	int index = i%vertexNumber;
            if (!isVisited[index]) {
            	breadthFirstSearch(isVisited, index, bfsList);
            }
        }
        return bfsList;
    }
    public void breadthFirstSearch(boolean[] isVisited, int index, List<Object> bfsList) {
    	Queue<Integer> queue = new ArrayDeque<>(); // ��������
        if (!isVisited[index]) {
        	isVisited[index] = true;
        	bfsList.add(headNode[index].data);
        	queue.add(index); // �������
        }
    	isVisited[index] = true; // index�Ѿ�����һ�б����ʹ��ˣ����Ա�Ϊtrue
    	// �Զ����еļ���Ԫ������ִ�й�ȱ���
    	while (!queue.isEmpty()) {
    		int j = queue.poll();// ȡ�����е�ͷ
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
