package net.radionica.ejira.api.response;

import java.util.HashSet;
import java.util.Set;

import net.radionica.ejira.model.Comment;
import net.radionica.ejira.model.Project;
import net.radionica.ejira.model.Ticket;
import net.radionica.ejira.model.User;

public class ProjectResponse {
	
    private long _projectID;
    private String _name;
    private String _description;
    private long _ownerID;
    private Set<Long> _ticketIDs;
    
    public ProjectResponse(Project project) {
    	_projectID = project.getProjectID();
		_name = project.getName();
		_description = project.getDescription();
		_ownerID = project.getOwner().getUserID();
		_ticketIDs = new HashSet<Long>();
		Set<Ticket> tickets =  project.getTickets();
		if(tickets!=null) {
			for(Ticket ticket : tickets) {
				Long ticketID = ticket.getTicketID();
				_ticketIDs.add(ticketID);
			}
		}
	}
    
    
	public long getProjectID() {
		return _projectID;
	}
	public void setProjectID(long projectID) {
		_projectID = projectID;
	}
	public String getName() {
		return _name;
	}
	public void setName(String name) {
		_name = name;
	}
	public String getDescription() {
		return _description;
	}
	public void setDescription(String description) {
		_description = description;
	}
	public long getOwnerID() {
		return _ownerID;
	}
	public void setOwnerID(long ownerID) {
		_ownerID = ownerID;
	}
	public Set<Long> getTicketIDs() {
		return _ticketIDs;
	}
	public void setTicketIDs(Set<Long> ticketIDs) {
		_ticketIDs = ticketIDs;
	}

}
