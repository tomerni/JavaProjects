package fileprocessing.filter;

@FunctionalInterface
public interface ThreeWayMatcher<One, Two, Three> {
	public Three apply(One one, Two two);
}
