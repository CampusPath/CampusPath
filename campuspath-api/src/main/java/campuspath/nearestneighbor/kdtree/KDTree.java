package campuspath.nearestneighbor.kdtree;

import campuspath.nearestneighbor.NearestNeighbor;
import campuspath.util.Coordinate;

import java.util.Arrays;

/**
 * @author Ben
 */
public class KDTree implements NearestNeighbor {
    public KDTreeNode root;
    public Coordinate[] points;

    public KDTree(Coordinate[] points) {
        this.points = points;
        this.root = constructKDTree(points, 0, points.length, 0);
    }

    /**
     * Recursively builds a KDTree.
     *
     * @param points A list of Coordinates.
     * @param depth  The current depth in the KDTree.
     * @return A KDTreeNode
     */
    public KDTreeNode constructKDTree(Coordinate[] points, int left, int right, int depth) {
        if (right > left) {
            int axis = depth % 2;

            points = sortByAxis(points, axis);
            int median = (right - left) / 2;

            return new KDTreeNode(points[median], constructKDTree(points, left, median, depth + 1), constructKDTree(points, median + 1, right, depth + 1));
        }
        return null;
    }

    /**
     * Sorts an array of Coordinates by latitude or longitude.
     *
     * @param points The array to sort.
     * @param axis   The axis to sort on. 0 for latitude, 1 for longitude
     * @return An array of sorted Coordinates.
     */
    static Coordinate[] sortByAxis(Coordinate[] points, int axis) {
        var sorted = Arrays.copyOf(points, points.length);
        Arrays.sort(sorted, Coordinate.compareAxis(axis));
        return sorted;
    }

    /**
     * Searches for a specific Coordinate in a KDTree recursively
     *
     * @param coor     The coordinate we are looking for
     * @param currBest The current closest node
     * @param depth    The current depth in the tree
     * @param currNode The current node we are comparing
     * @return The closest Coordinate.
     **/
    public Coordinate searchRecursive(Coordinate coor, Coordinate currBest, int depth, KDTreeNode currNode) {
        if (currNode == null) {return currBest;}

        int axis = depth % 2;

        if (currNode.centroid.distance(coor) < currBest.distance(coor)) {currBest = currNode.centroid;}

        if (Coordinate.compareAxis(axis).compare(currNode.centroid, coor) < 0)
            return this.searchRecursive(coor, currBest, depth + 1, currNode.left);
        else return this.searchRecursive(coor, currBest, depth + 1, currNode.right);
    }

    @Override
    public Coordinate findNearest(Coordinate coor) {
        return this.searchRecursive(coor, null, 0, this.root);
    }
}
