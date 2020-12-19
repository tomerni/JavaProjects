package fileprocessing.filter;

@FunctionalInterface
public interface FourWayMatcher<One, Two, Three, Four> {
	public Four apply(One one, Two two, Three three);
}
