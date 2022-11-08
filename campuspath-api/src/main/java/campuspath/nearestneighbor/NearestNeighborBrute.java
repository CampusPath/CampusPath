package campuspath.nearestneighbor;

import campuspath.util.Coordinate;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Ben
 */
public class NearestNeighborBrute implements NearestNeighbor {

    private final Coordinate[] points;

    public NearestNeighborBrute(Coordinate[] points) {
        this.points = points;
    }

    @Override
    public Coordinate findNearest(Coordinate coor) {
        return Arrays.stream(this.points).min(Comparator.comparingDouble(point -> point.distance(coor))).orElse(null);
    }
}
