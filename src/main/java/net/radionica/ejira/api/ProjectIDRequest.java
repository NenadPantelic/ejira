package net.radionica.ejira.api;

import javax.validation.constraints.NotNull;

public class ProjectIDRequest {
	
	@NotNull (message = "There is no project ID!")
	private Long _projectID;

	public Long getProjectID() {
		return _projectID;
	}

	public void setProjectID(Long projectID) {
		_projectID = projectID;
	}

}
