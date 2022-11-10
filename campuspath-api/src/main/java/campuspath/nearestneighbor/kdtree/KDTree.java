package campuspath.nearestneighbor.kdtree;

import campuspath.nearestneighbor.kdtree.KDTreeNode;
import campuspath.util.Coordinate;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Ben
 */
public class KDTree {
    public KDTreeNode root;
    public Coordinate[] points;

    public KDTree(Coordinate[] points) {
        this.points = points;
        this.root = constructKDTree(points, 0);
    }

    /**
     * Recursively builds a KDTree.
     *
     * @param points A list of Coordinates.
     * @param depth  The current depth in the KDTree.
     * @return A KDTreeNode
     */
    public KDTreeNode constructKDTree(Coordinate[] points, int depth) {
        if (points.length == 0) return null;

        int axis = depth % 2;

        points = sortByAxis(points, axis);
        int median = points.length / 2;

        return new KDTreeNode(points[median], constructKDTree(Arrays.copyOfRange(points, 0, median), depth + 1), constructKDTree(Arrays.copyOfRange(points, median + 1, points.length), depth + 1));
    }

    /**
     * Sorts an array of Coordinates by latitude or longitude.
     *
     * @param points The array to sort.
     * @param axis   The axis to sort on. 0 for latitude, 1 for longitude
     * @return An array of sorted Coordinates.
     */
    static Coordinate[] sortByAxis(Coordinate[] points, int axis) {
        Comparator sorter;
        Coordinate[] sortCopy = points.clone();

        if (axis == 0) sorter = Comparator.comparing(Coordinate::getLatitude);
        else sorter = Comparator.comparing(Coordinate::getLongitude);

        Arrays.sort(sortCopy, sorter);
        return sortCopy;
    }

}
