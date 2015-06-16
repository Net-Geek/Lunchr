package edu.uc.service;

/**
 * Created by Anthony on 6/16/2015.
 */
public interface MatchService {

    /**
     * fetches a match for a person
     * @return boolean if there is a match
     */
    boolean isThereMatch();


    /**
     * actually gets the match
     */
    void fetchMatch();
}
