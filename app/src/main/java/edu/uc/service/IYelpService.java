package edu.uc.service;

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
     * Sets the number of reviews that you want returned
     * @param limit number of reviews you want to limit
     */
    public void setLimitReviews(Integer limit);


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
     * @param business that you are setting
     * sets the variable to the term that you want to search for
     */
    public String searchBusinessByTermAndLocation(String term, String location);


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

    public String searchBusinessByTermLocationAndLimit(String term, String location, String limit);

    public Double getRating(String businessID);

}
