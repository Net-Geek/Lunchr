package edu.uc.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

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

    public YelpServiceImpl(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.service = new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(consumerKey)
                        .apiSecret(consumerSecret).build();
        this.accessToken = new Token(token, tokenSecret);
    }

    @Override
    public String getReview(String business) {
        return null;
    }

    @Override
    public void setLimitReviews(Integer limit) { }

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
     * This can give you an idea of how many businesses are in the area that you are located
     * based off of zip code, address, city, or state.
     * @param term
     * @param location
     * @return
     */
    @Override
    public String searchBusinessByTermAndLocation(String term, String location) {
        OAuthRequest request = createOAuthRequest(SEARCH_PATH + "term=" + term + "&location=" + location);
        return sendRequestAndGetResponse(request);
    }

    @Override
    public String searchBusinessByTermLocationAndLimit(String term, String location, String limit) {
        OAuthRequest request = createOAuthRequest(SEARCH_PATH + "term=" + term + "&location=" + location + "&limit=" + limit);
        return sendRequestAndGetResponse(request);
    }

    @Override
    public Double getRating(String businessID) {
        String business = searchByBusinessId(businessID);
        JSONObject queried = queryJSON(business);
        return (Double) queried.get("rating");
    }

    @Override
    public boolean isThereADeal(String business) {
        return false;
    }

    @Override
    public String searchByBusinessId(String businessID) {
        OAuthRequest request = createOAuthRequest(BUSINESS_PATH + "/" + businessID);
        return sendRequestAndGetResponse(request);
    }

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

    /**
     * This will convert the JSON Page into a HashMap with the key being the label and
     * the value the actual value in the JSON page.
     * @param JSONBody actual JSON body page
     * @return the response which will be a HashMap
     */
    public static JSONObject queryJSON(String JSONBody) {
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
