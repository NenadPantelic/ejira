package net.radionica.ejira.service.impl;

import java.io.IOException;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.radionica.ejira.dao.ProjectRepository;
import net.radionica.ejira.dao.TicketRepository;
import net.radionica.ejira.dao.UserRepository;
import net.radionica.ejira.exception.NonExistentResourceException;
import net.radionica.ejira.model.Ticket;
import net.radionica.ejira.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private ProjectRepository _projectRepository;
//    private final String _urlString = "http://localhost:8983/solr/techproducts";
//    private final SolrClient _solr = new HttpSolrClient.Builder(_urlString).build();;

    @Autowired
    private SolrService _solrService;

    @Autowired
    private TicketRepository _ticketRepository;

    // private String _urlString;
    @Autowired
    private UserRepository _userRepository;

//    public SolrClient getSolr() {
//	return _solr;
//    }

    public SolrService getSolrService() {
	return _solrService;
    }

    public Ticket getTicketByID(Long ticketID) {
	if (_ticketRepository.findBy_ticketID(ticketID) == null) {
	    throw new NonExistentResourceException("Ticket is non-existent!");
	}
	return _ticketRepository.findBy_ticketID(ticketID);
    }

    public TicketRepository getTicketRepository() {
	return _ticketRepository;
    }

    public Collection<Ticket> getTickets(Long userID, Long projectID) {
	if (_userRepository.findBy_userID(userID) == null) {
	    throw new NonExistentResourceException("User is non-existent!");
	}
	if (_projectRepository.findBy_projectID(projectID) == null) {
	    throw new NonExistentResourceException("Project is non-existent!");
	}
	return _ticketRepository.getTicketsByUserAndProject(userID, projectID);
    }

    public Collection<Ticket> getTicketsByProject(Long projectID) {
	if (_projectRepository.findBy_projectID(projectID) == null) {
	    throw new NonExistentResourceException("Project is non-existent!");
	}
	return getTicketRepository().getTicketsByProject(projectID);
    }

    public Collection<Ticket> getTicketsByUser(Long userID) {
	if (_userRepository.findBy_userID(userID) == null) {
	    throw new NonExistentResourceException("User is non-existent!");
	}
	return _ticketRepository.getTicketsByUser(userID);
    }

    public boolean saveTicketB(Ticket ticket) {
	Ticket savedTicket = _ticketRepository.save(ticket);
	if (savedTicket != null) {
	    return true;
	} else {
	    return false;
	}
    }

//    public boolean saveTicket(Ticket ticket) throws SolrServerException, IOException {
//	Ticket savedTicket = _ticketRepository.save(ticket);
//	if (savedTicket != null) {
//	    long id = ticket.getTicketID();
//	    String description = ticket.getDescription();
//	    String name = ticket.getName();
//	    System.out.println("Save id: " + id);
////	    SolrInputDocument doc = new SolrInputDocument();
////	    doc.addField("id", id);
////	    doc.addField("description", description);
////	    doc.addField("name", name);
////	    
////	    getSolr().add(doc);
////	    getSolr().commit();
//	    
//	    getSolrService().createIndexedData(id, name, description);;
//	    return true;
//	} else {
//	    return false;
//	}
//    }

    public long saveTicket(Ticket ticket) throws SolrServerException, IOException {
	
	Ticket savedTicket = _ticketRepository.save(ticket);
	
	long id = savedTicket.getTicketID();
	String description = savedTicket.getDescription();
	String name = savedTicket.getName();
	getSolrService().createIndexedData(id, name, description);
	
	
	return ticket.getTicketID();
    }

//    public String getUrlString() {
//	return _urlString;
//    }

    public void setSolrService(SolrService solrService) {
	_solrService = solrService;
    }

//    public void setSolr() {
//	_solr =  new HttpSolrClient.Builder(_urlString).build();;
//    }
//
//    public void setUrlString(String urlString) {
//	_urlString = ;
//    }

}
