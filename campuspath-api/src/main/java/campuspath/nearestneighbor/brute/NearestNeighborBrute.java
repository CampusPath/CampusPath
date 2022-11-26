package campuspath.nearestneighbor.brute;

import campuspath.app.entity.Location;
import campuspath.nearestneighbor.NearestNeighbor;
import campuspath.util.Coordinate;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @author Ben
 */
public abstract class NearestNeighborBrute implements NearestNeighbor {

    @Override
    public final Location findNearest(Coordinate coor) {
        return getAll().min(Comparator.comparingDouble(point -> point.distance(coor))).orElse(null);
    }

    protected abstract Stream<Location> getAll();
}
