package campuspath.nearestneighbor;

import campuspath.util.Coordinate;

/**
 * @Author Ben
 */
public interface NearestNeighbor {

    /**
     * Attempts to find the closest or approximately closest point in a set of coordinates to a given location.
     *
     * @param coor The users location
     * @return The closest point
     */
    Coordinate findNearest(Coordinate coor);
}
