package campuspath.app.entity;

import jakarta.persistence.Embeddable;

/**
 * @author Brady
 */
@Embeddable
public final class BoundingBox {

    public double minLat;
    public double minLng;
    public double maxLat;
    public double maxLng;

    public BoundingBox() {}

    public BoundingBox(double minLat, double minLng, double maxLat, double maxLng) {
        this.minLat = minLat;
        this.minLng = minLng;
        this.maxLat = maxLat;
        this.maxLng = maxLng;
    }
}
