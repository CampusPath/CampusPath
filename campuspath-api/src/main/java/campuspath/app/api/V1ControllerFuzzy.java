package campuspath.app.api;

import campuspath.app.entity.Destination;
import campuspath.app.service.DestinationServiceFuzzy;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ben
 */
@RestController
@RequestMapping("/api/v1")
public final class V1ControllerFuzzy {

    private final DestinationServiceFuzzy destinations;

    public V1ControllerFuzzy(@Autowired DestinationServiceFuzzy destinations) {
        this.destinations = destinations;
    }

    @GetMapping("/search")
    @JsonView(Destination.Query.class)
    public List<Destination> search(@RequestParam("q") final String query) {
        return this.destinations.lookup(query);
    }

    @PostMapping("/route")
    public String route(@RequestBody final String body) {
        return "";
    }
}
