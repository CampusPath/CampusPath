package campuspath.app.entity.runtime;

import campuspath.util.Coordinate;

import java.util.Collections;
import java.util.List;

/**
 * @author Brady
 */
public record Route(double distance, List<Coordinate> path) {

    public Route(double distance, List<Coordinate> path) {
        this.distance = distance;
        this.path = Collections.unmodifiableList(path);
    }
}
