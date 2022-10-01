package campuspath.pathfind;

import campuspath.pathfind.node.Node;

import java.util.Optional;

/**
 * @author Brady
 */
public interface Pathfinder<T extends Node<T>> {

    /**
     * Attempts to find a path from the start node which satisfies the goal requirement for this pathfinder instance.
     * If a path is found, then an {@link Optional} containing the path is returned; otherwise, an empty
     * {@link Optional} is returned.
     *
     * @return The found path, or {@link Optional#empty()} if none.
     */
    Optional<Path<T>> find();
}
