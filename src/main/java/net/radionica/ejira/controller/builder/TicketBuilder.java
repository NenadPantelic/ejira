package net.radionica.ejira.controller.builder;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import org.apache.solr.client.solrj.SolrServerException;

import net.radionica.ejira.model.Comment;
import net.radionica.ejira.model.Project;
import net.radionica.ejira.model.Ticket;
import net.radionica.ejira.model.TicketStatus;
import net.radionica.ejira.model.Type;
import net.radionica.ejira.model.User;
import net.radionica.ejira.service.TicketService;

public class TicketBuilder {
	
	Ticket _ticket;
	public static TicketBuilder builder() {
		return new TicketBuilder();
	}
	private TicketBuilder() {
		_ticket = new Ticket();
	}
	
	public TicketBuilder id(Long id) {
		_ticket.setTicketID(id);
		return this;
	}
	public TicketBuilder name(String name) {
		_ticket.setName(name);
		return this;
	}
	public TicketBuilder description(String description) {
		_ticket.setDescription(description);
		return this;
	}
	public TicketBuilder issuer(User issuer) {
		_ticket.setIssuer(issuer);
		return this;
	}
	public TicketBuilder assignee(User assignee) {
		_ticket.setAssignee(assignee);
		return this;
	}
	public TicketBuilder type(Type type) {
		_ticket.setType(type);
		return this;
	}
	public TicketBuilder status(TicketStatus status) {
		_ticket.setStatus(status);
		return this;
	}
	public TicketBuilder weight(int weight) {
		_ticket.setWeight(weight);
		return this;
	}
	public TicketBuilder project(Project project) {
		_ticket.setProject(project);
		return this;
	}
	public TicketBuilder createdOn(Date createdOn) {
		_ticket.setCreatedOn(createdOn);
		return this;
	}
	public TicketBuilder closedOn(Date closedOn) {
		_ticket.setClosedOn(closedOn);
		return this;
	}
	public TicketBuilder comments(Set<Comment> comments) {
		_ticket.setComments(comments);
		return this;
	}
	
	
	public long save(TicketService service) throws SolrServerException, IOException{
		return service.saveTicket(_ticket);
	}
	public boolean saveB(TicketService ticketService) {
	    return ticketService.saveTicketB(_ticket);
	}
}
