package net.radionica.ejira.service;

import java.security.NoSuchAlgorithmException;

import net.radionica.ejira.api.RegisterUserRequest;
import net.radionica.ejira.model.Role;
import net.radionica.ejira.model.User;


public interface UserService {

    boolean isValidCredentials(User user) throws NoSuchAlgorithmException;

    void checkPasswordMatching(User user);

    void setCryptedPassword(User user) throws NoSuchAlgorithmException;

    void setRole(User user);

    void checkUniqueMailAndUsername(User user);

    boolean registerUser(User user) throws NoSuchAlgorithmException;

    boolean validateUser(RegisterUserRequest user);

    Iterable<User> getAllUsers();

    String loginUser(User user) throws NoSuchAlgorithmException;

    Role getUserRole(Long userID);

    void modifyUser(User user);

    void logoutUser(String token);

    User getUserByUserID(Long userID);
    
    boolean editRoleUser(long id);
}