package campuspath.repository;

import campuspath.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Brady
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {}
