package net.radionica.ejira.api;

public class CreateProjectRequest {

    private String _name;
    private String _description;

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

}
