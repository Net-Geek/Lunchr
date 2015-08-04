package edu.uc.lunchr.service;

import org.json.simple.JSONArray;

import java.util.HashMap;

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
    public String getAddress(String businessID) {
        String address = null;
        if(businessID != null && businessID.length() > 0) {
            address = "555 Smith Street";
        }
        return address;
    }

    @Override
    public String getPhoneNumber(String businessID) {
        String number = null;
        if(businessID != null && businessID.length() > 0) {
            number = "555-555-5555";
        }
        return number;
    }

    @Override
    public String getBusinessName(String businessID) {
        String name = null;
        if(businessID != null && businessID.length() > 0) {
            name = "LaRosa's";
        }
        return name;
    }

    @Override
    public JSONArray findBusinessesByTermAndLocation(String business, String location) {
        if(business != null && business.length() > 0) {
            // set the business for search
        } else {
            throw new RuntimeException("Business was not valid, please try again");
        }
        return new JSONArray();
    }

    @Override
    public String searchByBusinessId(String businessID) {
        String business = null;
        if(businessID != null && businessID.length() > 0) {
            business = "Skyline";
        }
        return business;
    }

    @Override
    public Double getRating(String businessID) {
        Double rating = null;
        if(businessID != null && businessID.length() > 0) {
            rating = 5.0;
        }
        return rating;
    }

    @Override
    public String getRatingImage(String businessID) {
        String imageURL = null;
        if(businessID != null && businessID.length() > 0) {
            imageURL = "https://www.blahblahblah.com/image.jpg";
        }
        return imageURL;
    }

    @Override
    public String getBusinessID(HashMap business) {
        String businessID = null;
        if(!business.isEmpty()) {
            businessID = "skyline-chili-cincinnati-8";
        }
        return businessID;
    }
}

