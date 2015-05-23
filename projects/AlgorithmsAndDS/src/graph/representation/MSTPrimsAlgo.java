package graph.representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MSTPrimsAlgo {
	public Object[] vertices;
	public int vertexCount;
	
	//Need below DS for Prims algorithm
	int[] verticesWeights;
	int[][] edgeWeights;
	int[] verticesWeightFrom;
	Map<Integer,Integer> mstVertices = new HashMap<Integer,Integer>();
	
	public MSTPrimsAlgo(int vertexCount) {
		super();
		this.vertexCount = vertexCount;
		vertices = new Object[vertexCount];
		verticesWeights = new int[vertexCount];
		edgeWeights = new int[vertexCount][vertexCount];
		verticesWeightFrom = new int[vertexCount];
	}

	public void addEdge(int i, int j, int weight) throws Exception {
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
		edgeWeights[i][j] = weight;
		edgeWeights[j][i] = weight;
	}
	
	public void doMST(){
		for(int i=0;i<vertexCount;i++){
			verticesWeights[i] = Integer.MAX_VALUE;
		}
		verticesWeights[0] = 0; //Set all to max weight except first
		while(mstVertices.size() != vertexCount){
			doMST(findMinimumWeightVertex());
		}
		System.out.println("MST is as follows:");
		for(Entry<Integer, Integer> entry: mstVertices.entrySet()){
			System.out.println(verticesWeightFrom[entry.getKey()]+">"+entry.getKey()+" weight-"+entry.getValue());
		}
	}
	
	private void doMST(int v){
		mstVertices.put(v, verticesWeights[v]);
		verticesWeights[v] = Integer.MAX_VALUE; //Reset to make sure it doesnt get picked again
		if(vertices[v] != null)
			for(Integer adj : (LinkedList<Integer>) vertices[v]){
				//Set the weights for all unlisted adjacent vertexes
				if(mstVertices.get(adj) == null){
					if(edgeWeights[v][adj] < verticesWeights[adj]){
						verticesWeights[adj] = edgeWeights[v][adj];
						verticesWeightFrom[adj] = v; //Keep track of source vertex for this weight
					}
				}
			}
	}
	
	private int findMinimumWeightVertex() {
		int minimum = Integer.MAX_VALUE;
		for(int i=0;i<vertexCount;i++){
			if(verticesWeights[i] < minimum)
				minimum = i;
		}
		return minimum;
	}
	
	private int findMinimumWeightVertexForSPT() {
		int minimum = Integer.MAX_VALUE;
		for(int i=0;i<vertexCount;i++){
			if(verticesWeights[i] < minimum)
				minimum = i;
		}
		return minimum;
	}
	
	/**
	 * Dijkstra's shortest path algoritm - very similar to Prim's algo
	 */
	public void doSPT(){
		for(int i=0;i<vertexCount;i++){
			verticesWeights[i] = Integer.MAX_VALUE;
		}
		verticesWeights[0] = 0; //Set all to max weight except first
		while(mstVertices.size() != vertexCount){
			doSPT(findMinimumWeightVertexForSPT());
		}
		System.out.println("SPT is as follows:");
		for(Entry<Integer, Integer> entry: mstVertices.entrySet()){
			System.out.println(verticesWeightFrom[entry.getKey()]+">"+entry.getKey()+" weight-"+entry.getValue());
		}
	}
	
	private void doSPT(int v){
		mstVertices.put(v, verticesWeights[v]);
		verticesWeights[v] = Integer.MAX_VALUE; //Reset to make sure it doesnt get picked again
		if(vertices[v] != null)
			for(Integer adj : (LinkedList<Integer>) vertices[v]){
				//Set the weights for all unlisted adjacent vertexes
				if(mstVertices.get(adj) == null){
					if(edgeWeights[v][adj] + mstVertices.get(v) < verticesWeights[adj]){
						verticesWeights[adj] = edgeWeights[v][adj] +  mstVertices.get(v);
						verticesWeightFrom[adj] = v; //Keep track of source vertex for this weight
					}
				}
			}
	}
	
	public static void main(String[] args) throws Exception {
		MSTPrimsAlgo g = new MSTPrimsAlgo(5);
		g.addEdge(0,1 ,2 );
		g.addEdge(1,2 ,3 );
		g.addEdge(2, 4,7 );
		g.addEdge(1, 4, 5);
		g.addEdge(1,3 ,8 );
		g.addEdge(3,4 ,9 );
		g.addEdge(0,3 ,6 );
		g.doMST();
		
		MSTPrimsAlgo g2 = new MSTPrimsAlgo(5);
		g2.addEdge(0,1 ,20 );
		g2.addEdge(0,2 ,10 );
		g2.addEdge(2, 3,5 );
		g2.addEdge(3, 4, 10);
		g2.addEdge(1,4 ,10 );
		g2.addEdge(0,4 ,50 );
		g2.addEdge(1,3 ,20 );
		g2.doSPT();
	}
}
