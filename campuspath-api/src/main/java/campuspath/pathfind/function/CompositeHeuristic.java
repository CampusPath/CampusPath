package campuspath.pathfind.function;

import campuspath.pathfind.node.Node;

/**
 * @param <T> The node type
 * @author Brady
 */
public final class CompositeHeuristic<T extends Node<T>> implements HeuristicFunction<T> {

    private final HeuristicFunction<T>[] functions;

    @SafeVarargs
    private CompositeHeuristic(HeuristicFunction<T>... functions) {
        this.functions = functions;
    }

    @Override
    public double applyAsDouble(T node) {
        double min = Double.MAX_VALUE;
        for (var function : this.functions) {
            var heuristic = function.applyAsDouble(node);
            if (heuristic < min) {
                min = heuristic;
            }
        }
        return min;
    }

    @SafeVarargs
    public static <T extends Node<T>> HeuristicFunction<T> of(HeuristicFunction<T>... functions) {
        return switch (functions.length) {
            case 0 -> throw new IllegalArgumentException("At least one function must be provided");
            case 1 -> functions[0];
            case 2 -> node -> Math.min(functions[0].applyAsDouble(node), functions[1].applyAsDouble(node));
            default -> new CompositeHeuristic<>(functions);
        };
    }
}
