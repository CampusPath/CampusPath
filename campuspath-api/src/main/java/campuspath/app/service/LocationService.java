package campuspath.app.service;

import campuspath.app.entity.Location;
import campuspath.app.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ben
 */
@Service
public final class LocationService {

    private final LocationRepository repo;

    public LocationService(@Autowired LocationRepository repo) {
        this.repo = repo;
    }

    public List<Location> lookup() {
        return this.repo.findAll();
    }
}
