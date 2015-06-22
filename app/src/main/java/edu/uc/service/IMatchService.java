package edu.uc.service;

/**
 * Created by Anthony on 6/16/2015.
 */
public interface IMatchService {

    /**
     * fetches a match for a person
     * @return boolean if there is a match
     */
    public boolean isThereMatch();


    /**
     * actually gets the match
     * not sure what it will return yet.
     */
    public void fetchMatch();
}
