package campuspath.pathfind.astar;

import campuspath.pathfind.node.Node;

/**
 * Interface for an A* open set. This is generally implemented as a min-heap or priority queue, but any duplicate
 * checking collection should work (albeit with varying time complexities for the operations).
 *
 * @param <T> The node type
 * @author Brady
 */
public interface OpenSet<T extends AStarNode<T>> {

    /**
     * Notifies this set of a {@link Node} being selected as the current best neighboring node, potentially requiring
     * an insertion of the {@link Node} if it is not already contained within the set, or an update if the underlying
     * data structure is responsive to the cost estimate.
     *
     * @param node The selected node
     */
    void select(T node);

    /**
     * Removes the {@link Node} with the lowest estimated total cost from the set and returns it.
     *
     * @return The cheapest node
     * @see AStarNode#getEstimate()
     */
    T popCheapest();

    /**
     * @return {@code true} if this set contains no nodes
     */
    boolean isEmpty();
}
