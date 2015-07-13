package edu.uc.tests;

import junit.framework.TestCase;

import edu.uc.service.IMatchService;
import edu.uc.service.MatchServiceStub;

/**
 * Created by Tony on 7/13/2015.
 */
public class IMatchServiceTest extends TestCase {
    IMatchService matchService;

    @Override
    public void setUp() throws Exception {
        matchService = new MatchServiceStub();
    }

    public void testIsThereAMatch() throws Exception {
        assertFalse(matchService.isThereMatch());
    }

    public void testFetchMatch() throws Exception{

    }

}
