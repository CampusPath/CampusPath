package campuspath.pathfind.traversal;

import campuspath.pathfind.node.Node;

/**
 * @author Brady
 */
@FunctionalInterface
public interface MoveProvider<T extends Node<T>, M extends Move<T>> {

    /**
     * Returns an array of the available moves from the specified source node.
     *
     * @param node The source node
     * @return The available moves
     */
    M[] getMoves(T node);
}
