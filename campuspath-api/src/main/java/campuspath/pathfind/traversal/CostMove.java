package campuspath.pathfind.traversal;

import campuspath.pathfind.node.CostNode;

/**
 * @author Brady
 */
public interface CostMove<T extends CostNode<T>> extends Move<T> {

    /**
     * @return The cost of moving from the source node to the destination node
     */
    double getCost();
}
