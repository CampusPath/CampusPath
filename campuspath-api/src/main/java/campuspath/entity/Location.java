package campuspath.entity;

import campuspath.util.Coordinate;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

/**
 * An identifiable coordinate
 *
 * @author Brady
 */
@Entity(name = "location")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public final class Location extends Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    public Location() {
        this(0.0, 0.0);
    }

    public Location(double latitude, double longitude) {
        super(latitude, longitude);
    }

    @Basic(optional = false)
    public double getLatitude() {
        return super.getLatitude();
    }

    @Basic(optional = false)
    public double getLongitude() {
        return super.getLongitude();
    }
}
