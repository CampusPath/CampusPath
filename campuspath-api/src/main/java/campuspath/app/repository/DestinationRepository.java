package campuspath.app.repository;

import campuspath.app.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

/**
 * @author Brady
 */
@Repository
public interface DestinationRepository extends JpaRepository<Destination, UUID> {

    @Query
    Set<Destination> findByNameContainsIgnoreCase(String name);

    @Query(value = "SELECT * FROM destination WHERE ?1 % ANY(STRING_TO_ARRAY(destination.name,' '))", nativeQuery = true)
    Set<Destination> findAllMatching(String partialTitle);
}
