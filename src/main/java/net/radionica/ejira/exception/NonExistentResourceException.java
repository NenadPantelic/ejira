package net.radionica.ejira.exception;

public class NonExistentResourceException extends AbstractServerException {

    private static final long serialVersionUID = 1L;

    public NonExistentResourceException(String message) {
	super(message);
    }
    
}