package net.radionica.ejira.dao;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.radionica.ejira.model.Ticket;
import net.radionica.ejira.model.Type;
import net.radionica.ejira.model.User;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long>{

    Ticket findBy_ticketID(Long id);
    Ticket findBy_createdOn(Date createdOn);
    Set<Ticket> findBy_closedOn(Date closedOn);
    Set<Ticket> findBy_issuer(User issuer);
    Set<Ticket> findBy_assignee(User asignee);
    Set<Ticket> findBy_weight(int weight);
    Set<Ticket> findBy_type(Type type);
    Set<Ticket> findBy_status(boolean status);
    
    @Query("SELECT t FROM Ticket t WHERE t._project._projectID = :projectID " + 
    "AND (t._assignee._userID = :userID OR t._issuer._userID = :userID)")
    Collection<Ticket> getTicketsByUserAndProject(@Param("userID") Long userID, @Param("projectID") Long projectID);
    
    @Query("SELECT t FROM Ticket t WHERE t._assignee._userID = :userID OR t._issuer._userID = :userID")
    Collection<Ticket> getTicketsByUser(@Param("userID") Long userID);
    
    @Query(value = "SELECT t FROM Ticket t WHERE t._project._projectID = :projectID")
    Collection<Ticket> getTicketsByProject(@Param("projectID") Long projectID);
}