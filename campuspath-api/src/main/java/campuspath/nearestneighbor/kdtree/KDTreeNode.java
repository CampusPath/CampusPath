package campuspath.nearestneighbor.kdtree;

import campuspath.app.entity.Location;

/**
 * @author Ben
 */
public class KDTreeNode {

    public final Location centroid;
    public KDTreeNode left;
    public KDTreeNode right;

    public KDTreeNode(Location centroid, KDTreeNode left, KDTreeNode right) {
        this.centroid = centroid;
        this.left = left;
        this.right = right;
    }
}
