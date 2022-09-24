package campuspath.pathfind.node;

/**
 * @param <S> Self-type
 * @author Brady
 */
public interface Node<S extends Node<S>> {

    /**
     * Sets the node which immediately connects to this one along the current graph traversal
     *
     * @param previous The new previous node
     */
    void setPrevious(S previous);

    /**
     * @return The previous node along the current graph traversal
     */
    S getPrevious();
}
