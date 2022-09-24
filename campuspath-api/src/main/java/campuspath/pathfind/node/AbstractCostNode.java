package campuspath.pathfind.node;

/**
 * Abstract implementation of {@link CostNode}
 *
 * @param <S> Self-type
 * @author Brady
 */
public abstract class AbstractCostNode<S extends AbstractCostNode<S>> implements CostNode<S> {

    private double cost;

    public AbstractCostNode() {
        this(Double.POSITIVE_INFINITY);
    }

    public AbstractCostNode(double initialCost) {
        this.cost = initialCost;
    }

    @Override
    public void setCost(S previous, double cost) {
        this.setPrevious(previous);
        this.cost = cost;
    }

    @Override
    public final double getCost() {
        return this.cost;
    }
}
