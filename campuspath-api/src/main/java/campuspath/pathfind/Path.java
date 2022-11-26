package campuspath.pathfind;

import campuspath.pathfind.node.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An immutable, ordered collection of nodes. Each consecutive pair of nodes forms an edge, and all nodes are unique.
 *
 * @param <T> The node type
 * @author Brady
 */
public final class Path<T extends Node<T>> {

    private final List<T> nodes;

    /**
     * @param nodes A contiguous array of nodes ordered from source to destination
     */
    @SafeVarargs
    public Path(T... nodes) {
        this.nodes = List.of(nodes);
    }

    /**
     * @param nodes A contiguous list of nodes ordered from source to destination
     */
    public Path(List<T> nodes) {
        this.nodes = List.copyOf(nodes);
    }

    /**
     * @return The nodes defining this path
     */
    public List<T> nodes() {
        return this.nodes;
    }

    /**
     * @return The first node along this path
     */
    public T start() {
        return this.nodes.get(0);
    }

    /**
     * @return The last node along this path
     */
    public T end() {
        return this.nodes.get(this.size() - 1);
    }

    /**
     * @return The number of nodes in this path
     */
    public int size() {
        return this.nodes.size();
    }

    /**
     * Assembles a {@link Path} from an end node by traversing backwards through the nodes that precede it.
     *
     * @param end The end node
     * @return An assembled path
     */
    public static <T extends Node<T>> Path<T> assemble(final T end) {
        var nodes = new ArrayList<T>();

        var node = end;
        do {
            nodes.add(0, node);
            node = node.getPrevious();
        } while (node != null);

        return new Path<>(Collections.unmodifiableList(nodes));
    }
}
