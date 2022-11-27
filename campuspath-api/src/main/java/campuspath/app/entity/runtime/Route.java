package campuspath.app.entity.runtime;

import campuspath.app.entity.Destination;
import campuspath.app.entity.Views;
import campuspath.util.Coordinate;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

/**
 * @author Brady
 */
//@formatter:off
public record Route(
        @JsonView(Views.APIMinimal.class) double            distance,
        @JsonView(Views.APIMinimal.class) List<Coordinate>  path,
        @JsonView(Views.APIMinimal.class) Destination       destination) {
//@formatter:on
}
