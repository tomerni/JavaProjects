import oop.ex3.searchengine.*;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * implements the Booping site tests
 */
public class BoopingSiteTest {

    private final BoopingSite boop = new BoopingSite("hotels_tst1.txt");
    private final BoopingSite boopFull = new BoopingSite("hotels_dataset.txt");
    private final BoopingSite boop2 = new BoopingSite("hotels_tst2.txt");

    /**
     * tests the rating sort with dataset hotels_tst1
     */
    @Test
    public void ratingSortTest() {
        Hotel[] hotels = boop.getHotelsInCityByRating("manali");
        for (int i = 1; i < hotels.length; i++) {
            assertTrue("ratingSortTest(BoopingSiteTest): rating fail",
                    hotels[i - 1].getStarRating() >= hotels[i].getStarRating());
            if (hotels[i - 1].getStarRating() == hotels[i].getStarRating()) {
                assertTrue("ratingSortTestFull(BoopingSiteTest): same rating but different name fail",
                        0 < hotels[i].getPropertyName().compareTo(hotels[i - 1].getPropertyName()));
            }
        }

        Hotel[] hotels1 = boop.getHotelsInCityByRating("jerusalem");
        assertEquals("ratingSortTest(BoopingSiteTest): invalid city failure", 0,
                hotels1.length); // returning empty array

        Hotel[] hotels2 = boop.getHotelsInCityByRating(null); // null city
        assertEquals(0, hotels2.length); // returning empty array
    }

    /**
     * tests the proximity sort by city with dataset hotels_tst1
     */
    @Test
    public void sortProximityCityTest() {
        Hotel[] hotels = boop.getHotelsInCityByProximity("manali", 32, 77);

        for (int i = 1; i < hotels.length; i++) {
            double aDist = Math.sqrt(Math.pow(hotels[i - 1].getLongitude() - 77, 2) +
                    Math.pow(hotels[i - 1].getLatitude() - 32, 2));
            double bDist = Math.sqrt(Math.pow(hotels[i].getLongitude() - 77, 2) +
                    Math.pow(hotels[i].getLatitude() - 32, 2));
            assertTrue("sortProximityCityTest(BoopingSiteTest): sorting failure", aDist <= bDist);
            if (aDist == bDist) {
                assertTrue("sortProximityCityTest(BoopingSiteTest): POI failure",
                        hotels[i - 1].getNumPOI() >= hotels[i].getNumPOI());
            }
        }

        //invalid city
        Hotel[] hotels1 = boop.getHotelsInCityByProximity("jerusalem", 32, 77);
        assertEquals("sortProximityCityTest(BoopingSiteTest): invalid city failure", 0,
                hotels1.length); // returning empty array

        //invalid coordinates
        Hotel[] hotels2 = boop.getHotelsInCityByProximity("manali", 100, 77);
        assertEquals("sortProximityCityTest(BoopingSiteTest): invalid coordinates", 0,
                hotels2.length); // returning empty array
        Hotel[] hotels3 = boop.getHotelsInCityByProximity("manali", 32, -200);
        assertEquals("sortProximityCityTest(BoopingSiteTest): invalid coordinates", 0,
                hotels3.length); // returning empty array

        Hotel[] hotels4 = boop.getHotelsInCityByRating(null); // null city
        assertEquals("sortProximityCityTest(BoopingSiteTest): null city", 0,
                hotels4.length); // returning empty array
    }

    /**
     * tests the proximity sort with dataset hotels_tst2 which is empty
     */
    @Test
    public void sortProximityTestEmptyFile() {
        Hotel[] hotels = boop2.getHotelsByProximity(32, 77); // hotels_tst2 is empty

        assertEquals(0, hotels.length);
    }

    /**
     * tests the rating sort with dataset hotels_tst2 which is empty
     */
    @Test
    public void sortRatingTestEmptyFile() {
        Hotel[] hotels = boop2.getHotelsInCityByRating("manali"); // hotels_tst2 is empty

        assertEquals(0, hotels.length);
    }

    /**
     * tests the rating sort with dataset hotels_dataset
     */
    @Test
    public void ratingSortTestFull() {
        Hotel[] hotels = boopFull.getHotelsInCityByRating("goa");

        for (int i = 1; i < hotels.length; i++) {
            assertTrue("ratingSortTestFull(BoopingSiteTest): rating fail",
                    hotels[i - 1].getStarRating() >= hotels[i].getStarRating());
            if (hotels[i - 1].getStarRating() == hotels[i].getStarRating()) {
                assertTrue("ratingSortTestFull(BoopingSiteTest): same rating but different name fail",
                        0 < hotels[i].getPropertyName().compareTo(hotels[i - 1].getPropertyName()));
            }
        }

        Hotel[] hotels2 = boop.getHotelsInCityByRating("jerusalem");
        assertEquals("ratingSortTestFull(BoopingSiteTest): invalid city failure", 0,
                hotels2.length); // returning empty array
    }

    /**
     * tests the proximity sort by city with dataset hotels_dataset
     */
    @Test
    public void sortProximityCityTestFull() {
        Hotel[] hotels = boopFull.getHotelsInCityByProximity("delhi", 32, 77);

        for (int i = 1; i < hotels.length; i++) {
            double aDist = Math.sqrt(Math.pow(hotels[i - 1].getLongitude() - 77, 2) +
                    Math.pow(hotels[i - 1].getLatitude() - 32, 2));
            double bDist = Math.sqrt(Math.pow(hotels[i].getLongitude() - 77, 2) +
                    Math.pow(hotels[i].getLatitude() - 32, 2));
            assertTrue("sortProximityCityTestFull(BoopingSiteTest): sorting failure", aDist <= bDist);
            if (aDist == bDist) {
                assertTrue("sortProximityCityTestFull(BoopingSiteTest): POI failure",
                        hotels[i - 1].getNumPOI() >= hotels[i].getNumPOI());
            }
        }

        Hotel[] hotels1 = boopFull.getHotelsInCityByProximity("jerusalem", 32, 77);
        assertEquals("sortProximityCityTestFull(BoopingSiteTest): invalid city failure", 0,
                hotels1.length); // returning empty array
    }

    /**
     * tests the proximity sort with dataset hotels_dataset
     */
    @Test
    public void sortProximityTestFull() {
        Hotel[] hotels = boopFull.getHotelsByProximity(32, 77);

        for (int i = 1; i < hotels.length; i++) {
            double aDist = Math.sqrt(Math.pow(hotels[i - 1].getLongitude() - 77, 2) +
                    Math.pow(hotels[i - 1].getLatitude() - 32, 2));
            double bDist = Math.sqrt(Math.pow(hotels[i].getLongitude() - 77, 2) +
                    Math.pow(hotels[i].getLatitude() - 32, 2));
            assertTrue("sortProximityCityTestFull(BoopingSiteTest): sorting failure", aDist <= bDist);
            if (aDist == bDist) {
                assertTrue("sortProximityCityTestFull(BoopingSiteTest): POI failure",
                        hotels[i - 1].getNumPOI() >= hotels[i].getNumPOI());
            }
        }

        Hotel[] hotels1 = boop.getHotelsInCityByProximity("jerusalem", 32, 77);
        assertEquals("sortProximityTestFull(BoopingSiteTest): invalid city failure", 0,
                hotels1.length); // returning empty array
    }
}
