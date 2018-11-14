package net.radionica.ejira.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.radionica.ejira.dao.ProjectRepository;
import net.radionica.ejira.dao.TicketRepository;
import net.radionica.ejira.exception.NonExistentResourceException;
import net.radionica.ejira.model.Project;
import net.radionica.ejira.model.Ticket;
import net.radionica.ejira.model.User;
import net.radionica.ejira.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository _projectRepository;
    @Autowired
    private TicketRepository _ticketRepository;

    public ProjectRepository getProjectRepository() {
	return _projectRepository;
    }
    public void setProjectRepository(ProjectRepository projectRepository) {
	_projectRepository = projectRepository;
    }

    public Iterable<Project> viewAllProjects() {
	return getProjectRepository().findAll();
    }

    public void modifyProject(Project project) {

		Project proj = getProjectRepository().findBy_projectID(project.getProjectID());
	
		proj.setDescription(project.getDescription());
		proj.setName(project.getName());
		proj.setOwner(project.getOwner());
	
		getProjectRepository().save(proj);
    }

    public long createProject(Project project) {
		Project p = new Project();
		p.setName(project.getName());
		p.setDescription(project.getDescription());
		p.setOwner(project.getOwner());
		getProjectRepository().save(p);
		return p.getProjectID();
    }

    public Set<User> getUsersByProjectId(long projectID) {
    	Project project = getProjectRepository().findBy_projectID(projectID);
    	if(project==null) {
    		throw new NonExistentResourceException("Project is non-existent!");
    	}
    	HashSet<User> users = new HashSet<User>();
    	Collection<Ticket> tickets = _ticketRepository.getTicketsByProject(projectID);
    	for(Ticket ticket : tickets) {
    		User issuer = ticket.getIssuer();
    		if(!users.contains(issuer)) {
    			users.add(issuer);
    		}
    		User assignee = ticket.getAssignee();
    		if(!users.contains(assignee)) {
    			users.add(assignee);
    		}
    	}
    	return users;
    }
    
    public Project getProjectByProjectId(Long id) {
    	Project project = getProjectRepository().findBy_projectID(id);
    	if(project==null) {
    		throw new NonExistentResourceException("Project is non-existent!");
    	}
    	return project;
        }
    
    public Collection<Project> getProjectByUser(Long userID) {
    	return getProjectRepository().getProjectsByUser(userID);
    }
    
    public Collection<Project> getProjectsByOwner(Long ownerID) {
    	return getProjectRepository().getProjectsByOwner(ownerID);
    }

}
