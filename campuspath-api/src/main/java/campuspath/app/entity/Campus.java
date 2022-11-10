package campuspath.app.entity;

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
    public BoundingBox boundingBox;
}
