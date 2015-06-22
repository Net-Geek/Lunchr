package edu.uc.service;

/**
 * Created by Tony on 6/21/2015.
 */
public class MatchServiceStub implements IMatchService {
    @Override
    public boolean isThereMatch() {
        return false;
    }

    @Override
    public void fetchMatch() {
        // not sure yet what this will return so for now we just put void
    }
}
