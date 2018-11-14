package net.radionica.ejira.api;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import net.radionica.ejira.model.TicketStatus;
import net.radionica.ejira.model.Type;

public class CreateTicketRequest {
	
	@NotEmpty(message = "Name cannot be empty")
	private String _name;
	
    private String _description;
    
    @Min(value = 1, message = "Minimum weight: 1")
    @Max(value = 10, message = "Maximum weight: 10")
    private int _weight;
    
    @NotNull
    private Type _type = Type.TASK;
    
    @NotNull
    private TicketStatus _status = TicketStatus.OPEN;
    
    private Long _assigneeID;
    
    @NotNull
    private Long _projectID;
 
      
    
	public String getName() {
		return _name;
	}
	public void setName(String _name) {
		this._name = _name;
	}
	public String getDescription() {
		return _description;
	}
	public void setDescription(String _description) {
		this._description = _description;
	}
	public int getWeight() {
		return _weight;
	}
	public void setWeight(int _weight) {
		this._weight = _weight;
	}
	public Type getType() {
		return _type;
	}
	public void setType(Type _type) {
		this._type = _type;
	}
	public TicketStatus getStatus() {
		return _status;
	}
	public void setStatus(TicketStatus _status) {
		this._status = _status;
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
