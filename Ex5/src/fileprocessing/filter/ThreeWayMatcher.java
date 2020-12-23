package fileprocessing.filter;

/**
 * functional interface with two variables and one answer
 * @param <One> first
 * @param <Two> second
 * @param <Three> third
 */
@FunctionalInterface
public interface ThreeWayMatcher<One, Two, Three> {
    /**
     * applies the function on the object
     * @param one the first object
     * @param two the second object
     * @return three object
     */
    Three apply(One one, Two two);
}
