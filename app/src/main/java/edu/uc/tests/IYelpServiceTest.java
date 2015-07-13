package edu.uc.tests;

import junit.framework.TestCase;

import edu.uc.service.IYelpService;
import edu.uc.service.YelpServiceStub;

/**
 * Created by Tony on 7/13/2015.
 */
public class IYelpServiceTest  extends TestCase {
    IYelpService yelpService;

    public void setUp() throws Exception {
        yelpService = new YelpServiceStub();
    }

    public void testGetReview() throws Exception {
        assertNull(yelpService.getReview(""));
        assertEquals("", yelpService.getReview("review"));
    }

    public void testSetLimitReviews() throws Exception {
        try {
            yelpService.setLimitReviews(null);
        } catch (Exception e) {
            assertEquals("Limit was not valid, please try again.", e.getMessage());
        }

        yelpService.setLimitReviews(5);
    }

    public void testIsRestaurantOnYelp() throws Exception {
        assertFalse(yelpService.isRestaurantOnYelp(""));
        assertTrue(yelpService.isRestaurantOnYelp("Skyline"));
    }

    public void testSetLocation() throws Exception {
        try {
            yelpService.setLocation(null, null);
        } catch (Exception e) {
            assertEquals("The longitude and latitude were not valid, please try again", e.getMessage());
        }
    }

    public void testSetBusinessForSearch() throws Exception {
        try {
            yelpService.setBusinessForSearch(null);
        } catch (Exception e) {
            assertEquals("Business was not valid, please try again", e.getMessage());
        }

        yelpService.setBusinessForSearch("Skyline");
    }

    public void testIsThereADeal() throws Exception {
        assertFalse(yelpService.isThereADeal(null));
        assertTrue(yelpService.isThereADeal("Skyline"));
    }
}
