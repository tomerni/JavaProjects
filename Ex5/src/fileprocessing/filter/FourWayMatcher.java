package fileprocessing.filter;

/**
 * functional interface with three variables and one answer
 * @param <One> first
 * @param <Two> second
 * @param <Three> third
 * @param <Four> fourth
 */
@FunctionalInterface
public interface FourWayMatcher<One, Two, Three, Four> {

    /**
     * applies the function on the object
     * @param one the first object
     * @param two the second object
     * @param three the third object
     * @return Four object
     */
    Four apply(One one, Two two, Three three);
}
