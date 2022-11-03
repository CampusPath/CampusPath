package campuspath.app.service;

import campuspath.app.entity.Destination;
import campuspath.app.repository.DestinationRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

/**
 * @author Brady
 */
@Service
public final class DestinationService {

    private final DestinationRepository repo;

    public DestinationService(@Autowired DestinationRepository repo) {
        this.repo = repo;
    }

    public Set<Destination> lookup(String query) {
        if (query.length() < 2) {
            return Collections.emptySet();
        }

        var contains = this.repo.findByNameContainsIgnoreCase(query);
        var matching = this.repo.findAllMatching(query);

        // Join all the queries
        return Sets.union(contains, matching);
    }
}
