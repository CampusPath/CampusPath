package campuspath.util;

/**
 * @author Ben
 */
public class Coordinate {

    // The radius of the Earth in miles
    private static final int RADIUS = 3956;
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
        var sinLon = Math.sin((lon2 - lon1) / 2);
        var sinLat = Math.sin((lat2 - lat1) / 2);
        var a = sinLat * sinLat + Math.cos(lat1) * Math.cos(lat2) * sinLon * sinLon;

        var c = 2 * Math.asin(Math.sqrt(a));
        return (c * (double) RADIUS);
    }
}
