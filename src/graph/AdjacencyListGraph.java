package graph;

import java.util.ArrayList;

//�ڽӱ�
public class AdjacencyListGraph {

	// ��Ϊĳ������ڽӵ�Ķ�����Ϣ��һ������
	public class GraphNode{
        String vertex; // ����
        int weight; // �Ըö���Ϊ�յ�ıߵ�Ȩֵ
        GraphNode next; // ָ����һ�����ĵ�ַ��
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
	
	ArrayList<String> vertexList; //�洢���㼯��
	GraphNode[] headNode; // �ڽӱ���������нڵ㣬ÿһ�������ͷ���
    int vertexNumber = 0; // ͼ�ĵ�ǰ�������Ŀ
    int edgeNumber = 0; // ͼ�ĵ�ǰ�ߵ���Ŀ
    
    // ��ʼ��
    public AdjacencyListGraph(int maxVertex) {
    	vertexList = new ArrayList<String>(maxVertex);
    	headNode = new GraphNode[maxVertex];
    }
    
    //���붥��
    public void insertVertex(String vertex) {
    	GraphNode graphNode = new GraphNode(vertex);
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
     * ����һ��firstNode��secondNode�����ıߣ���Ȩ��Ϊweight
     * @param firstNode ��һ�����������
     * @param secondNode �ڶ������������
     * @param weight ����֮��ıߵ�Ȩ��
     */
    public void insertEdge(String firstNode, String secondNode, int weight) {
    	boolean isContainsFirst = vertexList.contains(firstNode);
    	boolean isContainsSecond = vertexList.contains(secondNode);
    	if (isContainsFirst && isContainsSecond) { // Ҫ��ӵ�����������ڶ��㼯����
    		for (int i = 0; i < headNode.length; i++) { // �������е�ͷ���
    			if (headNode[i] != null && headNode[i].vertex.equals(firstNode)) {
    				GraphNode graphNode = headNode[i]; // Ҫ���ĸ�ͷ���������ȱ�ǳ���
    				boolean isExist = false; // ���Ҫ��ӵı��Ƿ����
    				
    				// �Դ�Ѱ��graphNodeΪͷ�����������нڵ㣬���Ƿ��к�secondNode��ͬ��
    				// �����secondNode������ͬ��˵�������ڵ��ı��Ѿ�����
    				while (graphNode.next != null) {
    					if(graphNode.vertex.equals(secondNode)) {
    						isExist = true; // �����ڵ��ı��Ѿ�����
    						break;
    					}
    					graphNode = graphNode.next;
    				}
    				
    				// �����ڵ�֮��ı߲����ڣ���ô�������������Ϣ
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
    
    
	//��ӡ
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
