package edu.uc.tests;

import junit.framework.TestCase;

import org.json.simple.JSONArray;

import java.util.HashMap;

import edu.uc.service.IYelpService;
import edu.uc.service.YelpServiceImpl;
//import edu.uc.service.YelpServiceStub;

/**
 * Created by Tony on 7/13/2015.
 */
public class IYelpServiceTest extends TestCase {
    IYelpService yelpService;

    public void setUp() throws Exception {
        yelpService = new YelpServiceImpl();
    }

    public void testFindBusinessesByTermAndLocation() throws Exception {
        JSONArray businesses = yelpService.findBusinessesByTermAndLocation("food", "cincinnati");
        assertTrue(20 == businesses.size());

        assertNull(yelpService.findBusinessesByTermAndLocation("", ""));
    }

    public void testGetBusinessID() throws Exception {
        JSONArray businesses = yelpService.findBusinessesByTermAndLocation("skyline", "cincinnati");
        Object business = businesses.get(0);
        assertEquals("skyline-chili-cincinnati-8", yelpService.getBusinessID((HashMap)business));

        assertNull(yelpService.getBusinessID(new HashMap()));
    }

    public void testSearchByBusinessID() throws Exception {
        assertNotNull(yelpService.searchByBusinessId(businessID()));
    }

    public void testIsRestaurantOnYelp() throws Exception {
        assertFalse(yelpService.isRestaurantOnYelp(""));
    }

    public void testSetLocation() throws Exception {
        yelpService.setLocation(4.0, 4.0);
    }

    public void testGetRating() throws Exception {
        assertEquals(4.0, yelpService.getRating(businessID()));

        assertNull(yelpService.getRating(""));
    }

    public void testGetRatingImage() throws Exception {
        assertEquals("http://s3-media2.fl.yelpcdn.com/assets/2/www/img/ccf2b76faa2c/ico/stars/v1/stars_large_4.png", yelpService.getRatingImage(businessID()));

        assertNull(yelpService.getRatingImage(""));
    }

    public void testIsThereADeal() throws Exception {
        assertFalse(yelpService.isThereADeal(""));
    }


    public void testGetReview() throws Exception {
        assertEquals("Can I tell you how much we LOVE Skyline Chili????\n" +
                "\n" +
                "My kids had never tried it.  Althought we really wanted to stop in Cinci to have Graeters, I decided I...", yelpService.getReview(businessID()));

        assertNull(yelpService.getReview(""));
    }

    private String businessID() {
        JSONArray businesses = yelpService.findBusinessesByTermAndLocation("skyline", "cincinnati");
        Object business = businesses.get(0);
        return yelpService.getBusinessID((HashMap)business);
    }
}
