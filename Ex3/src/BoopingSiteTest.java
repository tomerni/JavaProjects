import oop.ex3.searchengine.*;
import org.junit.*;

import static org.junit.Assert.*;

public class BoopingSiteTest {

	private final BoopingSite boop = new BoopingSite("hotels_tst1.txt");
	private final BoopingSite boopFull = new BoopingSite("hotels_dataset.txt");
	private final BoopingSite boop2 = new BoopingSite("hotels_tst2.txt");

	@Test
	public void ratingSortTest() {
		Hotel[] hotels = boop.getHotelsInCityByRating("manali");
		assertEquals("apple inn cottage", hotels[0].getPropertyName()); // top rating and name
		assertEquals("woodyvu mansari cottage", hotels[hotels.length - 1].getPropertyName()); // last

		// same rating (both 5) but different names
		assertEquals("apple inn cottage", hotels[0].getPropertyName());
		assertEquals("country cottage manali", hotels[1].getPropertyName());

		Hotel[] hotels1 = boop.getHotelsInCityByRating("jerusalem");
		assertEquals(0, hotels1.length); // returning empty array
	}


	@Test
	public void sortProximityCityTest() {
		Hotel[] hotels = boop.getHotelsInCityByProximity("manali", 32, 77);

		assertEquals("bazira cottages", hotels[0].getPropertyName()); // closest
		assertEquals("solang cottage", hotels[hotels.length - 1].getPropertyName()); // farthest

		// same proximity (both 0.2907864282029195) but different POI
		assertEquals("apple inn cottage", hotels[21].getPropertyName()); // 19 POI
		assertEquals("sunrise cottage", hotels[20].getPropertyName()); // 7 POI

		Hotel[] hotels1 = boop.getHotelsInCityByProximity("jerusalem", 32, 77); //invalid
		// city
		assertEquals(0, hotels1.length); // returning empty array

		Hotel[] hotels2 = boop.getHotelsInCityByProximity("manali", 100, 77); //invalid
		assertEquals(0, hotels2.length); // returning empty array
		Hotel[] hotels3 = boop.getHotelsInCityByProximity("manali", 32, -200); //invalid
		assertEquals(0, hotels3.length); // returning empty array
	}


	@Test
	public void sortProximityTestEmptyFile() {
		Hotel[] hotels = boop2.getHotelsByProximity(32, 77); // hotels_tst2 is empty

		assertEquals(0, hotels.length);
	}

	@Test
	public void ratingSortTestFull() {
		Hotel[] hotels = boopFull.getHotelsInCityByRating("goa");

		assertEquals("camron guest house", hotels[0].getPropertyName()); // top rating and name
		assertEquals("wanderlust goa retreat", hotels[hotels.length - 1].getPropertyName()); // last

		// same rating (both 5) but different names
		assertEquals("camron guest house", hotels[0].getPropertyName());
		assertEquals("casa anjuna-boutique resort", hotels[1].getPropertyName());

		Hotel[] hotels2 = boop.getHotelsInCityByRating("jerusalem");
		assertEquals(0, hotels2.length); // returning empty array
	}

	@Test
	public void sortProximityCityTestFull() {
		Hotel[] hotels = boopFull.getHotelsInCityByProximity("delhi", 32, 77);

		assertEquals("tivoli grand resort hotel", hotels[0].getPropertyName()); // closest
		assertEquals("v resorts farm stay", hotels[hotels.length - 1].getPropertyName()); // farthest

		Hotel[] hotels1 = boopFull.getHotelsInCityByProximity("jerusalem", 32, 77);
		assertEquals(0, hotels1.length); // returning empty array
	}


	@Test
	public void sortProximityTestFull() {
		Hotel[] hotels = boopFull.getHotelsByProximity(32, 77);


		assertEquals("upadhyay cottages", hotels[0].getPropertyName()); // closest
		assertEquals("tsg aura", hotels[hotels.length - 1].getPropertyName()); // farthest

		// same proximity (both 0.2997962565585185) but different POI
		assertEquals("city heart hotel", hotels[41].getPropertyName()); // 1 POI
		assertEquals("hotel shree vinayak", hotels[42].getPropertyName()); // 0 POI

		Hotel[] hotels1 = boop.getHotelsInCityByProximity("jerusalem", 32, 77);
		assertEquals(0, hotels1.length); // returning empty array
	}
}
