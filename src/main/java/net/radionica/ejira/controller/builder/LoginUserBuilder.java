package net.radionica.ejira.controller.builder;

import java.security.NoSuchAlgorithmException;

import net.radionica.ejira.model.User;
import net.radionica.ejira.service.UserService;

public class LoginUserBuilder {

    User _user;

	public static LoginUserBuilder builder() {
		return new LoginUserBuilder();
	}

	private LoginUserBuilder() {
		_user = new User();
	}

	public LoginUserBuilder username(String userName) {
		_user.setUsername(userName);
		return this;
	}

	public LoginUserBuilder password(String pass) {
		_user.setPassword(pass);
		return this;
	}
	
	public String execute(UserService service) throws NoSuchAlgorithmException{
		return service.loginUser(_user);
	}
}
