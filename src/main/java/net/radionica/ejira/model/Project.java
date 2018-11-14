package net.radionica.ejira.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PROJ_ID")
    private long _projectID;

    @Column(name = "NAME")
    private String _name;

    @Column(name = "DESCRIPTION")
    private String _description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "USER_ID")
    private User _owner;

    @OneToMany(mappedBy = "_project", cascade = CascadeType.ALL)
    private Set<Ticket> _tickets;

	public long getProjectID() {
		return _projectID;
	}
	public void setProjectID(long projectID) {
		_projectID = projectID;
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

	public User getOwner() {
		return _owner;
	}
	public void setOwner(User owner) {
		_owner = owner;
	}

	public Set<Ticket> getTickets() {
		return _tickets;
	}
	public void setTickets(Set<Ticket> tickets) {
		_tickets = tickets;
	}

}
