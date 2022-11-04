package campuspath.app.repository;

import campuspath.app.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Brady
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    @Query(value = "SELECT * FROM location", nativeQuery = true)
    Set<Location> findAll();

    //This might work as well.
    @Query
    Set<Location> find();


}
