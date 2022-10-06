package campuspath.pathfind.function;

import campuspath.pathfind.node.Node;

import java.util.function.Predicate;

/**
 * Function which determines if the given node is at the goal. In most cases, the implementation of this function is
 * just an equality check against a fixed end node. If this function returns {@code true} for the minimum-estimated cost
 * node in the open set, then a path has been found.
 *
 * @param <T> The node type
 * @author Brady
 */
@FunctionalInterface
public interface GoalFunction<T extends Node<T>> extends Predicate<T> {

    /**
     * @param node A node
     * @return Whether the node is at the goal
     */
    @Override
    boolean test(T node);

    @SafeVarargs
    static <T extends Node<T>> GoalFunction<T> any(GoalFunction<T>... goals) {
        return switch (goals.length) {
            case 0 -> throw new IllegalArgumentException("At least one goal function must be provided");
            case 1 -> goals[0];
            case 2 -> node -> goals[0].test(node) || goals[1].test(node);
            default -> node -> {
                for (var g : goals) {
                    if (g.test(node)) {
                        return true;
                    }
                }
                return false;
            };
        };
    }
}
