package campuspath.pathfind.algorithm.astar;

import campuspath.pathfind.Path;
import campuspath.pathfind.Pathfinder;
import campuspath.pathfind.function.GoalFunction;
import campuspath.pathfind.traversal.CostMove;
import campuspath.pathfind.traversal.MoveProvider;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @param <T> The node type
 * @author Brady
 */
public final class AStarPathfinder<T extends AStarNode<T>> implements Pathfinder<T> {

    private final Supplier<OpenSet<T>> openSetSupplier;
    private final T start;
    private final MoveProvider<T, CostMove<T>> moves;
    private final GoalFunction<T> goal;

    public AStarPathfinder(Supplier<OpenSet<T>> openSetSupplier, T start, MoveProvider<T, CostMove<T>> moves, GoalFunction<T> goal) {
        this.openSetSupplier = openSetSupplier;
        this.start = start;
        this.moves = moves;
        this.goal = goal;
    }

    @Override
    public Optional<Path<T>> find() {
        // https://en.wikipedia.org/wiki/A*_search_algorithm#Pseudocode

        final var openSet = this.openSetSupplier.get();

        // Initialize the start node with the correct state
        this.start.setCost(null, 0);
        openSet.select(this.start);

        while (!openSet.isEmpty()) {
            // Remove the node with the lowest cost estimate from the set
            var current = openSet.popCheapest();

            // If the goal test passes, then we've found a path and can return successfully
            if (this.goal.test(current)) {
                return Optional.of(Path.assemble(current));
            }

            for (var move : this.moves.getMoves(current)) {
                var neighbor = move.getDestination();
                var tentativeScore = current.getCost() + move.getCost();
                if (tentativeScore < neighbor.getCost()) {
                    // Update the neighbor with the current best path to it
                    neighbor.setCost(current, tentativeScore);
                    // Notify the open set that the node has been selected (See OpenSet#select() documentation)
                    openSet.select(neighbor);
                }
            }
        }
        // If the open set is empty, we couldn't find a path
        return Optional.empty();
    }
}
