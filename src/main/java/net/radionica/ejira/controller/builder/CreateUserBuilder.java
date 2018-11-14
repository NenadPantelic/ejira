package net.radionica.ejira.controller.builder;

import java.security.NoSuchAlgorithmException;
import net.radionica.ejira.model.User;
import net.radionica.ejira.service.UserService;

public class CreateUserBuilder {
	User _user;

	public static CreateUserBuilder builder() {
		return new CreateUserBuilder();
	}

	private CreateUserBuilder() {
		_user = new User();
	}

	public CreateUserBuilder username(String userName) {
		_user.setUsername(userName);
		return this;
	}

	public CreateUserBuilder password(String pass) {
		_user.setPassword(pass);
		return this;
	}
	
	
	public CreateUserBuilder repeatedPassword(String rpass) {
		_user.setRepeatedPassword(rpass);
		return this;
	}
	
	public CreateUserBuilder email(String Email) {
		_user.setEmail(Email);
		return this;
	}
	
	public CreateUserBuilder firstname(String fname) {
		_user.setFirstname(fname);
		return this;
	}
	
	public CreateUserBuilder lastname(String lname) {
		_user.setLastname(lname);
		return this;
	}

	
	public boolean execute(UserService service) throws NoSuchAlgorithmException{
		return service.registerUser(_user);
	}
}



