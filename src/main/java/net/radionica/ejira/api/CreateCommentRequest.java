package net.radionica.ejira.api;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateCommentRequest {

    @NotEmpty(message = "Text cannot be empty")
    private String _text;

    @NotNull
    private Long _ticketID;

    public String getText() {
	return _text;
    }

    public void setText(String text) {
	_text = text;
    }

    public Long getTicketID() {
	return _ticketID;
    }

    public void setTicketID(Long ticketID) {
	_ticketID = ticketID;
    }

}
