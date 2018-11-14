package net.radionica.ejira.api;

import javax.validation.constraints.NotNull;

public class TicketIDRequest {
	
	@NotNull (message = "There is no ticket ID!")
	private Long _ticketID;

	public Long getTicketID() {
		return _ticketID;
	}

	public void setTicketID(Long ticketID) {
		_ticketID = ticketID;
	}

}
