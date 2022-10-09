package campuspath.util;

/**
 * @author Ben
 */
public class Coordinate {

    // The radius of the Earth in miles
    static int radius = 3956;
    public double lat;
    public double lon;

    /**
     * Creates a coordinate with lattitude and longitude
     *
     * @param latitude  The latitide of the coordinate
     * @param longitude The longitude of the coordinate
     */
    public Coordinate(double latitude, double longitude) {
        this.lat = latitude;
        this.lon = longitude;
    }

    /**
     * Returns the distance between any two coordinates in miles
     *
     * @param coor The coordinate to find the distance to
     * @return The distance from one coordinate to another in miles.
     */
    public double distance(Coordinate coor) {
        // Convert all degrees into radians
        var lon1 = Math.toRadians(this.lon);
        var lon2 = Math.toRadians(coor.lon);
        var lat1 = Math.toRadians(this.lat);
        var lat2 = Math.toRadians(coor.lat);

        // Haversine formula
        var dlon = lon2 - lon1;
        var dlat = lat2 - lat1;
        var a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        var c = 2 * Math.asin(Math.sqrt(a));

        // calculate the result
        return (c * (double) radius);
    }
}
