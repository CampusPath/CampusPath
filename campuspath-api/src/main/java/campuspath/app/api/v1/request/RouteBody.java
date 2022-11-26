package campuspath.app.api.v1.request;

import campuspath.util.Coordinate;

import java.util.UUID;

/**
 * @author Brady
 */
public record RouteBody(Coordinate location, UUID destination) {}
