package campuspath.pathfind.algorithm.astar;

import campuspath.pathfind.AbstractPathfinder;
import campuspath.pathfind.Path;
import campuspath.pathfind.function.GoalFunction;
import campuspath.pathfind.traversal.CostMove;
import campuspath.pathfind.traversal.MoveProvider;

import java.util.Optional;

/**
 * @param <T> The node type
 * @author Brady
 */
public final class AStarPathfinder<T extends AStarNode<T>> extends AbstractPathfinder<T, CostMove<T>> {

    private final OpenSet<T> openSet;

    public AStarPathfinder(OpenSet<T> openSet, T start, MoveProvider<T, CostMove<T>> moves, GoalFunction<T> goal) {
        super(start, moves, goal);
        this.openSet = openSet;
    }

    @Override
    public Optional<Path<T>> find() {
        // https://en.wikipedia.org/wiki/A*_search_algorithm#Pseudocode

        // Initialize the start node with the correct state
        this.start.setCost(null, 0);
        this.openSet.select(this.start);

        while (!this.openSet.isEmpty()) {
            // Remove the node with the lowest cost estimate from the set
            var current = this.openSet.popCheapest();

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
                    this.openSet.select(neighbor);
                }
            }
        }
        // If the open set is empty, we couldn't find a path
        return Optional.empty();
    }
}
