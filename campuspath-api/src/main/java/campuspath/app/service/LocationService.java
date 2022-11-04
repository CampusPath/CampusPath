package campuspath.app.service;

import campuspath.app.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Ben
 */
@Service
public final class LocationService {

    private final LocationRepository repo;

    public LocationService(@Autowired DestinationRepository repo) {
        this.repo = repo;
    }

    public Set<Location> lookup() {return this.repo.findAll();}
}
