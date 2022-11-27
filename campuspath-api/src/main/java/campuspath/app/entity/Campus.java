package campuspath.app.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

/**
 * @author Brady
 */
@Entity(name = "campus")
public final class Campus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    public UUID id;

    @Column(nullable = false)
    public String name;

    @Embedded
    @JsonUnwrapped
    public BoundingBox bounds;

    public Campus() {}

    public Campus(UUID id, String name, BoundingBox bounds) {
        this.id = id;
        this.name = name;
        this.bounds = bounds;
    }
}
