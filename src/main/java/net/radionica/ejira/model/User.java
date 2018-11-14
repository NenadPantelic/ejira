package net.radionica.ejira.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private long _userID;

	@Column(name = "FIRSTNAME", nullable = false)
	private String _firstname;

	@Column(name = "LASTNAME", nullable = false)
	private String _lastname;

	@Column(name = "EMAIL", unique = true, nullable = false)
	private String _email;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ROLE")
	private Role _role;

	@Column(name = "USERNAME", unique = true, nullable = false)
	private String _username;

	@Column(name = "PASSWORD", nullable = false)
	private String _password;

	@OneToMany(mappedBy = "_assignee", cascade = CascadeType.ALL)
	Set<Ticket> _tickets;

	@OneToMany(mappedBy = "_issuer", cascade = CascadeType.ALL)
	Set<Ticket> _createdTickets;

	@Transient
	private String _repeatedPassword;

	public long getUserID() {
		return _userID;
	}

	public void setUserID(long userID) {
		_userID = userID;
	}

	public String getFirstname() {
		return _firstname;
	}

	public void setFirstname(String firstname) {
		_firstname = firstname;
	}

	public String getLastname() {
		return _lastname;
	}

	public void setLastname(String lastname) {
		_lastname = lastname;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public Role getRole() {
		return _role;
	}

	public void setRole(Role role) {
		_role = role;
	}

	public String getUsername() {
		return _username;
	}

	public void setUsername(String username) {
		_username = username;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public Set<Ticket> getTickets() {
		return _tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		_tickets = tickets;
	}

	public Set<Ticket> getCreatedTickets() {
		return _createdTickets;
	}

	public void setCreatedTickets(Set<Ticket> createdTickets) {
		_createdTickets = createdTickets;
	}

	public String getRepeatedPassword() {
		return _repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		_repeatedPassword = repeatedPassword;
	}


}
