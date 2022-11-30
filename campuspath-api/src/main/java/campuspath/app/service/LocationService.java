package campuspath.app.service;

import campuspath.app.entity.Location;
import campuspath.app.repository.LocationRepository;
import campuspath.nearestneighbor.NearestNeighbor;
import campuspath.nearestneighbor.brute.NearestNeighborBrute;
import campuspath.util.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Ben
 */
@Service
public final class LocationService implements NearestNeighbor {

    private final LocationRepository repo;
    private final NearestNeighbor delegateNearest;

    public LocationService(@Autowired LocationRepository repo) {
        this.repo = repo;
        this.delegateNearest = new NearestNeighborBrute() {

            @Override
            protected Stream<Location> getAll() {
                return LocationService.this.getAll().stream();
            }
        };
    }

    public List<Location> getAll() {
        return this.repo.findAll();
    }

    @Override
    public Location findNearest(Coordinate coordinate) {
        return this.delegateNearest.findNearest(coordinate);
    }
}
