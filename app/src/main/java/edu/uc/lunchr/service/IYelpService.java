package edu.uc.lunchr.service;

import org.json.simple.JSONArray;

import java.util.HashMap;

/**
 * Created by Tony on 6/15/2015.
 */
public interface IYelpService {

    /**
     * Returns the review that the user requested
     * @param business that you are searching for
     * @return currently don't know what this will return but when we figure it out we will change it
     */
    public String getReview(String business);

    /**
     * get the address of the business
     * @param businessID ID of the business you want the address from
     * @return the address
     */
    public String getAddress(String businessID);

        /**
         * Gets the phone number of the business
         * @param businessID id of the business you want the number from
         * @return the phone number
         */
    public String getPhoneNumber(String businessID);

    /**
     * gets the name of the business based off of the businessID.  Will be used mainly to
     * display the name of business on the UI
     * @param businessID businessID of the business
     * @return the name of the business
     */
    public String getBusinessName(String businessID);

    /**
     *
     * @param term specific term you are looking for
     * @param location location you want the search to include
     * @return array of businesses based off of the location and term provided
     */
    public JSONArray findBusinessesByTermAndLocation(String term, String location);

    /**
     * Returns a JSON page of material based of the ID
     * @param businessID the ID of the business you want to search for
     * @return
     */
    public String searchByBusinessId(String businessID);

    /**
     * Gets the rating of the business
     * @param businessID ID of the business
     * @return a Double value determining the rating of the business
     */
    public Double getRating(String businessID);

    /**
     * Gets the url of the image rating specified on the Yelp page.
     * @param businessID ID of the business you want the rating for
     * @return url of the rating
     */
    public String getRatingImage(String businessID);

    /**
     * Gets the business ID of the business
     * @param business HashMap that contains all the criteria for one business
     * @return the business ID
     */
    public String getBusinessID(HashMap business);

}
