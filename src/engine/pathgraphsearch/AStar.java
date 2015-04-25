package engine.pathgraphsearch;

import java.util.List;
import java.util.PriorityQueue;


public class AStar {
    public static List<GraphNodeID> run (GraphNode start,
                                         List<GraphNode> ends,
                                         Heuristic heuristicCalculator) {
        PriorityQueue<TreeNode> fringe =
                new PriorityQueue<TreeNode>(
                                            100,
                                            (a, b) -> a.getCost() + a.getHeuristic() >= b.getCost() +
                                                                                        b.getHeuristic() ? 1
                                                                                                        : -1);
        List<GraphNodeID> finalPath;
        TreeNode treeStart = new TreeNode(start, heuristicCalculator);
        fringe.add(treeStart);
        while (true) {
            TreeNode current = fringe.poll();
            if (current == null) {
                throw new OutOfMemoryError();
            }
            current.getGraphNode().setVisited(true);
            if (isSolutionNode(current)) {
                finalPath = current.getPathHistory();
                break;
            }
            for (TreeNode neighbor : current.calculateNeighbors()) {
                if (!neighbor.getGraphNode().visited()) {
                    fringe.add(neighbor);
                }
            }
        }
        return finalPath;
    }

    private static boolean isSolutionNode (TreeNode n) {
        return n.getHeuristic() <= .01;
    }
}
