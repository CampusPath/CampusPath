package campuspath.pathfind.astar;

import campuspath.pathfind.node.CostNode;

/**
 * @param <S> Self-type
 * @author Brady
 */
public interface AStarNode<S extends AStarNode<S>> extends CostNode<S> {

    /**
     * Sets the currently known, cheapest cost of a path from the start node to this node, and then updates the cost
     * estimate.
     *
     * @param previous The source node, may be {@code null} if this node is the root
     * @param cost     The new cost
     */
    @Override
    void setCost(S previous, double cost);

    /**
     * Returns the heuristic, or the cost estimate to the goal, for this node.
     *
     * @return The heuristic for this node
     */
    double getHeuristic();

    /**
     * Returns the estimated total cost from the start to the goal if the path goes through this node. This is
     * referred to as the {@code fScore} in the wikipedia pseudocode A* implementation.
     *
     * @return The cost estimate for this node
     */
    double getEstimate();
}
