import oop.ex3.searchengine.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BoopingSite {

	private final String dataSet;

	private final Hotel[] hotels;

	public BoopingSite(String name) {
		dataSet = name;
		hotels = HotelDataset.getHotels(dataSet);
	}

	private ArrayList<Hotel> createCityHotelsArray(String city, boolean flag) {
		ArrayList<Hotel> hotelsInTheCity = new ArrayList<Hotel>();
		for (Hotel t : hotels) {
			if (t.getCity().equals(city) & flag) {
				hotelsInTheCity.add(t);
			} else {
				if (!flag) {
					hotelsInTheCity.add(t);
				}
			}
		}
		return hotelsInTheCity;
	}

	private Hotel[] sortAndReturnArray(ArrayList<Hotel> arr, Comparator<Hotel> comparator, boolean flag) {
		Collections.sort(arr, comparator);
		if (flag) {
			Collections.reverse(arr);
		}
		Hotel[] sortedHotels = new Hotel[arr.size()];
		for (int i = 0; i < arr.size(); i++) {
			sortedHotels[i] = arr.get(i);
		}
		return sortedHotels;
	}

	public Hotel[] getHotelsInCityByRating(String city) {
		ArrayList<Hotel> hotelsInTheCity = createCityHotelsArray(city, true);
		return sortAndReturnArray(hotelsInTheCity, new SortByRating(), true);
	}

	public Hotel[] getHotelsByProximity(double latitude, double longitude) {
		ArrayList<Hotel> hotelsInTheCity = createCityHotelsArray("", false);
		return sortAndReturnArray(hotelsInTheCity, new SortByProximity(latitude, longitude), false);
	}

	public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude) {
		ArrayList<Hotel> hotelsInTheCity = createCityHotelsArray(city, true);
		return sortAndReturnArray(hotelsInTheCity, new SortByProximity(latitude, longitude), false);
	}
}
