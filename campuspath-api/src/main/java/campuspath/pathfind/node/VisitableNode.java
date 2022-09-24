package campuspath.pathfind.node;

/**
 * A node which may be visited once during graph traversal, indicated via a call to {@link #visit()}, which can then
 * later be queried with {@link #visited()}.
 *
 * @param <S> Self-type
 * @author Brady
 */
public interface VisitableNode<S extends VisitableNode<S>> extends Node<S> {

    /**
     * Marks this node as visited
     */
    void visit();

    /**
     * @return Whether this node has been visited
     */
    boolean visited();
}
