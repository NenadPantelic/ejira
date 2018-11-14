package net.radionica.ejira.service;

import java.util.Collection;
import java.util.Set;

import net.radionica.ejira.model.Project;
import net.radionica.ejira.model.User;

public interface ProjectService {

    Iterable<Project> viewAllProjects();

    void modifyProject(Project project);

    long createProject(Project project);

    Set<User> getUsersByProjectId(long id);
    
    Project getProjectByProjectId(Long id);
    
    Collection<Project> getProjectByUser(Long userID);
    
    Collection<Project> getProjectsByOwner(Long ownerID);

}
