package net.radionica.ejira.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.radionica.ejira.api.RegisterUserRequest;
import net.radionica.ejira.controller.interceptor.LogInterceptor;
import net.radionica.ejira.dao.UserRepository;
import net.radionica.ejira.exception.InvalidCredentialsException;
import net.radionica.ejira.model.Role;
import net.radionica.ejira.model.User;
import net.radionica.ejira.service.UserService;
import net.radionica.ejira.util.SecurityUtil;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository _userRepository;

    public UserRepository getUserRepository() {
	return _userRepository;
    }

    public boolean isValidCredentials(User user) throws NoSuchAlgorithmException {
	String plainPassword = user.getPassword();
//	MessageDigest digest = MessageDigest.getInstance("SHA-256");
//	byte[] hash = digest.digest(plainPassword.getBytes(StandardCharsets.UTF_8));
//	String encoded = Base64.getEncoder().encodeToString(hash);
	User user1 = getUserRepository().findBy_username(user.getUsername());
	//BCrypt.checkpw(user1.getPassword(), plainPassword);
	return !(user1 == null || !BCrypt.checkpw(plainPassword, user1.getPassword()));

    }

    public void checkPasswordMatching(User user) throws InvalidCredentialsException {

	if (!user.getPassword().equals(user.getRepeatedPassword())) {
	    throw new InvalidCredentialsException("Passwords do not match!");
	}
    }

    public void setCryptedPassword(User user) throws NoSuchAlgorithmException {
	String plainPassword = user.getPassword();
//	MessageDigest digest = MessageDigest.getInstance("SHA-256");
//	byte[] hash = digest.digest(plainPassword.getBytes(StandardCharsets.UTF_8));
//	String encoded = Base64.getEncoder().encodeToString(hash);
	String encoded = BCrypt.hashpw(plainPassword, BCrypt.gensalt(13));

	user.setPassword(encoded);

    }

    public void setRole(User user) {
	Role role;

	if (getUserRepository().findBy_role(Role.PROJECT_MANAGER).isEmpty()) {

	    role = Role.PROJECT_MANAGER;
	} else {
	    role = Role.DEVELOPER;
	}
	user.setRole(role);
    }

    public void checkUniqueMailAndUsername(User user) throws InvalidCredentialsException {
	if (getUserRepository().findBy_email(user.getEmail()) != null
		|| getUserRepository().findBy_username(user.getUsername()) != null) {
	    throw new InvalidCredentialsException("Username/mail already exists in database!");
	}
    }

    public boolean registerUser(User user) throws NoSuchAlgorithmException {

	checkUniqueMailAndUsername(user);
	// checkMailAndUsernameValidity(user);
	checkPasswordMatching(user);
	setCryptedPassword(user);
	setRole(user);
	getUserRepository().save(user);
	return true;

    }

    public boolean validateUser(RegisterUserRequest user) {

	String message = "";
	String message1 = "";
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(user);
	for (ConstraintViolation<RegisterUserRequest> violation : constraintViolations) {
	    message += violation.getMessage() + " ";
	}
	if (message.length() != 0)
	    message1 = message.substring(0, message.length() - 1);

	if (constraintViolations.size() == 0) {
	    return true;
	} else {
	    throw new InvalidCredentialsException(message1);
	}
    }

    public Iterable<User> getAllUsers() {
	// TODO Auto-generated method stub
	return getUserRepository().findAll();
    }

    public String loginUser(User user) throws NoSuchAlgorithmException {

	if (!isValidCredentials(user)) {
	    throw new InvalidCredentialsException("Username/Password doesn't match");
	}
	String authToken = SecurityUtil.generateToken();
	SecurityUtil.CACHE.put(authToken, getUserRepository().findBy_username(user.getUsername()).getUserID());
	System.out.println(authToken);
	return authToken;

    }

    public Role getUserRole(Long userID) {
	return getUserRepository().findBy_userID(userID).getRole();
    }

    public void modifyUser(User user) {
	User user1 = getUserRepository().findBy_userID(LogInterceptor.context.get().getUserId());
	user1.setUsername(user.getUsername());
	user1.setPassword(user.getPassword());
	user1.setFirstname(user.getFirstname());
	user1.setLastname(user.getLastname());
	user1.setEmail(user.getEmail());
	getUserRepository().save(user1);
    }

    public void logoutUser(String token) {
	SecurityUtil.CACHE.invalidate(token);
    }

    public User getUserByUserID(Long userID) throws InvalidCredentialsException {
	return getUserRepository().findBy_userID(userID);
    }
    
    public boolean editRoleUser(long id)
    {
	User user1 = getUserRepository().findBy_userID(id);
	Role role;
	if(user1!=null && user1.getRole()==Role.DEVELOPER)
	{	
	    	role=Role.PROJECT_MANAGER;
		user1.setRole(role);
		getUserRepository().save(user1);
	    return true;
	}
	return false;
    }
}