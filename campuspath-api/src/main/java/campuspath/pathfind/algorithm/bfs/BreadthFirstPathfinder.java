package campuspath.pathfind.algorithm.bfs;

import campuspath.pathfind.Path;
import campuspath.pathfind.Pathfinder;
import campuspath.pathfind.function.GoalFunction;
import campuspath.pathfind.node.VisitableNode;
import campuspath.pathfind.traversal.Move;
import campuspath.pathfind.traversal.MoveProvider;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

/**
 * @author Brady
 */
public final class BreadthFirstPathfinder<T extends VisitableNode<T>> implements Pathfinder<T> {

    private final T start;
    private final MoveProvider<T, Move<T>> moves;
    private final GoalFunction<T> goal;

    public BreadthFirstPathfinder(T start, MoveProvider<T, Move<T>> moves, GoalFunction<T> goal) {
        this.start = start;
        this.moves = moves;
        this.goal = goal;
    }

    @Override
    public Optional<Path<T>> find() {
        Deque<T> queue = new ArrayDeque<>();
        queue.add(this.start);

        while (!queue.isEmpty()) {
            T node = queue.pop();

            if (this.goal.test(node)) {
                return Optional.of(Path.assemble(node));
            }

            for (var move : this.moves.getMoves(node)) {
                T adjacent = move.getDestination();
                if (!adjacent.visited()) {
                    adjacent.visit();
                    adjacent.setPrevious(node);
                    queue.add(adjacent);
                }
            }
        }

        return Optional.empty();
    }
}
