package net.radionica.ejira.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.radionica.ejira.api.response.TicketResponse;
import net.radionica.ejira.controller.interceptor.LogInterceptor;
import net.radionica.ejira.dao.TicketRepository;
import net.radionica.ejira.dao.UserRepository;
import net.radionica.ejira.model.Project;
import net.radionica.ejira.model.Ticket;
import net.radionica.ejira.model.User;

@Service
public class SolrService {
    private final String _urlString = "http://localhost:8983/solr/tickets";
    private final SolrClient _solr = new HttpSolrClient.Builder(_urlString).build();
    private final SolrQuery query = new SolrQuery();
    @Autowired
    private TicketRepository _ticketRepo;
    @Autowired
    private UserRepository _userRepo;

    @Autowired
    private ProjectServiceImpl _projectService;
    public ProjectServiceImpl getProjectService() {
        return _projectService;
    }

    public UserRepository getUserRepo() {
	return _userRepo;
    }

    public void setUserRepo(UserRepository userRepo) {
	_userRepo = userRepo;
    }

    public TicketRepository getTicketRepo() {
	return _ticketRepo;
    }

    public void setTicketRepo(TicketRepository ticketRepo) {
	_ticketRepo = ticketRepo;
    }

    public SolrQuery getSolrQuery() {
	return query;
    }

    public SolrClient getSolr() {
	return _solr;
    }

    public void addAndCommit(SolrInputDocument doc) throws SolrServerException, IOException {
	getSolr().add(doc);
	getSolr().commit();
    }

    public void createIndexedData(long id, String name, String descr) throws SolrServerException, IOException {
	SolrInputDocument doc = new SolrInputDocument();
	doc.addField("id", id);
	doc.addField("description", descr);
	doc.addField("name", name);
	System.out.println("ID u indeksiranju: " + id);
	System.out.println(doc);
	addAndCommit(doc);

    }

    public void setSolrQuery(String query) {
	String queryString = "description:*" + query + "* OR name:*" + query + "*";
	getSolrQuery().setQuery(queryString);
    }

    public String parseList(String prop) {
	return null;
    }

    public List<Long> searchTickets(String text) throws SolrServerException, IOException {
	setSolrQuery(text);
	// System.out.println("Text:" + text);
	QueryResponse response = getSolr().query(getSolrQuery());
	// System.out.println(response.getResults());
	// List<Ticket> items = response.getBeans(Ticket.class);
	SolrDocumentList list = response.getResults();
	List<Long> ticketIDS = new ArrayList<Long>();
	getSolr().commit();
	// List<TicketResponse> tickets = new ArrayList<TicketResponse>();
	for (SolrDocument doc : list) {
	    // TicketResponse tickResp = new TicketResponse();
	    // System.out.println((((ArrayList<String>)doc.getFieldValue("description")).get(0));
	    System.out.println(doc);
	    System.out.println(doc.getFieldValue("id").toString());
	    ticketIDS.add(Long.parseLong(doc.getFieldValue("id").toString()));
	    System.out.println("ID u petlji: "  + Long.parseLong(doc.getFieldValue("id").toString()));
	    // System.out.println(doc.getFieldValue("description").getClass().getName());
	    // tickResp.setDescription(((ArrayList)
	    // doc.getFieldValue("description")).get(0).toString());
	    // tickResp.setName(((ArrayList) doc.getFieldValue("name")).get(0).toString());
	    // System.out.println(doc.getFieldValue("id").getClass().getName());
	    // tickResp.setTicketID(Long.parseLong(doc.getFieldValue("id").toString()));
	    // System.out.println(tickResp.getName());
	    // tickets.add(tickResp);

	}
	return ticketIDS;

    }

    public List<TicketResponse> getMatchedTickets(String text) throws SolrServerException, IOException {
	List<Long> ticketIDS = searchTickets(text);
	Long userID = LogInterceptor.context.get().getUserId();
	List<TicketResponse> tickets = new ArrayList<TicketResponse>();
	System.out.println("User: " + userID);
	for (long id : ticketIDS) {
	    System.out.println("Ticket: " + id);
	    Project proj = getTicketRepo().findById(id).get().getProject();
	    System.out.println("PROJ: " + proj.getProjectID());
	    Set<User> users = getProjectService().getUsersByProjectId(proj.getProjectID());
	    for (User user : users) {
		System.out.println("User2: " + user.getUserID());
		if (user.getUserID() == userID) {
		    Ticket t = getTicketRepo().findById(id).get();
		    tickets.add(new TicketResponse(t));
		}
	    }

	}
	return tickets;

    }

}
