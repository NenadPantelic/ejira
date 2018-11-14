package net.radionica.ejira.api;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


public class RegisterUserRequest {
	
	    private String _firstname;
	    
	    private String _lastname;
	    
	    @Email(message="Invalid email.")
	    private String _email;
	    
	    @Size(min=5, max=10, message="Your username should be between 5 - 10 characters.")
	    private String _username;
	    
	    @Size(min=5, max=10, message="Your password should be between 5 - 10 characters.")
	    private String _password;
	    
	    private String _repeatedPassword;

		public String getFirstname() {
			return _firstname;
		}

		public void setFirstname(String _firstname) {
			this._firstname = _firstname;
		}

		public String getLastname() {
			return _lastname;
		}

		public void setLastname(String _lastname) {
			this._lastname = _lastname;
		}

		public String getEmail() {
			return _email;
		}

		public void setEmail(String _email) {
			this._email = _email;
		}

		public String getUsername() {
			return _username;
		}

		public void setUsername(String _username) {
			this._username = _username;
		}

		public String getPassword() {
			return _password;
		}

		public void setPassword(String _password) {
			this._password = _password;
		}

		public String getRepeatedPassword() {
			return _repeatedPassword;
		}

		public void setRepeatedPassword(String _repeatedPassword) {
			this._repeatedPassword = _repeatedPassword;
		}
	    
	    
}
