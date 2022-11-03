package campuspath.app.service;

import campuspath.app.entity.Destination;
import campuspath.app.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Brady
 */
@Service
public final class DestinationService {

    private final DestinationRepository repo;

    public DestinationService(@Autowired DestinationRepository repo) {
        this.repo = repo;
    }

    public List<Destination> lookup(String query) {
        return this.repo.findAllMatching(query);
    }
}
