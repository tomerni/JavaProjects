import java.util.Comparator;
import oop.ex3.searchengine.*;

public class SortByRating implements Comparator<Hotel> {

	@Override
	public int compare(Hotel a, Hotel b) {
		int ratingCompare = Float.compare(a.getSiteGeneralRating(), b.getSiteGeneralRating());
		if (ratingCompare == 0) { // if the rating is the same, compare by their names
			return a.getPropertyName().compareTo(b.getPropertyName()) * (-1); // a first, so need to * (-1)
		}
		return ratingCompare;
	}
}
