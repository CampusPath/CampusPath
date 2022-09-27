package campuspath.pathfind.function;

import campuspath.pathfind.node.Node;

import java.util.function.ToDoubleBiFunction;

/**
 * Function which calculates the actual cost from one node to another. Typically, the return value of this function is
 * independent of the order of its arguments; however, the arguments are guaranteed to be passed in the order of
 * {@code (from, to)} for special cases where asymmetrical behavior is desired.
 *
 * @param <T> The node type
 * @author Brady
 */
@FunctionalInterface
public interface CostFunction<T extends Node<T>> extends ToDoubleBiFunction<T, T> {

    /**
     * @param from The source node
     * @param to   The destination node
     * @return The cost between the nodes
     */
    @Override
    double applyAsDouble(T from, T to);
}
