package campuspath.util;

/**
 * @author Ben
 */
public class Coordinate {

    /**
     * The radius of the Earth in miles
     */
    private static final int RADIUS = 3956;
    public double lat;
    public double lng;

    /**
     * Creates a coordinate with latitude and longitude
     *
     * @param latitude  The latitude of the coordinate
     * @param longitude The longitude of the coordinate
     */
    public Coordinate(double latitude, double longitude) {
        this.lat = latitude;
        this.lng = longitude;
    }

    /**
     * Returns the distance between any two coordinates in miles
     *
     * @param coor The coordinate to find the distance to
     * @return The distance in miles.
     */
    public double distance(Coordinate coor) {
        // Convert all degrees into radians
        var lng1 = Math.toRadians(this.lng);
        var lng2 = Math.toRadians(coor.lng);
        var lat1 = Math.toRadians(this.lat);
        var lat2 = Math.toRadians(coor.lat);

        // Haversine formula
        var sinLng = Math.sin((lng2 - lng1) / 2);
        var sinLat = Math.sin((lat2 - lat1) / 2);
        var a = sinLat * sinLat + Math.cos(lat1) * Math.cos(lat2) * sinLng * sinLng;

        var c = 2 * Math.asin(Math.sqrt(a));
        return (c * (double) RADIUS);
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
