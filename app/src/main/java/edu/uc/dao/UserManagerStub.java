package edu.uc.dao;

import edu.uc.dto.User;

/**
 * Created by matthewcorbett on 6/15/15.
 */
public class UserManagerStub implements IUserDAO {
    @Override
    public void createUser(User user) throws Exception {
        if (user != null) {
            //calls database
        } else {
            throw new Exception("Invalid user object");
        }
    }

    @Override
    public User fetchUser(String userId) throws Exception {
        User fauxUser = new User();
        fauxUser.setUserID(1);
        fauxUser.setPhotoID(2);
        fauxUser.setUserName("Abraham Lincoln");
        fauxUser.setDistance(3);
        fauxUser.setFoodPreference("Mexican");
        fauxUser.setLatitude(20.5);
        fauxUser.setLongitude(50.3);


        return fauxUser;
    }

    @Override
    public void updateUser(String userId, User user) throws Exception {
        if (userId != null) {
            //updates user based on their userId
        } else {
            throw new Exception("Could not update user");
        }


    }

    @Override
    public void deleteUser(String userId) throws Exception {
        if (userId != null){
            //deletes user based on their userId
        } else {
            throw new Exception("Could not delete user");
        }
    }
}
