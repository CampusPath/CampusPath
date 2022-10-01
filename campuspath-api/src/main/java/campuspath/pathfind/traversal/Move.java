package campuspath.pathfind.traversal;

import campuspath.pathfind.node.Node;

/**
 * @author Brady
 */
public interface Move<T extends Node<T>> {

    /**
     * @return The source node
     */
    T getSource();

    /**
     * @return The destination node
     */
    T getDestination();
}
