package campuspath.app.entity;

import campuspath.util.Coordinate;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * An identifiable coordinate
 *
 * @author Brady
 */
@Entity(name = "location")
public final class Location extends Coordinate {

    private UUID id;
    private List<Location> adjacent;

    public Location() {
        this(0.0, 0.0);
    }

    public Location(double latitude, double longitude) {
        super(latitude, longitude);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    public List<Location> getAdjacent() {
        return this.adjacent;
    }

    public void setAdjacent(List<Location> adjacent) {
        this.adjacent = adjacent;
    }

    @Column(nullable = false)
    @Override
    public double getLatitude() {
        return super.getLatitude();
    }

    @Basic(optional = false)
    @Override
    public double getLongitude() {
        return super.getLongitude();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location loc)) {
            return false;
        }
        return Objects.equals(this.id, loc.id)
                && Objects.equals(this.getLatitude(), loc.getLatitude())
                && Objects.equals(this.getLongitude(), loc.getLongitude());
    }
}
