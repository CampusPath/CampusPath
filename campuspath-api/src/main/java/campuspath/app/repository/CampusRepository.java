package campuspath.app.repository;

import campuspath.app.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Brady
 * @since 11/10/2022
 */
@Repository
public interface CampusRepository extends JpaRepository<Campus, UUID> {}
