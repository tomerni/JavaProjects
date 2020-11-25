import oop.ex3.searchengine.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * implementing the BoopSite class
 */
public class BoopingSite {

	// hotels list
	private final Hotel[] hotels;

	/**
	 * constructor
	 * @param name the name of the dataset
	 */
	public BoopingSite(String name) {
		hotels = HotelDataset.getHotels(name);
	}

	/*
	creates an array with the hotels in the given city (the sort needs array). If the flag is true adds only
	the hotels in the city, else adds all of the hotels in the given file.
	 */
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

	/*
	sorts the given array with the given comparator. If the flag is true - reverses the result (for the
	rating sort).
	 */
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

	/*
	checks if the given coordinates are valid
	 */
	private int checkCoordinates(double latitude, double longitude) {
		return (((latitude > 90) | (latitude < -90) | (longitude > 180) | (longitude < -180)) ? -1 : 0);
	}

	/**
	 * Sorts the hotels in the city according to the rating field
	 * @param city the given city
	 * @return sorted array of the hotels in the city according to the rating field.
	 */
	public Hotel[] getHotelsInCityByRating(String city) {
		ArrayList<Hotel> hotelsInTheCity = createCityHotelsArray(city, true);
		return sortAndReturnArray(hotelsInTheCity, new SortByRating(), true);
	}

	/**
	 * Sorts the hotels in the dataset according to the rating field
	 * @param latitude coordinate
	 * @param longitude coordinate
	 * @return sorted array of the hotels in the given dataset according to the location field.
	 */
	public Hotel[] getHotelsByProximity(double latitude, double longitude) {
		if (checkCoordinates(latitude, longitude) == -1) {
			return new Hotel[0];
		}
		ArrayList<Hotel> hotelsInTheCity = createCityHotelsArray("", false);
		return sortAndReturnArray(hotelsInTheCity, new SortByProximity(latitude, longitude), false);
	}

	/**
	 * Sorts the hotels in the city according to the rating field
	 * @param latitude coordinate
	 * @param longitude coordinate
	 * @return sorted array of the hotels in the given city according to the location field.
	 */
	public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude) {
		if (checkCoordinates(latitude, longitude) == -1) {
			return new Hotel[0];
		}
		ArrayList<Hotel> hotelsInTheCity = createCityHotelsArray(city, true);
		return sortAndReturnArray(hotelsInTheCity, new SortByProximity(latitude, longitude), false);
	}
}
