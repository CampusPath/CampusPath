package campuspath.app.service;

import campuspath.app.entity.Destination;
import campuspath.app.repository.DestinationRepositoryFuzzy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ben
 */
@Service
public final class DestinationServiceFuzzy {

    private final DestinationRepositoryFuzzy repo;

    public DestinationServiceFuzzy(@Autowired DestinationRepositoryFuzzy repo) {
        this.repo = repo;
    }

    public List<Destination> lookup(String query) {
        return this.repo.findAllMatching(query);
    }
}
