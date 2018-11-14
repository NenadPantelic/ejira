package net.radionica.ejira.api.response;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import net.radionica.ejira.model.Comment;
import net.radionica.ejira.model.Ticket;
import net.radionica.ejira.model.TicketStatus;
import net.radionica.ejira.model.Type;

public class TicketResponse {

	private long _ticketID;
   
    private String _name;

    private String _description;

    private Date _createdOn;

    private Date _closedOn;

    private int _weight;

    private Type _type;

    private TicketStatus _status;
    
    private Set<Long> _commentsIDs;

    private long _issuerID;

    private long _assigneeID;

    private long _projectID;
    
    public TicketResponse(Ticket ticket) {
		_ticketID = ticket.getTicketID();
		_name = ticket.getName();
		_description = ticket.getDescription();
		_createdOn = ticket.getCreatedOn();
		_closedOn = ticket.getClosedOn();
		_weight = ticket.getWeight();
		_type = ticket.getType();
		_status = ticket.getStatus();
		_commentsIDs = new HashSet<Long>();
		Set<Comment> comments =  ticket.getComments();
		if(comments!=null) {
			for(Comment comment : comments) {
				Long commentID = comment.getCommentID();
				_commentsIDs.add(commentID);
			}
		}
		_issuerID = ticket.getIssuer().getUserID();
		if(ticket.getAssignee()!=null) {
			_assigneeID = ticket.getAssignee().getUserID();
		}
		_projectID = ticket.getProject().getProjectID();
	}
    

	public TicketResponse() {
	// TODO Auto-generated constructor stub
    }


	public long getTicketID() {
		return _ticketID;
	}

	public void setTicketID(long ticketID) {
		_ticketID = ticketID;
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

	public Date getCreatedOn() {
		return _createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		_createdOn = createdOn;
	}

	public Set<Long> getCommentsIDs() {
		return _commentsIDs;
	}

	public void setCommentsIDs(Set<Long> commentsIDs) {
		_commentsIDs = commentsIDs;
	}

	public Date getClosedOn() {
		return _closedOn;
	}

	public void setClosedOn(Date closedOn) {
		_closedOn = closedOn;
	}

	public int getWeight() {
		return _weight;
	}

	public void setWeight(int weight) {
		_weight = weight;
	}

	public Type getType() {
		return _type;
	}

	public void setType(Type type) {
		_type = type;
	}

	public TicketStatus getStatus() {
		return _status;
	}

	public void setStatus(TicketStatus status) {
		_status = status;
	}

	public long getIssuerID() {
		return _issuerID;
	}

	public void setIssuerID(long issuerID) {
		_issuerID = issuerID;
	}

	public long getAssigneeID() {
		return _assigneeID;
	}

	public void setAssigneeID(long assigneeID) {
		_assigneeID = assigneeID;
	}

	public long getProjectID() {
		return _projectID;
	}

	public void setProjectID(long projectID) {
		_projectID = projectID;
	}
	

}
