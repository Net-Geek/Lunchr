package edu.uc.service;

/**
 * Created by Tony on 6/21/2015.
 */
public class YelpServiceStub implements IYelpService {
    @Override
    public String getReview(String business) {
        String review = null;
        if(business != null && business.length() > 0) {
            // get the review
            // review will probably call something that will return the review
           review = "";
        }
        return review;
    }

    @Override
    public void setLimitReviews(Integer limit) {
        if(limit != null) {
            // set the limit to the number of reviews specified
        } else {
            throw new RuntimeException("Limit was not valid, please try again.");
        }
    }

    @Override
    public boolean isRestaurantOnYelp(String business) {
        boolean isOnYelp = false;
        if(business != null && business.length() > 0) {
            // see if business is on yelp
            // isOnYelp will probably call something that will return whether or not the restaurant is on Yelp
            isOnYelp = true;
        }
        return isOnYelp;
    }

    @Override
    public void setLocation(Double longitude, Double latitude) {
        if(longitude != null && latitude != null) {
            // set the Location of the person
        } else {
            throw new RuntimeException("The longitude and latitude were not valid, please try again");
        }
    }

    @Override
    public String searchBusinessByTermAndLocation(String business, String location) {
        if(business != null && business.length() > 0) {
            // set the business for search
        } else {
            throw new RuntimeException("Business was not valid, please try again");
        }
        return "";
    }

    @Override
    public boolean isThereADeal(String business) {
        boolean isDeal = false;
        if(business != null && business.length() > 0) {
            // check to see if there is a deal
            // isDeal will probably call something that will return whether or not there is a deal
            isDeal = true;
        }
        return isDeal;
    }

    @Override
    public String searchByBusinessId(String businessID) {
        return null;
    }

    @Override
    public String searchBusinessByTermLocationAndLimit(String term, String location, String limit) {
        return null;
    }

    @Override
    public Double getRating(String businessID) {
        return null;
    }
}

