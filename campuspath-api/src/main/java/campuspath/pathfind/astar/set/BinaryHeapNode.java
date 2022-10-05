package campuspath.pathfind.astar.set;

import campuspath.pathfind.astar.AStarNode;

/**
 * @param <S> Self-type
 * @author Brady
 */
public interface BinaryHeapNode<S extends BinaryHeapNode<S>> extends AStarNode<S> {

    void setIndex(int index);

    int getIndex();

    void setOpen(boolean open);

    boolean isOpen();
}
