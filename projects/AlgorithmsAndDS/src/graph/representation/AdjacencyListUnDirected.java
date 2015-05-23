package graph.representation;
import java.util.*;

/*
 * Refer - http://www.geeksforgeeks.org/graph-and-its-representations/
 * 
 */
public class AdjacencyListUnDirected {
	public Object[] vertices;
	public int vertexCount;
	public boolean isCyclic;
	List<Integer> visitedNodes = new ArrayList<Integer>();
	HashMap<Integer, Boolean> visitedMap = new HashMap<Integer, Boolean>();
	List<String> visitedEdges = new ArrayList<String>();
	//These are for AP
	private int[] dfsNum;
	private int[] low;
	private int dfsCount = 0;
	
	public AdjacencyListUnDirected(int vertexCount) {
		super();
		this.vertexCount = vertexCount;
		vertices = new Object[vertexCount];
		dfsNum = new int[vertexCount];
		low =  new int[vertexCount];
	}

	public void addEdge(int i, int j) throws Exception {
		if (i < 0 || j < 0 || i >= vertexCount || j >= vertexCount)
			throw new Exception("i, j out of range");
		if (vertices[i] == null) {
			List<Integer> edges = new LinkedList<Integer>();
			edges.add(j);
			vertices[i] = edges;
		} else {
			((LinkedList<Integer>) vertices[i]).add(j);
		}
		// Below is only for undirected graph
		if (vertices[j] == null) {
			List<Integer> edges = new LinkedList<Integer>();
			edges.add(i);
			vertices[j] = edges;
		} else {
			((LinkedList<Integer>) vertices[j]).add(i);
		}
	}

	public void printAdjacencyList() {
		int count = 0;
		for (Object vertex : vertices) {
			System.out.println("\nAdjacency list for vertex " + count++);
			for (Integer edge : (LinkedList<Integer>) vertex) {
				System.out.print(edge + ">");
			}
		}
	}

	/*
	 * Refer http://ideone.com/U3pGNE
	 */
	public void doDfs(int n) {
		if (visitedMap.get(n) == null) {
			// This vertex is not visited - so iterate through the list
			System.out.print(n + ">");
			visitedMap.put(n, true);
			if (vertices[n] != null)
				for (Integer edge : (LinkedList<Integer>) vertices[n]) {
					doDfs(edge);
				}
		}
	}
	
	/**
	 * Refer http://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
	 * and also the Karamunchi book
	 */
	public void findArPoints(int u){
		//Set low and dfsnum for this vertex
		dfsCount++;
		dfsNum[u] = dfsCount;
		low[u] = dfsCount;
		int rootCount = dfsCount; //This keeps track of subtrees for u = 0
		visitedMap.put(u, true);
		
		//Repeat DFS for every adjacent vertex of u
		if (vertices[u] != null){
			for (Integer v : (LinkedList<Integer>) vertices[u]) {
				if(visitedMap.get(v) == null){
					findArPoints(v);
					if(u==0 && rootCount > 1){
						System.out.println("AP>"+u);
					}else if(u == 0){
						rootCount++;
					}
					if(low[v] >= dfsNum[u] && u!=0)
						System.out.println("AP>"+u);
					//We have completed DFS for all the child nodes of v - so set the low for this vertex
					//u should have the lowest low[] of its child elements since all children are reachable from u
					low[u] = low[v] < low[u] ? low[v] : low[u];
				}else{
					//Its a back edge - so set the low for this vertex
					low[u] = dfsNum[v] < low[u] ? dfsNum[v] : low[u];
				}
			}
			//We are done processing u
		}
	}
	
	public void doBFS(int n) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(n);

		while (!q.isEmpty()) {
			int vertex = q.poll();
			if (visitedMap.get(vertex) == null) {
				System.out.print(vertex + ">");
				visitedMap.put(vertex, true);
			}
			if (vertices[vertex] != null)
				for (Integer edge : (LinkedList<Integer>) vertices[vertex]) {
					if (visitedMap.get(edge) == null) {
						q.add(edge);
					}
				}
		}
	}

	/**
	 * 
	 * http://www.geeksforgeeks.org/detect-cycle-undirected-graph/ Like directed
	 * graphs, we can use DFS to detect cycle in an undirected graph in O(V+E)
	 * time. We do a DFS traversal of the given graph. For every visited vertex
	 * ‘v’, if there is an adjacent ‘u’ such that u is already visited and u is
	 * not parent of v, then there is a cycle in graph. The assumption of this
	 * approach is that there are no parallel edges between any two vertices.
	 * 
	 * https://www.me.utexas.edu/~bard/IP/Handouts/cycles.pdf
	 */
	public void isCyclic(int n, int parent) {
		if (visitedMap.get(n) == null) {
			// This vertex is not visited - so iterate through the list
			//System.out.print(n + ">");
			visitedNodes.add(n);
			visitedMap.put(n, true);
			visitedEdges.add(parent+""+n); //Keep track of visited edge
			if (vertices[n] != null)
				for (Integer edge : (LinkedList<Integer>) vertices[n]) {
					isCyclic(edge, n);
				}
		} else {
			if(!visitedEdges.contains(n+""+parent)){ //Check if its the visited edge being visited in reverse manner
				isCyclic = true;
			}
		}
	}

	private boolean isCycleNode(int n, int parent) {
		System.out.println(n+"-"+parent);
		if (vertices[n] != null)
			for (Integer edge : (LinkedList<Integer>) vertices[n]) {
				if (visitedMap.get(edge) != null) {
					if (visitedNodes.indexOf(edge) != visitedNodes.indexOf(n)-1) {
						return true;
					}

				}
			}
		return false;
	}

	public static void main(String[] args) throws Exception {
			AdjacencyListUnDirected g1 = new AdjacencyListUnDirected(5);
		    g1.addEdge(1, 0);
		    g1.addEdge(0, 2);
		    g1.addEdge(2, 0);
		    g1.addEdge(0, 3);
		    g1.addEdge(3, 4);
			g1.isCyclic(0,0);
			System.out.println(g1.isCyclic);
			
			AdjacencyListUnDirected g2 = new AdjacencyListUnDirected(3);
			g2.addEdge(0, 1);
		    g2.addEdge(1, 2);
			g2.isCyclic(0,0);
			System.out.println(g2.isCyclic);
			
			AdjacencyListUnDirected g3 = new AdjacencyListUnDirected(5);
		    g3.addEdge(0, 1);
		    g3.addEdge(0, 2);
		    g3.addEdge(1, 2);
		    g3.addEdge(2, 3);
		    g3.addEdge(0, 4);
			g3.findArPoints(0);
	}
}
