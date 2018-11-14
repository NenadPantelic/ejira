package net.radionica.ejira.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.radionica.ejira.model.Project;
import net.radionica.ejira.model.User;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findBy_projectID(long id);

    Project findBy_name(String name);

    Collection<Project> findBy_owner(User owner);
    
    @Query(value = "SELECT DISTINCT t._project._projectID, t._project._name FROM Ticket t WHERE t._assignee._userID = :userID")
    Collection<Project> getProjectsByUser(@Param("userID") Long userID);
    
    @Query(value = "SELECT p._projectID, p._name FROM Project p WHERE p._owner._userID = :userID")
    Collection<Project> getProjectsByOwner(@Param("userID") Long userID);


}
