package campuspath.pathfind.node;

/**
 * A node which has a cost
 *
 * @param <S> Self-type
 * @author Brady
 */
public interface CostNode<S extends CostNode<S>> extends Node<S> {

    /**
     * Sets the currently known, cheapest cost of a path from the start node to this node.
     *
     * @param previous The source node, may be {@code null} if this node is the root
     * @param cost     The new cost
     */
    void setCost(S previous, double cost);

    /**
     * Returns the cost of the cheapest path from the start node to this node which is currently known. This is
     * referred to as the {@code gScore} in the wikipedia pseudocode A* implementation.
     *
     * @return The cost to this node
     */
    double getCost();
}
