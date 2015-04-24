package engine.pathgraphsearch;

import java.util.LinkedList;
import java.util.List;


public class TreeNode {
    private double myCost;
    private double myHeuristic;
    private GraphNode myNode;
    private List<GraphNodeID> myPathHistory;
    private static Heuristic myHeuristicCalculator;

    public TreeNode (GraphNode start, Heuristic hCalc) {
        myCost = 0;
        myNode = start;
        myPathHistory = new LinkedList<>();
        myPathHistory.add(start.getID());
        myHeuristicCalculator = hCalc;
        myHeuristic = hCalc.calculateHeuristic(start);
    }

    private TreeNode (TreeNode parent, Edge traversedEdge) {
        myCost = parent.getCost() + traversedEdge.getCost();
        myNode = traversedEdge.getNeighbor();
        myPathHistory = new LinkedList<>(parent.getPathHistory());
        myPathHistory.add(myNode.getID());
        myHeuristic = myHeuristicCalculator.calculateHeuristic(traversedEdge.getNeighbor());
    }

    public List<GraphNodeID> getPathHistory () {
        return myPathHistory;
    }

    public double getCost () {
        return myCost;
    }

    public double getHeuristic () {
        return myHeuristic;
    }

    public GraphNode getGraphNode () {
        return myNode;
    }

    public List<TreeNode> calculateNeighbors () {
        List<TreeNode> ret = new LinkedList<>();
        for (Edge e : myNode.getEdges()) {
            ret.add(new TreeNode(this, e));
        }
        return ret;
    }
}
