package graph.representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author vakamat
 * Refer - http://www.geeksforgeeks.org/topological-sorting/
 * 
 * In DFS - we print the vertex then call DFS for all the adjacent vertices
 * In TS - we call TS on all adjacent nodes then push the vertex on stack
 * which means when we push a vertex to stack we have already pushed all the vertices reachable through that vertex.
 */
public class TopologicalSort {
	public Object[] vertices;
	public int vertexCount;
	public boolean isCyclic;
	List<Integer> visitedNodes= new ArrayList<Integer>();
	HashMap<Integer, Boolean> visitedMap = new HashMap<Integer, Boolean>();
	
	public TopologicalSort(int vertexCount) {
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
	
	public void tSort(){
		Stack<Integer> s = new Stack<Integer>(); 
		tSorter(0,s);
		System.out.println("Topo sort----");
		while(!s.isEmpty())
			System.out.print(">"+s.pop());
	}
	
	public void tSorter(int i, Stack<Integer> s){
		if(visitedMap.get(i) == null){
			//unvisited node
			visitedMap.put(i, true);
			if(vertices[i] != null)
			for(Integer edge : (LinkedList<Integer>)vertices[i])
				tSorter(edge, s);
			s.push(i);
		}
	}
	
	public static void main(String[] args) throws Exception {
		TopologicalSort t = new TopologicalSort(4);
		t.addEdge(0, 1);
		t.addEdge(0, 3);
		t.addEdge(1, 3);
		t.addEdge(1, 2);
		t.addEdge(3,2 );
		t.tSort();
	}
}
