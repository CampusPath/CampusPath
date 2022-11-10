package campuspath.nearestneighbor.balltree;

import campuspath.util.Coordinate;

import java.util.Arrays;

/**
 * @author Ben
 */
public class BallTreeNode {
    private static int minBucketSize = 1;

    private final Coordinate[] points;
    private final double radius;
    private final Coordinate centroid;

    private final BallTreeNode left;
    private final BallTreeNode right;


    public BallTreeNode(Coordinate[] points, double radius) {
        this.points = points;
        this.radius = radius;
        this.centroid = this.getCentroid();
        this.left = null;
        this.right = null;
    }


    private Coordinate getCentroid() {
        int bucketSize = this.points.length;

        double latMean = Arrays.stream(this.points).mapToDouble(Coordinate::getLatitude).sum() / bucketSize;
        double lngMean = Arrays.stream(this.points).mapToDouble(Coordinate::getLongitude).sum() / bucketSize;

        return new Coordinate(latMean, lngMean);
    }


    public boolean inBucket(Coordinate coor) {
        if (this.centroid.distance(coor) <= this.radius)
            return true;
        return false;
    }
}
