package engine.pathgraphsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Graph {
	private List<GraphNode> myNodes;
	private Map<GraphNodeID,GraphNode> myNodeMap;
	
	public Graph(){
		myNodes = new ArrayList<>();
	}
	
	public void addNode(GraphNode node){
		myNodes.add(node);
		myNodeMap.put(node.getID(), node);
	}
	
	public List<GraphNode> shortestPath(GraphNodeID start, List<GraphNodeID> endNodes, Heuristic h){
		List<GraphNodeID> path = AStar.run(myNodeMap.get(start), endNodes.stream().map(n -> myNodeMap.get(n)).collect(Collectors.toList()), h);
		return path.stream().map(id -> myNodeMap.get(id)).collect(Collectors.toList());
	}
}
