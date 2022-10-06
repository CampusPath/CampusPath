package campuspath.pathfind.function;

import campuspath.pathfind.node.Node;

/**
 * @author Brady
 */
public final class CompositeGoal<T extends Node<T>> implements GoalFunction<T> {

    private final GoalFunction<T>[] functions;

    @SafeVarargs
    private CompositeGoal(GoalFunction<T>... functions) {
        this.functions = functions;
    }

    @Override
    public boolean test(T node) {
        for (var function : this.functions) {
            if (function.test(node)) {
                return true;
            }
        }
        return false;
    }

    @SafeVarargs
    public static <T extends Node<T>> GoalFunction<T> of(GoalFunction<T>... functions) {
        return switch (functions.length) {
            case 0 -> throw new IllegalArgumentException("At least one goal function must be provided");
            case 1 -> functions[0];
            case 2 -> node -> functions[0].test(node) || functions[1].test(node);
            default -> new CompositeGoal<>(functions);
        };
    }
}
