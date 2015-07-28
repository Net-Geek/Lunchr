package edu.uc.service;

import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tony on 7/21/2015.
 */
public class YelpServiceImpl implements IYelpService {

    private static final String API_HOST = "api.yelp.com";
    private static final String SEARCH_PATH = "/v2/search?";
    private static final String BUSINESS_PATH = "/v2/business";
    private static Double LONGITUDE = 0.0;
    private static Double LATITUDE = 0.0;

    private static final String CONSUMER_KEY = "Q--QTZexuMoLJ-mtRNxQvQ";
    private static final String CONSUMER_SECRET = "ZhuWE6w9F8LZKj0Z2dHT4-Mu2Mw";
    private static final String TOKEN = "O0wus9GeCU-gP2z7b1cZca1gSMUYiiYf";
    private static final String TOKEN_SECRET = "U4sd_TRtNuj4sQHAue-zDYwJ_zo";

    OAuthService service;
    Token accessToken;

    public YelpServiceImpl() {
        this.service = new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(CONSUMER_KEY)
                        .apiSecret(CONSUMER_SECRET).build();
        this.accessToken = new Token(TOKEN, TOKEN_SECRET);
    }

    /**
     * Will search for businesses based off of term and location given
     * @param term for what you are searching for
     * @param location where you are searching for
     * @return a JSONArray that contains all the businesses that were found based off of the given criteria.
     *         This array will be filled with HashMaps
     */
    @Override
    public JSONArray findBusinessesByTermAndLocation(String term, String location) {
        JSONArray businessesArray = null;
        if((term != null && location != null) && (term.length() > 0 && location.length() > 0)) {
            OAuthRequest request = createOAuthRequest(SEARCH_PATH + "term=" + term + "&location=" + location);
            String businesses = sendRequestAndGetResponse(request);
            JSONObject queried = queryJSON(businesses);
            businessesArray = (JSONArray) queried.get("businesses");
        }
        return businessesArray;
    }

    /**
     * Returns the business ID for a business specifically to use for the Yelp API
     * @param business should be a HashMap that contains all the criteria for one business.
     *                 You can find a bunch of businesses by calling the findBusinessesByTermAndLocation
     *                 and then use one of the indexes, which is a HashMap for one business, and use that
     *                 as the parameters here.
     * @return the business ID
     */
    @Override
    public String getBusinessID(HashMap business){
        String businessID = null;
        if(!business.isEmpty()) {
            businessID = (String) business.get("id");
        }
        return businessID;
    }

    /**
     * Actually goes out and gets the JSON text of the business
     * @param businessID the ID of the business you want to search for
     * @return String representation of the JSON
     */
    @Override
    public String searchByBusinessId(String businessID) {
        OAuthRequest request = createOAuthRequest(BUSINESS_PATH + "/" + businessID);
        return sendRequestAndGetResponse(request);
    }

    @Override
    public boolean isRestaurantOnYelp(String business) {
        return false;
    }

    @Override
    public void setLocation(Double longitude, Double latitude) {
        this.LONGITUDE = longitude;
        this.LATITUDE = latitude;
    }

    /**
     * Gets the rating of the business
     * @param businessID business ID you want the rating for
     * @return a double representing the business rating
     */
    @Override
    public Double getRating(String businessID) {
        Double ratingValue = null;
        if(businessID != null && businessID.length() > 0) {
            String business = searchByBusinessId(businessID);
            JSONObject queried = queryJSON(business);
            ratingValue = (Double) queried.get("rating");
        }
        return ratingValue;
    }

    /**
     * Returns a url for the image of the rating
     * @param businessID Business you want the rating for
     * @return a url of the image
     */
    public String getRatingImage(String businessID) {
        String imageUrl = null;
        if(businessID != null && businessID.length() > 0) {
            String business = searchByBusinessId(businessID);
            JSONObject queried = queryJSON(business);
            imageUrl = (String) queried.get("rating_img_url_large");
        }
        return imageUrl;
    }

    @Override
    public boolean isThereADeal(String business) {
        return false;
    }

    /**
     * Returns only one review because that's how many are included in the original JSON
     * @param businessID business you want a review for
     * @return the review of the business based off of customers.
     */
    @Override
    public String getReview(String businessID) {
        String review = null;
        String business = searchByBusinessId(businessID);
        if(businessID != null && businessID.length() > 0) {
            JSONObject queried = queryJSON(business);
            Map<Object, Object> reviewMap;
            try {
                JSONArray arrayReview = (JSONArray) queried.get("reviews");
                reviewMap = (Map<Object, Object>) arrayReview.get(0);
                review = (String) reviewMap.get("excerpt");
            } catch (Exception e) {
                Log.w("getReview", e.getMessage());
                e.printStackTrace();
            }
        }
        return review;
    }

    /////////////////////////////////////  PRIVATE METHODS  /////////////////////////////////////////

    private OAuthRequest createOAuthRequest(String path) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://" + API_HOST + path);
        return request;
    }

    private String sendRequestAndGetResponse(OAuthRequest request) {
        System.out.println("Querying " + request.getCompleteUrl() + " ...");
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    private JSONObject queryJSON(String JSONBody) {
        JSONParser parser = new JSONParser();
        JSONObject response = null;
        try {
            response = (JSONObject) parser.parse(JSONBody);
        } catch (ParseException pe) {
            System.out.println("Error: could not parse JSON response:");
            System.out.println(JSONBody);
            System.exit(1);
        }
        return response;
    }


}
