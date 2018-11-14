package net.radionica.ejira.api.response;

import java.util.HashSet;
import java.util.Set;

import net.radionica.ejira.model.Project;
import net.radionica.ejira.model.Role;
import net.radionica.ejira.model.Ticket;
import net.radionica.ejira.model.User;

public class UserResponse {
	
	private long _userID;
	private String _firstname;
	private String _lastname;
	private String _email;
	private Role _role;
	private String _username;
	Set<Long> _ticketIDs;
	Set<Long> _createdTicketIDs;
	
	public UserResponse(User user) {
		_userID = user.getUserID();
		_firstname = user.getFirstname();
		_lastname = user.getLastname();
		_email = user.getEmail();
		_role = user.getRole();
		_username = user.getUsername();
		_ticketIDs = new HashSet<Long>();
		Set<Ticket> tickets = user.getTickets();
		if(tickets!=null) {
			for(Ticket ticket : tickets) {
				Long ticketID = ticket.getTicketID();
				_ticketIDs.add(ticketID);
			}
		}
		_createdTicketIDs = new HashSet<Long>();
		Set<Ticket> createdTickets = user.getCreatedTickets();
		if(createdTickets!=null) {
			for(Ticket ticket : createdTickets) {
				Long ticketID = ticket.getTicketID();
				_createdTicketIDs.add(ticketID);
			}
		}
	}
	
	public long getUserID() {
		return _userID;
	}
	public void setUserID(long userID) {
		_userID = userID;
	}
	public String getFirstname() {
		return _firstname;
	}
	public void setFirstname(String firstname) {
		_firstname = firstname;
	}
	public String getLastname() {
		return _lastname;
	}
	public void setLastname(String lastname) {
		_lastname = lastname;
	}
	public String getEmail() {
		return _email;
	}
	public void setEmail(String email) {
		_email = email;
	}
	public Role getRole() {
		return _role;
	}
	public void setRole(Role role) {
		_role = role;
	}
	public String getUsername() {
		return _username;
	}
	public void setUsername(String username) {
		_username = username;
	}

	public Set<Long> getTicketIDs() {
		return _ticketIDs;
	}

	public void setTicketIDs(Set<Long> ticketIDs) {
		_ticketIDs = ticketIDs;
	}

	public Set<Long> getCreatedTicketIDs() {
		return _createdTicketIDs;
	}

	public void setCreatedTicketIDs(Set<Long> createdTicketIDs) {
		_createdTicketIDs = createdTicketIDs;
	}

}
