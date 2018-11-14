package net.radionica.ejira.api;

public class ApiError{

	private String _message;
	
	public ApiError(String message) {
		_message = message;
	}
	
	public String getMessage() {
		return _message;
	}
	public void setMessage(String message) {
		_message = message;
	}
	
}