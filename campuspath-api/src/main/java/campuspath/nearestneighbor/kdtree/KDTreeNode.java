package campuspath.nearestneighbor.kdtree;

import campuspath.util.Coordinate;

/**
 * @author Ben
 */
public class KDTreeNode {

    public final Coordinate centroid;
    public KDTreeNode left;
    public KDTreeNode right;

    public KDTreeNode(Coordinate centroid, KDTreeNode left, KDTreeNode right) {
        this.centroid = centroid;
        this.left = left;
        this.right = right;
    }
}
