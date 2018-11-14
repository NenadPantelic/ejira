package net.radionica.ejira.controller.builder;

import java.security.NoSuchAlgorithmException;

import net.radionica.ejira.model.Project;
import net.radionica.ejira.model.User;
import net.radionica.ejira.service.ProjectService;

public class CreateProjectBuilder {
    private Project _project;

    public static CreateProjectBuilder builder() {
	return new CreateProjectBuilder();
    }

    private CreateProjectBuilder() {
	_project = new Project();
    }

    public Project getProject() {
	return _project;
    }

    public CreateProjectBuilder name(String name) {
	getProject().setName(name);
	return this;

    }

    public CreateProjectBuilder description(String description) {
	getProject().setDescription(description);
	return this;
    }

    public CreateProjectBuilder owner(User owner) {
	getProject().setOwner(owner);
	return this;
    }

    public long execute(ProjectService service) throws NoSuchAlgorithmException {
	return service.createProject(_project);
    }

}
