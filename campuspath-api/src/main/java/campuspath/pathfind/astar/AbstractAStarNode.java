package campuspath.pathfind.astar;

import campuspath.pathfind.node.AbstractCostNode;

/**
 * @author Brady
 */
public abstract class AbstractAStarNode<S extends AbstractAStarNode<S>> extends AbstractCostNode<S> implements AStarNode<S> {

    @Override
    public final void setCost(S previous, double cost) {
        super.setCost(previous, cost);
        this.setEstimate(cost + this.getHeuristic());
    }

    protected abstract void setEstimate(double estimate);
}
