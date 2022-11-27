package campuspath.app.service;

import campuspath.app.entity.Destination;
import campuspath.app.repository.CampusRepository;
import campuspath.app.repository.DestinationRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author Brady
 */
@Service
public final class DestinationService {

    private final DestinationRepository repo;
    private final CampusRepository campuses;

    public DestinationService(@Autowired DestinationRepository repo, @Autowired CampusRepository campuses) {
        this.repo = repo;
        this.campuses = campuses;
    }

    public Optional<Destination> getById(UUID id) {
        return this.repo.findById(id);
    }

    public Set<Destination> lookup(UUID campusId, String query) {
        if (query.length() < 2) {
            return Collections.emptySet();
        }

        var campus = this.campuses.getReferenceById(campusId);
        var contains = this.repo.findByCampusEqualsAndNameContainsIgnoreCase(campus, query);
        var matching = this.repo.findAllMatching(campus.id, query);

        Set<Destination> abbrSearch;

        if (query.length() > 3) {
            String abbr = query.replaceAll("\\B.|\\P{L}", "").toUpperCase();
            abbrSearch = this.repo.findByCampusEqualsAndAbbreviationEquals(campus, abbr);
        } else {
            abbrSearch = this.repo.findByCampusEqualsAndAbbreviationEquals(campus, query);
        }

        // Join all the queries
        return Sets.union(Sets.union(contains, matching), abbrSearch);
    }
}
