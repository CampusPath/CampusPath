package campuspath.app.repository;

import campuspath.app.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Brady
 */
@Repository
public interface DestinationRepository extends JpaRepository<Destination, UUID> {}
