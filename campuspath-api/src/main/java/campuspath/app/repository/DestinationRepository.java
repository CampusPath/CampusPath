package campuspath.app.repository;

import campuspath.app.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Brady
 */
@Repository
public interface DestinationRepository extends JpaRepository<Destination, UUID> {

    @Query
    List<Destination> findByNameContainsIgnoreCase(String name);
}
