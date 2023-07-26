package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// �ڽӾ���
public class AdjacencyMatrixGraph {
	ArrayList<String> vertexList; //�洢���㼯��
	int[][] arcs; // �ڽӾ���
    int vertexNumber; // ͼ�ĵ�ǰ�������Ŀ
    int edgeNumber; // ͼ�ĵ�ǰ�ߵ���Ŀ
    boolean[] isVisited; // ���������boolean[], ��¼ĳ������Ƿ񱻷���
	
    // ��ʼ��
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
	
    //���붥��
    public void insertVertex(String vertex) {
        vertexList.add(vertex); // ��Ҫ�����ֵ��ӵ�vertexList�м��ɡ�
        vertexNumber ++;
        System.out.println(vertex + " has been entered!");
    }
    
    /**
         * ��ӱߣ�a->b�ıߣ�
     * @param a 
     * @param b
     * @param weight Ȩ�أ�����Ȩ��ͼ��weight=1��
     */
    public void insertEdge(int a, int b, int weight) {
    	if(a < vertexNumber && b < vertexNumber) {
        	if (arcs[a][b] == 0) {
            	arcs[a][b] = weight; // ��Ȩ����ӵ�arcs[a][b]�м���
            	System.out.println(vertexList.get(a)+"->"+vertexList.get(b)+" connect edge!");
        	}
        	edgeNumber ++;
    	}
    }

    // �õ�index����ĵ�һ���ڽӽ����±�
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexNumber; j++) {
            if (arcs[index][j] > 0) { // arcs[index][j]��Ϊ��һ���ڽӵ��Ȩ��
//            	System.out.println(vertexList.get(j) + " is the first neighbor");
                return j;
            }
        }
        return -1;
    }
    
    //����ǰһ���ڽӽ����±�����ȡ��һ���ڽӽ��
    public int getNextNeighbor (int a, int b) {
        for (int j = b+1; j < vertexNumber; j++) {
            if (arcs[a][j] > 0) { // arcs[a][j]��Ϊ��һ���ڽӵ��Ȩ��
//            	System.out.println(vertexList.get(j) + " is the next neighbor");
                return j;
            }
        }     
    	return -1;
    }
    
    
    // ���ؽ���Ӧ�Ķ���ֵ 0:"A" 1:"B" 2:"C" 3:"D" 4:"E"
    public String getValueByIndex(int index) {
    	return vertexList.get(index);
    }
    // ��ȡa->b��Ȩֵ
    public int getWeight(int a, int b) {
    	return arcs[a][b];
    }

    
    // ����������� DFS(deep-first search)
    public void deepFirstSearch(boolean[] isVisited, int index) {
    	System.out.print(vertexList.get(index) + " "); // �������Ƿ��ʸý��,���
    	isVisited[index] = true; // index�Ѿ�����һ�б����ʹ��ˣ����Ա�Ϊtrue
    	// ��������������ظ��ҵ�һ���ڽӽڵ㣬ֱ���Ҳ�����Ϊֹ
    	int w = getFirstNeighbor(index); // �õ�index����ĵ�һ���ڽӽ����±�
    	while (w != -1) { // ������ڽӶ����ѭ����ֱ��û����Ϊֹ
    		if (!isVisited[w]) { // ���wû�����ʹ�
    			deepFirstSearch(isVisited, w);
    		}
    		// ���w����Ѿ������ʹ���˵��һ�������������ɣ�Ѱ����һ���ڽӶ���
    		w = getNextNeighbor(index, w);
    	}
    }
    // ��deepFirstSearch����һ������, �����������еĽ�㣬������deepFirstSearch
    public void deepFirstSearch() {
    	isVisited = new boolean[vertexList.size()];
        //�������еĽ�㣬����dfs[����]
        for (int i = 0; i < vertexNumber; i++) {
            if (!isVisited[i]) {
            	deepFirstSearch(isVisited, i);
            }
        }
    }
    
    /*
     * 1��A �ǵ�һ�������Ķ��㣬���A����ʹA������У�Ѱ��A�ĵ�һ���ڽӶ����֪û�У���Ѱ��A����һ���ڽӶ��㣬A��B��D���бߣ������ݶ���˳���֪��һ���ڽӶ���Ϊ B�����Ա��B����ʹB������У�
     * 2����ʱA��Ϊ���е�ͷ����A�����У������Զ���A���б�����Ѱ��B�Ժ���ڽӶ����֪ΪD���ʱ��D��ʹD������У�A�����й����Ķ�����Ѿ������ꣻ
     * 3����ʱB��Ϊ���е�ͷ����B�����У��Զ���B���б�����Ѱ��B�ĵ�һ���ڽӶ����֪ΪA����A�Ѿ������������Ѱ����һ���ڽӶ���ΪE�����Ա��E��ʹE������У�B�����й����Ķ�����Ѿ������ꣻ
     */
    // ����������� BFS(breadth-first search)
    public void breadthFirstSearch(boolean[] isVisited, int index) {
    	int u; // u������е�ͷ����Ӧ���±ꡣ
    	int w; // w�����ڽӽڵ�
    	Queue<Integer> queue = new ArrayDeque<>();
    	System.out.print(vertexList.get(index) + " "); // �������Ƿ��ʸý��,���
    	isVisited[index] = true; // index�Ѿ�����һ�б����ʹ��ˣ����Ա�Ϊtrue
    	queue.add(index); // ʹA�������
    	
    	// �Զ����еļ���Ԫ������ִ�й�ȱ���
    	while (!queue.isEmpty()) {
            u = queue.poll();// ȡ�����е�ͷ
            w = getFirstNeighbor(u); // �õ���һ���ڽӵ���±�
            while (w != -1) { // ������ڽӶ����ѭ����ֱ��û����Ϊֹ
                if (!isVisited[w]) { // ���wû�����ʹ�
                    //�����һ���ڽӵ�δ����������ʵ�һ���ڽӽڵ�
                    System.out.print(getValueByIndex(w) + " ");
                    isVisited[w] = true;
                    queue.add(w);
                }
        		// ���w����Ѿ������ʹ���Ѱ��u����һ���ڽӶ��㡣ʵ�ֹ�ȱ���
        		w = getNextNeighbor(u, w);
            }
    	}	
    }
    public void breadthFirstSearch() {
    	isVisited = new boolean[vertexList.size()];
        //�������еĽ�㣬����dfs[����]
        for (int i = 0; i < vertexNumber; i++) {
            if (!isVisited[i]) {
            	breadthFirstSearch(isVisited, i);
            }
        }
    }
    
    
    // ��ʾͼ��Ӧ�ľ���
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
