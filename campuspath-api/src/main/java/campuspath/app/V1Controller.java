package campuspath.app;

import org.springframework.web.bind.annotation.*;

/**
 * @author Brady
 */
@RestController
@RequestMapping("/api/v1")
public final class V1Controller {

    @GetMapping("/search")
    public String search(@RequestParam("q") final String query) {
        return "";
    }

    @PostMapping("/route")
    public String route(@RequestBody final String body) {
        return "";
    }
}
