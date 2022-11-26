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
}
