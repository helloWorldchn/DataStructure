package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

// �ڽӾ���
public class AdjacencyMatrixGraph {
	ArrayList<String> vertexList; //�洢���㼯��
	int[][] arcs; // �ڽӾ���
    int vertexNumber; // ͼ�ĵ�ǰ�������Ŀ
    int edgeNumber; // ͼ�ĵ�ǰ�ߵ���Ŀ
	
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

    
    /**
     *  Deep First Search Algorithm: ������������㷨(dfs)
     *  @return: java.util.List<java.lang.String>
     */
    public List<Object> deepFirstSearch() {
        return deepFirstSearch(0);
    }
    public List<Object> deepFirstSearch(int startIndex) {
    	List<Object> dfsList = new ArrayList<Object>();
        boolean[] isVisited = new boolean[vertexList.size()]; // ���������boolean[], ��¼ĳ������Ƿ񱻷���
        //�������еĽ�㣬����dfs[����]
        for (int i = startIndex; i < vertexNumber+startIndex; i++) {
        	int index = i%vertexNumber;
            if (!isVisited[index]) {
            	deepFirstSearch(isVisited, index ,dfsList);
            }
        }
        return dfsList;
    }
    public void deepFirstSearch(boolean[] isVisited, int index, List<Object> dfsList) {
    	dfsList.add(vertexList.get(index)); // �������Ƿ��ʸý��,���
//    	System.out.print(vertexList.get(index) + " "); // �������Ƿ��ʸý��,���
    	isVisited[index] = true; // index�Ѿ�����һ�б����ʹ��ˣ����Ա�Ϊtrue
    	// ��������������ظ��ҵ�һ���ڽӽڵ㣬ֱ���Ҳ�����Ϊֹ
    	int w = getFirstNeighbor(index); // �õ�index����ĵ�һ���ڽӽ����±�
    	while (w != -1) { // ������ڽӶ����ѭ����ֱ��û����Ϊֹ
    		if (!isVisited[w]) { // ���wû�����ʹ�
    			deepFirstSearch(isVisited, w, dfsList);
    		}
    		// ���w����Ѿ������ʹ���˵��һ�������������ɣ�Ѱ����һ���ڽӶ���
    		w = getNextNeighbor(index, w);
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
    	int u; // u������е�ͷ����Ӧ���±ꡣ
    	int w; // w�����ڽӽڵ�
    	Queue<Integer> queue = new ArrayDeque<>(); // ��������
    	bfsList.add(vertexList.get(index));
//    	System.out.print(vertexList.get(index) + " "); // �������Ƿ��ʸý��,���
    	isVisited[index] = true; // index�Ѿ�����һ�б����ʹ��ˣ����Ա�Ϊtrue
    	queue.add(index); // ʹA�������
    	// �Զ����еļ���Ԫ������ִ�й�ȱ���
    	while (!queue.isEmpty()) {
            u = queue.poll();// ȡ�����е�ͷ
            w = getFirstNeighbor(u); // �õ���һ���ڽӵ���±�
            while (w != -1) { // ������ڽӶ����ѭ����ֱ��û����Ϊֹ
                if (!isVisited[w]) { // ���wû�����ʹ�
                    //�����һ���ڽӵ�δ����������ʵ�һ���ڽӽڵ�
                	bfsList.add(getValueByIndex(w));
//                    System.out.print(getValueByIndex(w) + " ");
                    isVisited[w] = true;
                    queue.add(w);
                }
        		// ���w����Ѿ������ʹ���Ѱ��u����һ���ڽӶ��㡣ʵ�ֹ�ȱ���
        		w = getNextNeighbor(u, w);
            }
    	}
    }
    
    // ��ʾͼ��Ӧ�ľ���
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
     *  @descript ��С�������㷨��prim�㷨
     *  @param: graph
     */
    public void prim(int[][] graph) {
        int vertices = graph.length;
        int[] parent = new int[vertices]; // ���ڴ洢��С�������ĸ��ڵ�
        int[] key = new int[vertices]; // ���ڴ洢��������С����������СȨ��
        boolean[] mstSet = new boolean[vertices]; // ���ڼ�¼�����Ƿ��Ѽ�����С������

        Arrays.fill(key, INF);  // ��ʼ�����ж����Ȩ��Ϊ�����
        Arrays.fill(mstSet, false); // ��ʼ�����ж����δ������С������

        key[0] = 0; // ��ʼ�����Ȩ��Ϊ0����������Ϊ��ʼ�ڵ�
        parent[0] = -1;  // ��ʼ�ڵ�û�и��ڵ�
        
        // ���μ���(n-1)�����㵽��С��������
        for (int i = 0; i < vertices - 1; i++) {
            int u = minKey(key, mstSet, vertices); // ѡ��Ȩ����С�Ķ���u������С������
            mstSet[u] = true;
            
            // �������ڶ����Ȩ�غ͸��ڵ���Ϣ
            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        
        // ��ӡ��С�������ıߺ�Ȩ��
        System.out.println("Edge" + "\t" + "Weight");
        for (int i = 1; i < graph.length; i++) {
            if (parent[i] != -1) { // �����ӡ��ʼ�ڵ�ı�
            	System.out.println(vertexList.get(parent[i]) + " -> " + vertexList.get(i) + "\t" + graph[parent[i]][i]);
            }
        }
    }
    // Ѱ��Ȩ����С��δ������С�������Ķ���
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
     *  @descript ��С�������㷨��kruskal�㷨
     *  @param graph
     */
    public void kruskal(int[][] graph) {
        int vertices = graph.length;
        List<Edge> edges = new ArrayList<>(); // ���ڴ洢ͼ�е����б�
        // ��ͼ�ıߴ洢��edges�б���
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (graph[i][j] != 0) {
                	Edge edge = new Edge(i, j, graph[i][j]);  // �ߵ���㡢�յ��Ȩֵ
                    edges.add(edge);
                }
            }
        }

        // ��Ȩ�ضԱ߽�������
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));
        int[] parent = new int[vertices]; // ���ڴ洢ÿ������ĸ��ڵ�
        Arrays.fill(parent, -1);

        List<Edge> mst = new ArrayList<>();  // ���ڴ洢��С�������ı�
        int edgeCount = 0;
        // �������б�
        for (Edge edge : edges) {
            int x = find(parent, edge.src); // ���ұߵ����ĸ��ڵ�
            int y = find(parent, edge.dest); // ���ұߵ��յ�ĸ��ڵ�

            // ����ߵ��������㲻��ͬһ����ͨ�����У��������С������
            if (x != y) {
                mst.add(edge);  // ���߼�����С������
                union(parent, x, y); // �ϲ������������ͨ����
                edgeCount++;  // ���ӱߵ�����
                if (edgeCount == vertices - 1) { // ���ҵ�n-1���ߣ����˳�ѭ��
                    break;
                }
            }
        }
        // �����С�������ıߺ�Ȩ��
        System.out.println("Edge" + "\t" + "Weight");
        for (Edge edge : mst) {
        	System.out.println(vertexList.get(edge.src) + " -> " + vertexList.get(edge.dest) + "\t" +  edge.weight);
        }
    }
    // ���Ҷ���i�ĸ��ڵ�
    private int find(int[] parent, int i) {
        if (parent[i] == -1) {
            return i; // �������i�ĸ��ڵ�Ϊ-1����ʾi�Ǹ��ڵ�
        }
        return find(parent, parent[i]); // �ݹ����i�ĸ��ڵ�
    }
    // �ϲ���������x��y����ͨ����
    private void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);// ���Ҷ���x�ĸ��ڵ�
        int rootY = find(parent, y);// ���Ҷ���y�ĸ��ڵ�
        parent[rootX] = rootY; // ������x�ĸ��ڵ�ĸ��ڵ���Ϊ����y�ĸ��ڵ�
    }
    // �������ʾ�ߵ���Ϣ
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
     *  @descript ���·���㷨��dijkstra�㷨
     *  @param start
     *  @return int[]
     */
    public int[] dijkstra(int start) {
    	if (start > vertexList.size() || start < 0) {
            System.out.println("Start vertex not found.");
            return new int[] {};
		}
        // ��ʼ��
        boolean[] visited = new boolean[vertexList.size()]; // ���������֪ʵ�����·��ֵ�Ķ���,true������֪�����·��
        int[] path = new int[vertexList.size()]; // ��ʾ��Դ�㵽����֮������·����ǰ�����
        int[] dist = new int[vertexList.size()]; // ��ʾ��Դ�㵽����֮��Ļ��ϵ�Ȩֵ
        for (int i = 0; i < vertexList.size(); i++) {
            visited[i] = false; // ����i�����·����û��ȡ����
            path[i] = 0;  // ����i��ǰ������Ϊ0��
            dist[i] = arcs[start][i]==0 ? INF : arcs[start][i];  // ����i�����·��Ϊ��㵽����i��Ȩֵ
        }
        visited[start] = true;
        dist[start] = 0;

        // �����������·��
        int k=0;
        for (int i = 1; i < vertexList.size(); i++) {
            // Ѱ�ҵ�ǰ��С��·����
            // ������δ��ȡ���·���Ķ����У��ҵ���vs����Ķ���(k)��
            int min = INF;
            for (int j = 0; j < vertexList.size(); j++) {
                if (!visited[j] && dist[j]<min) {
                    min = dist[j];
                    k = j;
                }
            }
            visited[k] = true; // ��k���������ҵ����·��

            // �������·��dist��ǰ������path
            for (int j = 0; j < vertexList.size(); j++) {
                if (!visited[j]&& arcs[k][j] != 0 && min!=INF && (min + arcs[k][j] < dist[j]) ) {
                    dist[j] = (arcs[k][j]==INF ? INF : (min + arcs[k][j]));
                    path[j] = k;
                }
            }
        }
        // ��ӡ���·�������·������
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
     *  @descript ���·���㷨��Floyd�㷨
     *  @return int[][]
     */
    public int[][] floyd() {
        // ��ʼ��
    	int[][] dist = new int[vertexNumber][vertexNumber]; // ���� ��Ϊ����
    	int[][] path = new int[vertexNumber][vertexNumber]; // ǰ�����
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
        // ��̬�滮˼���������
        for (int k = 0; k < vertexNumber; k++) {
            for (int i = 0; i < vertexNumber; i++) {
                for (int j = 0; j < vertexNumber; j++) {
                	if (i!=j && dist[i][k] != INF && dist[k][j] != INF) {
                        path[i][j] = dist[i][j]>dist[i][k] + dist[k][j]? path[k][j]: path[i][j];
                    	dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]); // ���µ��ƹ�ʽ
					}
                }
            }
        }
        // ��ӡ
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
