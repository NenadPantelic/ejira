package net.radionica.ejira.service;

import java.io.IOException;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrServerException;

import net.radionica.ejira.model.Ticket;

public interface TicketService {

    long saveTicket(Ticket ticket) throws SolrServerException, IOException;
    
    Ticket getTicketByID(Long ticketID);
    
    Collection <Ticket> getTickets(Long userID, Long projectID);
    
    Collection <Ticket> getTicketsByUser(Long userID);
    
    Collection<Ticket> getTicketsByProject(Long projectID);

    boolean saveTicketB(Ticket ticket);
        
}
