package campuspath.nearestneighbor;

import campuspath.util.Coordinate;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;


/**
 * @author Ben
 */
public class BallTree implements NearestNeighbor {

    static final int minBuckSize = 1;
    private final Coordinate[] points;
    private final BallTree left;
    private final BallTree right;


    public BallTree(Coordinate[] points) {
        this.points = points;
        this.left = null;
        this.right = null;
    }

    public BallTree(Coordinate[] points, BallTree left, BallTree right) {
        this.points = points;
        this.left = left;
        this.right = right;
    }


    public BallTree constructBallTree(Coordinate[] points) {
        if (points.length <= minBuckSize) {
            return new BallTree(points);
        } else {
            
        }
    }

    public int getMaxSpread() {
        DoubleSummaryStatistics latStats = Arrays.stream(this.points).mapToDouble(Coordinate::getLatitude).summaryStatistics();
        var latSpread = latStats.getMax() - latStats.getMin();

        DoubleSummaryStatistics lngStats = Arrays.stream(this.points).mapToDouble(Coordinate::getLongitude).summaryStatistics();
        var lngSpread = lngStats.getMax() - lngStats.getMin();

        if (latSpread > lngSpread)
            return 0;
        return 1;
    }

    @Override
    public Coordinate findNearest(Coordinate coor) {
        return null;
    }
}
