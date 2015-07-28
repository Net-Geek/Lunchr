package edu.uc.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
     * decides if a business has been claimed on Yelp
     * @param business that you are searching for
     * @return boolean indicating the above statement
     */
    public boolean isRestaurantOnYelp(String business);


    /**
     * @param longitude the longitude of the current location
     * @param latitude the latitude of the current location
     * sets the location of where you want to search
     */
    public void setLocation(Double longitude, Double latitude);


    /**
     *
     * @param term specific term you are looking for
     * @param location location you want the search to include
     * @return array of businesses based off of the location and term provided
     */
    public JSONArray findBusinessesByTermAndLocation(String term, String location);


    /**
     * see's if there is a deal for the business on that particular day
     * @param business that you are seeing if there is a deal or not
     * @return whether or not the business is having a deal
     */
    public boolean isThereADeal(String business);

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
