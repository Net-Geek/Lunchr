package edu.uc.dao;

import edu.uc.dto.User;

/**
 * Created by Aaron on 6/16/2015.
 */
public interface IUserDAO {

    /**
     * Creates a user on the database.
     * @param user the user to save
     * @throws Exception if new user is not created.
     */
    public void createUser(User user) throws Exception;

    /**
     * Fetches a user from the database.
     * @param userId the userId of the user to retrieve
     * @throws Exception user could not be retrieved from the database.
     */
    public User fetchUser(String userId) throws Exception;

    /**
     * Updates a user in the database.
     * @param userId the userId of the user to update
     * @param user the updated user object to overwrite the current user object
     * @throws Exception user could not be updated in the database.
     */
    public void updateUser(String userId, User user) throws Exception;

    /**
     * Deletes a user from the database.
     * @param userId the userId of the user to delete
     * @throws Exception user could not be deleted from the database.
     */
    public void deleteUser(String userId) throws Exception;
}
