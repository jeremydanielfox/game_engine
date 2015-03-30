package engine.pathgraphsearch;

public interface Heuristic {
	public double calculateHeuristic(GraphNode node);
	public void setGoalLocations(GraphNode... endNodes);
}
