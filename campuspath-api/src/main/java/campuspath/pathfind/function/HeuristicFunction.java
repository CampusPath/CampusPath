package campuspath.pathfind.function;

import campuspath.pathfind.node.Node;

import java.util.function.ToDoubleFunction;

/**
 * Function which calculates the heuristic, or the cost estimate to the goal, for a given node.
 *
 * @param <T> The node type
 */
@FunctionalInterface
public interface HeuristicFunction<T extends Node<T>> extends ToDoubleFunction<T> {

    /**
     * @param node A node
     * @return The cost estimate to the goal
     */
    @Override
    double applyAsDouble(T node);
}
