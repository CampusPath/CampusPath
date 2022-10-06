package campuspath.pathfind;

import campuspath.pathfind.function.GoalFunction;
import campuspath.pathfind.node.Node;
import campuspath.pathfind.traversal.Move;
import campuspath.pathfind.traversal.MoveProvider;

/**
 * @author Brady
 */
public abstract class AbstractPathfinder<T extends Node<T>, M extends Move<T>> implements Pathfinder<T> {

    protected final T start;
    protected final MoveProvider<T, M> moves;
    protected final GoalFunction<T> goal;

    public AbstractPathfinder(T start, MoveProvider<T, M> moves, GoalFunction<T> goal) {
        this.start = start;
        this.moves = moves;
        this.goal = goal;
    }
}
