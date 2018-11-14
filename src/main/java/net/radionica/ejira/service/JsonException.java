package net.radionica.ejira.service;

public class JsonException extends Exception {

    /**
     * 
     */
    private String _message;
    private static final long serialVersionUID = 1L;
    

    public JsonException(String message) {
	super(message);
	_message = message;
	// TODO Auto-generated constructor stub
    }

    @Override
    public String getMessage() {
	return "Error message: " + _message;
    }
    
//    public void printError() {
//	System.out.println(String.format("Error message: " + _message));
//    }

}
