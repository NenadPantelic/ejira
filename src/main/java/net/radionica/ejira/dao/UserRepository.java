package net.radionica.ejira.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.radionica.ejira.model.*;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
	User findBy_username(String username);
	User findBy_email(String email);
	User findBy_tickets(Ticket ticket);
	User findBy_createdTickets(Ticket ticket);
	Set<User> findBy_role(Role role);
	User findBy_firstname(String firstname);
	User findBy_lastname(String lastname);
	User findBy_password(String username);
	User findBy_userID(Long userID);
	

}