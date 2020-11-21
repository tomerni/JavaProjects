import oop.ex3.searchengine.Hotel;

import java.util.Comparator;

public class SortByProximity implements Comparator<Hotel> {

	private final double longitude;

	private final double latitude;

	SortByProximity(double lat, double longi) {
		longitude = longi;
		latitude = lat;
	}

	@Override
	public int compare(Hotel a, Hotel b) {
		double aDist = Math.sqrt(Math.pow(a.getLongitude() - longitude, 2) +
								 Math.pow(a.getLatitude() - latitude, 2));
		double bDist = Math.sqrt(Math.pow(b.getLongitude() - longitude, 2) +
								 Math.pow(b.getLatitude() - latitude, 2));
		int distCompare = Double.compare(aDist, bDist);
		if (distCompare == 0) { // if the proximity is the same, compare by their names
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
