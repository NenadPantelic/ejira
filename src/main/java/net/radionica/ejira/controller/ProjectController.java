package net.radionica.ejira.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.IterateBlock;

import net.radionica.ejira.api.CreateProjectRequest;
import net.radionica.ejira.api.ProjectIDRequest;
import net.radionica.ejira.api.RegisterUserRequest;
import net.radionica.ejira.api.response.CreateProjectResponse;
import net.radionica.ejira.api.response.ProjectResponse;
import net.radionica.ejira.api.response.TicketResponse;
import net.radionica.ejira.api.response.UserResponse;
import net.radionica.ejira.controller.aop.UserRole;
import net.radionica.ejira.controller.builder.CreateProjectBuilder;
import net.radionica.ejira.controller.interceptor.LogInterceptor;
import net.radionica.ejira.dao.UserRepository;
import net.radionica.ejira.model.Project;
import net.radionica.ejira.model.Role;
import net.radionica.ejira.model.Ticket;
import net.radionica.ejira.model.User;
import net.radionica.ejira.service.ProjectService;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService _projectService;
    @Autowired
    private UserRepository _userRepository;

    public ProjectService getProjectService() {
	return _projectService;
    }
    public void setProjectSerice(ProjectService projectService) {
	_projectService = projectService;
    }
    
    public UserRepository getUserRepository() {
    	return _userRepository;
    }
    public void setUserRepository(UserRepository userRepository) {
    	_userRepository = userRepository;
    }
        
    @UserRole(name = Role.PROJECT_MANAGER)
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public Collection<ProjectResponse> getAllProjects() {
    	Iterable<Project> projects = getProjectService().viewAllProjects();
    	Collection<ProjectResponse> projectsResponse = new HashSet<ProjectResponse>();
    	if(projects!=null) {
			for(Project project : projects) {
				ProjectResponse response = new ProjectResponse(project);
				projectsResponse.add(response);
			}
		}
		return projectsResponse;
    }

    @RequestMapping(value = "/projects/edit/{id}", method = RequestMethod.POST)
    public void editProject(@RequestBody Project project) {                       //Napraviti EditProjectRequest
    	_projectService.modifyProject(project);
    }

    @UserRole(name = Role.PROJECT_MANAGER)
    @RequestMapping(value = "/project/create", method = RequestMethod.POST)
    public CreateProjectResponse createProject(@RequestBody CreateProjectRequest project) throws NoSuchAlgorithmException {
	
	CreateProjectResponse respon = new CreateProjectResponse();
	
	respon.setProjectId(CreateProjectBuilder.builder().name(project.getName()).description(project.getDescription())
		.owner(getUserRepository().findBy_userID(LogInterceptor.context.get().getUserId()))
		.execute(_projectService));
	
	return respon;
    }

    @RequestMapping(value = "/project/users", method = RequestMethod.POST)
    public Collection<UserResponse> getUsersByProjectId(@Valid @RequestBody ProjectIDRequest projectIDRequest) {
    	Long projectID = projectIDRequest.getProjectID();
    	Collection<UserResponse> usersResponse = new HashSet<UserResponse>();
		Set<User> users = getProjectService().getUsersByProjectId(projectID);
		if(users!=null) {
			for(User user : users) {
				UserResponse response = new UserResponse(user);
				usersResponse.add(response);
			}
		}
		return usersResponse;
    }

    @RequestMapping(value = "/user_projects", method = RequestMethod.GET)
    public Collection<Project> getProjectByUsers() {
	Long userID = LogInterceptor.context.get().getUserId();
	return getProjectService().getProjectByUser(userID);
    }
    
    @RequestMapping(value = "/owner_projects", method = RequestMethod.GET)
    public Collection<Project> getProjectsByOwner() {
	Long ownerID = LogInterceptor.context.get().getUserId();
	return getProjectService().getProjectsByOwner(ownerID);
    }
}
