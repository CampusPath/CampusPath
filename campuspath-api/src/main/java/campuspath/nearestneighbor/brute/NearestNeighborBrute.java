package campuspath.nearestneighbor.brute;

import campuspath.nearestneighbor.NearestNeighbor;
import campuspath.util.Coordinate;
import campuspath.app.entity.Location;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Ben
 */
public class NearestNeighborBrute implements NearestNeighbor {

    private final Location[] points;

    public NearestNeighborBrute(Location[] points) {
        this.points = points;
    }

    @Override
    public Location findNearest(Coordinate coor) {
        return Arrays.stream(this.points).min(Comparator.comparingDouble(point -> point.distance(coor))).orElse(null);
    }
}
