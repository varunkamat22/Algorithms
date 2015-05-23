package graph.representation;

import java.util.*;

/*
 * Refer - http://www.geeksforgeeks.org/graph-and-its-representations/
 * 
 */
public class AdjacencyListDirected {
	public Object[] vertices;
	public int vertexCount;
	public boolean isCyclic;
	List<Integer> visitedNodes= new ArrayList<Integer>();
	HashMap<Integer, Boolean> visitedMap = new HashMap<Integer, Boolean>();
	
	public AdjacencyListDirected(int vertexCount) {
		super();
		this.vertexCount = vertexCount;
		vertices = new Object[vertexCount];
	}
	
	public void addEdge(int i, int j) throws Exception{
		if(i < 0 || j < 0 || i >= vertexCount || j >= vertexCount)
			throw new Exception("i, j out of range");
		if(vertices[i] == null){
			List<Integer> edges = new LinkedList<Integer>();
			edges.add(j);
			vertices[i] = edges;
		}else{
			((LinkedList<Integer>)vertices[i]).add(j);
		}
		//Below is only for undirected graph
		/*if(vertices[j] == null){
			List<Integer> edges = new LinkedList<Integer>();
			edges.add(i);
			vertices[j] = edges;
		}else{
			((LinkedList<Integer>)vertices[j]).add(i);
		}*/
	}
	
	public void printAdjacencyList(){
		int count = 0;
		for(Object vertex : vertices){
			System.out.println("\nAdjacency list for vertex "+count++);
			for(Integer edge : (LinkedList<Integer>)vertex){
				System.out.print(edge+">");
			}
		}
	}
	
	/*
	 * Refer http://ideone.com/U3pGNE
	 */
	public void doDfs(int n){
		if(visitedMap.get(n) == null){
			//This vertex is not visited - so iterate through the list
			System.out.print(n+">");
			visitedMap.put(n, true);
			if(vertices[n] != null)
				for(Integer edge : (LinkedList<Integer>)vertices[n]){
					doDfs(edge);
				}
		}
	}
	
	public void doBFS(int n){
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(n);
		
		while(!q.isEmpty()){
			int vertex = q.poll();
			if(visitedMap.get(vertex) == null){
				System.out.print(vertex+">");
				visitedMap.put(vertex,true);
			}
			if(vertices[vertex] != null)
				for(Integer edge : (LinkedList<Integer>)vertices[vertex]){
					if(visitedMap.get(edge) == null){
						q.add(edge);
					}
				}
		}
	}
	
	public void isCyclic(int n){
		if(visitedMap.get(n) == null){
			//This vertex is not visited - so iterate through the list
			System.out.print(n+">");
			visitedNodes.add(n);
			visitedMap.put(n, true);
			if(vertices[n] != null)
				for(Integer edge : (LinkedList<Integer>)vertices[n]){
					isCyclic(edge);
				}
		}else{
			if(visitedNodes.contains(n))
				isCyclic = true;
		}
	}
	
	public static void main(String[] args) throws Exception {
		AdjacencyListDirected g = new AdjacencyListDirected(4);
		g.addEdge(0, 1);
	    g.addEdge(1, 2);
	    g.addEdge(1, 3);
	    g.addEdge(2, 0); // causes a cycle
	    g.addEdge(3, 2); // causes a cycle 
		g.isCyclic(0);
		System.out.println(g.isCyclic);
		//System.out.println(g.isCyclic);
		
		/*System.out.println("-----------");
		AdjacencyListUnDirected g2 = new AdjacencyListUnDirected(4);
		g2.addEdge(0, 1);
	    g2.addEdge(0, 2);
	    g2.addEdge(1, 2);
	    g2.addEdge(2, 0);
	    g2.addEdge(2, 1);
	    g2.addEdge(2, 3);
	    g2.addEdge(3, 3);
		g2.doBFS(0);*/
		
	}
}
