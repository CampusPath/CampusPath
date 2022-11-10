package campuspath.app.api;

import campuspath.app.entity.Campus;
import campuspath.app.entity.Destination;
import campuspath.app.service.CampusService;
import campuspath.app.service.DestinationService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author Brady
 */
@RestController
@RequestMapping("/api/v1")
public final class V1Controller {

    private final CampusService campuses;
    private final DestinationService destinations;

    public V1Controller(@Autowired CampusService campuses, @Autowired DestinationService destinations) {
        this.campuses = campuses;
        this.destinations = destinations;
    }

    @GetMapping("/campus")
    public List<Campus> campuses() {
        return this.campuses.getAll();
    }

    @GetMapping("/search/{campusId}")
    @JsonView(Destination.Query.class)
    public Set<Destination> search(@PathVariable final UUID campusId, @RequestParam("q") final String query) {
        return this.destinations.lookup(campusId, query);
    }

    @PostMapping("/route")
    public String route(@RequestBody final String body) {
        return "";
    }
}
