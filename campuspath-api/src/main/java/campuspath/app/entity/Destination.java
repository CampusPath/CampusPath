package campuspath.app.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID;

/**
 * @author Brady
 */
@Entity(name = "destination")
public final class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @JsonView(Query.class)
    private UUID id;

    @Column(nullable = false)
    @JsonView(Query.class)
    private String name;

    @Column
    @JsonView(Query.class)
    private String abbreviation;

    @ManyToOne(optional = false)
    private Campus campus;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Location> locations;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Campus getCampus() {
        return this.campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public Set<Location> getLocations() {
        return this.locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public static class Query {}
}
