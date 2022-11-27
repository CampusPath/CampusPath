package campuspath.app.api.v1;

import campuspath.app.api.v1.request.RouteBody;
import campuspath.app.entity.Campus;
import campuspath.app.entity.Destination;
import campuspath.app.entity.runtime.Route;
import campuspath.app.service.CampusService;
import campuspath.app.service.DestinationService;
import campuspath.app.service.RoutingService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author Brady
 */
@RestController
@CrossOrigin(origins = {
        "http://localhost:4200" // Angular Dev
})
@RequestMapping("/api/v1")
public final class V1Controller {

    //@formatter:off
    private final CampusService campuses;
    private final DestinationService destinations;
    private final RoutingService routing;

    public V1Controller(@Autowired CampusService campuses,
                        @Autowired DestinationService destinations,
                        @Autowired RoutingService routing) {
        this.campuses = campuses;
        this.destinations = destinations;
        this.routing = routing;
    }
    //@formatter:on

    @GetMapping("/campuses")
    public List<Campus> campuses() {
        return this.campuses.getAll();
    }

    @GetMapping("/campus/{campusId}")
    public Campus campus(@PathVariable final UUID campusId) {
        return this.campuses.getById(campusId);
    }

    @GetMapping("/search/{campusId}")
    @JsonView(Destination.Query.class)
    public Set<Destination> search(@PathVariable final UUID campusId, @RequestParam("q") final String query) {
        return this.destinations.lookup(campusId, query);
    }

    @PostMapping("/route")
    public Route route(@RequestBody final RouteBody body) {
        return this.destinations.getById(body.destination())
                .map(destination -> this.routing.route(body.location(), destination))
                // FIXME: This doesn't actually forward the error to the client
                .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "Destination not found"));
    }
}
