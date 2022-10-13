// I'm not done with this yet I just need to work on it on my laptop

package campuspath.entity;

import jakarta.persistence.*;
import org.springframework.beans.factory.parsing.Location;

import java.util.UUID;

/**
 * A connection between two locations
 *
 * @author Ben
 */
@Entity(name = "connection")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public final class Connection{
    @Id
    private UUID id1;
    private UUID id2;

    @ManyToMany
    private Location location1;
    private Location location2;

    public Connection(Location location1, Location location2) {
        super();
        this.location1 = location1;
        this.location2 = location2;
    }
}
