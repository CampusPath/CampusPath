package campuspath.app;

import campuspath.app.entity.Destination;
import campuspath.app.service.DestinationService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Brady
 */
@RestController
@RequestMapping("/api/v1")
public final class V1Controller {

    private final DestinationService destinations;

    public V1Controller(@Autowired DestinationService destinations) {
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
