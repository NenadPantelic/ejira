package net.radionica.ejira.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.radionica.ejira.api.LoginUserRequest;
import net.radionica.ejira.api.RegisterUserRequest;
import net.radionica.ejira.api.response.LoginResponse;
import net.radionica.ejira.controller.aop.UserRole;
import net.radionica.ejira.controller.builder.CreateUserBuilder;
import net.radionica.ejira.controller.builder.LoginUserBuilder;
import net.radionica.ejira.controller.interceptor.LogInterceptor;
import net.radionica.ejira.exception.InvalidCredentialsException;
import net.radionica.ejira.model.Role;
import net.radionica.ejira.model.User;
import net.radionica.ejira.service.UserService;
import net.radionica.ejira.util.SecurityUtil;

@RestController
public class UserController {
    @Autowired
    private UserService _userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<User> getAllUsers() {
	return getUserService().getAllUsers();

    }

    public UserService getUserService() {
	return _userService;
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public LoginResponse loginUser(@RequestBody LoginUserRequest user) throws NoSuchAlgorithmException {
	LoginResponse resp = new LoginResponse();
	resp.setToken(LoginUserBuilder.builder().username(user.getUsername()).password(user.getPassword()).execute(_userService));
	return resp;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public boolean registerUser(@RequestBody RegisterUserRequest user) throws NoSuchAlgorithmException {

	if(_userService.validateUser(user) == true) {
	    return CreateUserBuilder.builder().firstname(user.getFirstname()).lastname(user.getLastname())
		    .email(user.getEmail()).username(user.getUsername()).password(user.getPassword())
		    .repeatedPassword(user.getRepeatedPassword()).execute(_userService);
	}
	else {
	    return false;
	}
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editUser(@RequestBody User user) {
	getUserService().modifyUser(user);
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public boolean logoutUser() {
	getUserService().logoutUser(LogInterceptor.context.get().getToken());
	return true;
    }
    
    @UserRole(name = Role.PROJECT_MANAGER)
    @RequestMapping(value = "/users/editRole/{id}", method = RequestMethod.POST)
    public boolean editRoleUser(@PathVariable("id") long id) {
	return getUserService().editRoleUser(id);	
    }
}
