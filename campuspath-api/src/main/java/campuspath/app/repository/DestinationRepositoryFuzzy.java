package campuspath.app.repository;

import campuspath.app.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Ben
 */
@Repository
public interface DestinationRepositoryFuzzy extends JpaRepository<Destination, UUID> {

    @Query(value = "SELECT * FROM t_todo todo WHERE ?1 % ANY(STRING_TO_ARRAY(todo.title,' '))", nativeQuery = true)
    List<Destination> findAllMatching(String partialTitle);

}