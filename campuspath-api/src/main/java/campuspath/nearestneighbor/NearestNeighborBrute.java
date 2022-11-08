package campuspath.nearestneighbor;

import campuspath.util.Coordinate;

/**
 * @Author Ben
 */
public class NearestNeighborBrute implements NearestNeighbor {

    private final Coordinate[] pathPoints;

    public NearestNeighborBrute(Coordinate[] pathPoints) {this.pathPoints = pathPoints;}

    @Override
    public Coordinate findNearest(Coordinate coor) {
        // Return null if the array is empty
        if (this.pathPoints.length == 0) {return null;}

        // Set the current minimum distance to infinity and the closest coordinate to null
        var minDist = Double.POSITIVE_INFINITY;
        Coordinate closestCoor = null;

        // Loop through all coordinates in the array
        for (int i = 0; i <= this.pathPoints.length; i++) {
            // Find the distance between the current node and the target coordinate
            Coordinate currCoor = this.pathPoints[i];
            var currDist = coor.distance(currCoor);

            // Update the current minimum if necessary
            if (currDist < minDist) {
                minDist = currDist;
                closestCoor = currCoor;
            }
        }

        // Return the minimum
        return closestCoor;
    }
}
