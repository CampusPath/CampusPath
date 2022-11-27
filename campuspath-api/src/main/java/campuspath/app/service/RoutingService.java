package campuspath.app.service;

import campuspath.app.entity.Destination;
import campuspath.app.entity.Location;
import campuspath.app.entity.runtime.Route;
import campuspath.nearestneighbor.NearestNeighbor;
import campuspath.nearestneighbor.brute.NearestNeighborBrute;
import campuspath.pathfind.algorithm.astar.AStarPathfinder;
import campuspath.pathfind.algorithm.astar.AbstractAStarNode;
import campuspath.pathfind.algorithm.astar.set.BinaryHeapNode;
import campuspath.pathfind.algorithm.astar.set.BinaryHeapOpenSet;
import campuspath.pathfind.function.CompositeGoal;
import campuspath.pathfind.function.CompositeHeuristic;
import campuspath.pathfind.function.GoalFunction;
import campuspath.pathfind.function.HeuristicFunction;
import campuspath.pathfind.traversal.CostMove;
import campuspath.util.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * FIXME: This impl is all-around inefficient and could be done a lot better,
 *        it's just so that something functional exists.
 *
 * @author Brady
 */
@Service
public final class RoutingService {

    private final LocationService locations;
    private final NearestNeighbor nearestNeighbor;

    public RoutingService(@Autowired LocationService locations) {
        this.locations = locations;
        // TODO: Replace brute-force impl with log(n) algorithm
        this.nearestNeighbor = new NearestNeighborBrute() {

            @Override
            public Stream<Location> getAll() {
                return RoutingService.this.locations.lookup().stream();
            }
        };
    }

    public Route route(Coordinate source, Destination destination) {
        var closest = this.nearestNeighbor.findNearest(source);

        // Create composite functions
        var destinations = destination.getLocations();

        // TODO: Consider possible benefit of using manhattan distance instead of euclidean?
        var heuristics = destinations.stream()
                .map(loc -> (HeuristicFunction<LocationNode>) node -> node.location.distance(loc))
                .toArray(HeuristicFunction[]::new);

        var goals = destinations.stream()
                .map(loc -> (GoalFunction<LocationNode>) node -> node.location.getId().equals(loc.getId()))
                .toArray(GoalFunction[]::new);
        ;

        // noinspection unchecked
        return route(destination, closest, CompositeHeuristic.of(heuristics), CompositeGoal.of(goals));
    }

    private Route route(Destination destination, Location source, HeuristicFunction<LocationNode> heuristic, GoalFunction<LocationNode> goal) {
        // Scuffed caching let's goooo
        var nodes = new HashMap<Location, LocationNode>();
        Function<Location, LocationNode> nodeFor
                = loc -> nodes.computeIfAbsent(loc, l -> new LocationNode(loc, heuristic));

        // Pathfinder setup
        var start = nodeFor.apply(source);
        var pathfinder = new AStarPathfinder<>(
                new BinaryHeapOpenSet<>(LocationNode[]::new),
                start,
                src -> src.location.getAdjacent().stream()
                        .map(nodeFor)
                        .map(dest -> new LocationNodeMove(src, dest, src.location.distance(dest.location)))
                        .toArray(LocationNodeMove[]::new),
                goal
        );

        // FIND IT!!!!
        return pathfinder.find().map(path -> {
            var distance = path.end().getCost();
            var points = path.nodes().stream()
                    .map(node -> node.location)
                    .map(loc -> (Coordinate) loc) // down-cast
                    .toList();
            return new Route(distance, points, destination);
        }).orElse(null);
    }

    public static class LocationNode extends AbstractAStarNode<LocationNode> implements BinaryHeapNode<LocationNode> {

        private final Location location;
        private double heuristic;

        // Initial State
        private double estimate = Double.MAX_VALUE;
        private int index = -1;

        public LocationNode(Location location, HeuristicFunction<LocationNode> heuristic) {
            this.location = location;
            this.heuristic = heuristic.applyAsDouble(this);
        }

        @Override
        public double getHeuristic() {
            return this.heuristic;
        }

        @Override
        public double getEstimate() {
            return this.estimate;
        }

        @Override
        protected void setEstimate(double estimate) {
            this.estimate = estimate;
        }

        @Override
        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public int getIndex() {
            return this.index;
        }

        @Override
        public boolean isOpen() {
            return this.index != -1;
        }
    }

    private record LocationNodeMove(LocationNode source, LocationNode destination,
                                    double cost) implements CostMove<LocationNode> {

        @Override
        public double getCost() {
            return this.cost;
        }

        @Override
        public LocationNode getSource() {
            return this.source;
        }

        @Override
        public LocationNode getDestination() {
            return this.destination;
        }
    }
}
