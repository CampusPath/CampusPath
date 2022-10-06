package campuspath.pathfind.function;

import campuspath.pathfind.node.Node;

import java.util.function.ToDoubleFunction;

/**
 * Function which calculates the heuristic, or the cost estimate to the goal, for a given node.
 *
 * @param <T> The node type
 * @author Brady
 */
@FunctionalInterface
public interface HeuristicFunction<T extends Node<T>> extends ToDoubleFunction<T> {

    /**
     * @param node A node
     * @return The cost estimate to the goal
     */
    @Override
    double applyAsDouble(T node);

    static <T extends Node<T>> HeuristicFunction<T> any(HeuristicFunction<T>... heuristics) {
        return switch (heuristics.length) {
            case 0 -> throw new IllegalArgumentException("At least one function must be provided");
            case 1 -> heuristics[0];
            case 2 -> node -> Math.min(heuristics[0].applyAsDouble(node), heuristics[1].applyAsDouble(node));
            default -> node -> {
                double min = Double.MAX_VALUE;
                for (var h : heuristics) {
                    var val = h.applyAsDouble(node);
                    if (val < min) {
                        min = val;
                    }
                }
                return min;
            };
        };
    }
}
