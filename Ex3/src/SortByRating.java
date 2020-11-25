import java.util.Comparator;

import oop.ex3.searchengine.*;

/**
 * implementing Comparator the compares by rating
 */
public class SortByRating implements Comparator<Hotel> {

	/**
	 * compares between the hotels according to their rating.
	 * @param a first hotel
	 * @param b second hotel
	 * @return -1 if the rating of b is bigger and 1 if the rating of a is bigger (if the rating is
	 * equal, compare by their names)
	 */
	@Override
	public int compare(Hotel a, Hotel b) {
		int ratingCompare = Float.compare(a.getSiteGeneralRating(), b.getSiteGeneralRating());
		if (ratingCompare == 0) { // if the rating is the same, compare by their names
			return a.getPropertyName().compareTo(b.getPropertyName()) * (-1); // a first, so need to * (-1)
		}
		return ratingCompare;
	}
}
