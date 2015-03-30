package engine.pathgraphsearch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class BFS {
	public static Map<GraphNode,GraphNode> completeBFS(List<GraphNode> endNodes) {
		List<Map<GraphNode,Edge>> gradients = endNodes.stream().map(end -> completeBFS(end)).collect(Collectors.toList());
		Set<GraphNode> graphNodeSet = new HashSet<>();
		gradients.stream().map(map -> map.keySet()).forEach(keyset -> graphNodeSet.addAll(keyset));
		Map<GraphNode,GraphNode> compositeGradient = new HashMap<>();
		graphNodeSet.forEach(node -> 
			compositeGradient.put(node, gradients.stream()
					.map(map -> map.get(node)).max((a,b) -> Double.compare(a.getCost(), b.getCost())).get().getNeighbor()));
		return compositeGradient;
	}

	public static Map<GraphNode,Edge> completeBFS(GraphNode endNode) {
		if (endNode == null) {
		}
		Queue<GraphNode> queue = new LinkedList<>();
		endNode.setVisited(true);
		queue.add(endNode);

		Map<GraphNode, Edge> gradientMap = new HashMap<>();
		while (!queue.isEmpty()) {
			GraphNode node = queue.remove();
			for (Edge e : node.getEdges()){
				GraphNode neighbor = e.getNeighbor();
				if (!neighbor.visited()) {
					gradientMap.put(neighbor, new Edge(node, gradientMap.get(node).getCost() + e.getCost()));
					queue.add(neighbor);
					neighbor.setVisited(true);
				}
			} //TODO support uniform cost search instead
		}
		return gradientMap;
	}
}
