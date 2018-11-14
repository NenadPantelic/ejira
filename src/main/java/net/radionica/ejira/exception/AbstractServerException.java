package net.radionica.ejira.exception;

public class AbstractServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _errorMessage;
	public AbstractServerException(String message) {
		_errorMessage = message;
	}

	@Override
	public String getMessage() {
		return _errorMessage;
	}
	
}
