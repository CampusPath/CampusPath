package campuspath.pathfind.algorithm.dfs;

import campuspath.pathfind.AbstractPathfinder;
import campuspath.pathfind.Path;
import campuspath.pathfind.function.GoalFunction;
import campuspath.pathfind.node.VisitableNode;
import campuspath.pathfind.traversal.Move;
import campuspath.pathfind.traversal.MoveProvider;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

/**
 * @param <T> The node type
 * @author Brady
 */
public final class DepthFirstPathfinder<T extends VisitableNode<T>> extends AbstractPathfinder<T, Move<T>> {

    public DepthFirstPathfinder(T start, MoveProvider<T, Move<T>> moves, GoalFunction<T> goal) {
        super(start, moves, goal);
    }

    @Override
    public Optional<Path<T>> find() {
        Deque<T> deque = new ArrayDeque<>();
        deque.add(this.start);

        while (!deque.isEmpty()) {
            T node = deque.pop();

            if (this.goal.test(node)) {
                return Optional.of(Path.assemble(node));
            }

            for (var move : this.moves.getMoves(node)) {
                T adjacent = move.getDestination();
                if (!adjacent.visited()) {
                    adjacent.visit();
                    adjacent.setPrevious(node);
                    deque.add(adjacent);
                }
            }
        }

        return Optional.empty();
    }
}
