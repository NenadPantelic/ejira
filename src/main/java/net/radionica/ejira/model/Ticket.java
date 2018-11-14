package net.radionica.ejira.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//import org.springframework.data.solr.core.mapping.Indexed;
//import org.springframework.data.solr.core.mapping.SolrDocument;

//@SolrDocument(solrCoreName = "ticket")
@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TICKET_ID")
    private long _ticketID;
    //@Indexed(name = "name", type = "string")
    @Column(name = "NAME")
    private String _name;

    //@Indexed(name = "description", type = "string")
    @Column(name = "DESCRIPTION")
    private String _description;

    @Column(name = "CREATED_ON")
    private Date _createdOn;

    @OneToMany(mappedBy = "_ticket", cascade = CascadeType.ALL) // Ubacivanje komentara
    private Set<Comment> _comments;

    @Column(name = "CLOSED_ON")
    private Date _closedOn;

    @Column(name = "WEIGHT")
    private int _weight;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TYPE")
    private Type _type;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "STATUS")
    private TicketStatus _status;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ISSUER_ID", referencedColumnName = "USER_ID")
    private User _issuer;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ASSIGNEE_ID", referencedColumnName = "USER_ID")
    private User _assignee;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "PROJ_ID")
    private Project _project;

    public long getTicketID() {
	return _ticketID;
    }

    public void setTicketID(long ticketID) {
	_ticketID = ticketID;
    }

    public String getName() {
	return _name;
    }

    public void setName(String name) {
	_name = name;
    }

    public String getDescription() {
	return _description;
    }

    public void setDescription(String description) {
	_description = description;
    }

    public Date getCreatedOn() {
	return _createdOn;
    }

    public void setCreatedOn(Date createdOn) {
	_createdOn = createdOn;
    }

    public Set<Comment> getComments() {
	return _comments;
    }

    public void setComments(Set<Comment> comments) {
	_comments = comments;
    }

    public Date getClosedOn() {
	return _closedOn;
    }

    public void setClosedOn(Date closedOn) {
	_closedOn = closedOn;
    }

    public int getWeight() {
	return _weight;
    }

    public void setWeight(int weight) {
	_weight = weight;
    }

    public Type getType() {
	return _type;
    }

    public void setType(Type type) {
	_type = type;
    }

    public TicketStatus getStatus() {
	return _status;
    }

    public void setStatus(TicketStatus status) {
	_status = status;
    }

    public User getIssuer() {
	return _issuer;
    }

    public void setIssuer(User issuer) {
	_issuer = issuer;
    }

    public User getAssignee() {
	return _assignee;
    }

    public void setAssignee(User assignee) {
	_assignee = assignee;
    }

    public Project getProject() {
	return _project;
    }

    public void setProject(Project project) {
	_project = project;
    }

}
