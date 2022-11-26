package campuspath.nearestneighbor;

import campuspath.util.Coordinate;
import campuspath.app.entity.Location;

/**
 * @author Ben
 */
public interface NearestNeighbor {

    /**
     * Attempts to find the closest or approximately closest point in a set of coordinates to a given location.
     *
     * @param coor The users location
     * @return The closest point
     */
    Location findNearest(Coordinate coor);
}
