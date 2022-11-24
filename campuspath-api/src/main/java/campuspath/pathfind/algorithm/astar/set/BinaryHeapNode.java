package campuspath.pathfind.algorithm.astar.set;

import campuspath.pathfind.algorithm.astar.AStarNode;

/**
 * @param <S> Self-type
 * @author Brady
 */
public interface BinaryHeapNode<S extends BinaryHeapNode<S>> extends AStarNode<S> {

    void setIndex(int index);

    int getIndex();

    boolean isOpen();
}
