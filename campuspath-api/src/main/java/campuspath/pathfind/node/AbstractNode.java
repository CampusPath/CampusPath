package campuspath.pathfind.node;

/**
 * @param <S> Self-type
 * @author Brady
 */
public abstract class AbstractNode<S extends AbstractNode<S>> implements Node<S> {

    private S previous;

    @Override
    public final void setPrevious(S previous) {
        this.previous = previous;
    }

    @Override
    public final S getPrevious() {
        return this.previous;
    }
}
