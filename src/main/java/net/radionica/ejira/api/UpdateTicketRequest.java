package net.radionica.ejira.api;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import net.radionica.ejira.model.TicketStatus;
import net.radionica.ejira.model.Type;

public class UpdateTicketRequest {
	
	@NotNull (message = "There is no ticket ID!")
    private Long _ticketID;
    
    @NotEmpty(message = "Name cannot be empty")
    private String _name;
    
    private String _description;
 
    private Date _closedOn;
    
    @Min(value = 1, message = "Minimum weight: 1")
    @Max(value = 10, message = "Maximum weight: 10")
    private int _weight;
    
    private Type _type;

    private TicketStatus _status;

    private Long _assigneeID;
    
    @NotNull
    private Long _projectID;

    
    
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

	public Long getAssigneeID() {
		return _assigneeID;
	}
	public void setAssigneeID(Long assigneeID) {
		_assigneeID = assigneeID;
	}

	public Long getProjectID() {
		return _projectID;
	}
	public void setProjectID(Long projectID) {
		_projectID = projectID;
	}

}
