package campuspath.util;

/**
 * A 2-tuple
 *
 * @param left  The LHS element
 * @param right The RHS element
 * @param <L>   The LHS element type
 * @param <R>   The RHS element type
 */
public record Pair<L, R>(L left, R right) {}
