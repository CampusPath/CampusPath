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
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private BoundingBox boundingBox;
}
