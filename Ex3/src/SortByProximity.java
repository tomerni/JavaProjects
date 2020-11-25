import oop.ex3.searchengine.Hotel;

import java.util.Comparator;

/**
 * implementing Comparator the compares by proximity
 */
public class SortByProximity implements Comparator<Hotel> {

	// the given longitude
	private final double longitude;

	// the given latitude
	private final double latitude;

	/**
	 * constructor
	 * @param lat the given latitude
	 * @param longi the given longitude
	 */
	SortByProximity(double lat, double longi) {
		longitude = longi;
		latitude = lat;
	}

	/**
	 * compares between the hotels according to their proximity to the given coordinates.
	 * @param a first hotel
	 * @param b second hotel
	 * @return -1 if a is closer and 1 if b is closer (if the distance is equal,compare by the number of POIs)
	 */
	@Override
	public int compare(Hotel a, Hotel b) {
		double aDist = Math.sqrt(Math.pow(a.getLongitude() - longitude, 2) +
								 Math.pow(a.getLatitude() - latitude, 2));
		double bDist = Math.sqrt(Math.pow(b.getLongitude() - longitude, 2) +
								 Math.pow(b.getLatitude() - latitude, 2));
		int distCompare = Double.compare(aDist, bDist);
		if (distCompare == 0) { // if the proximity is the same, compare by the number of POIs
			if (a.getNumPOI() < b.getNumPOI()) {
				return 1;
			}
			if (a.getNumPOI() > b.getNumPOI()) {
				return -1;
			}
			return 0;
		}
		return distCompare;
	}
}
