package net.radionica.ejira.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

@Table(name = "Comment")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_ID")
    private Long _commentID;
    private Date _date;
    private String _text;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TICKET_ID", referencedColumnName = "TICKET_ID")
    private Ticket _ticket;

    public Long getCommentID() {
		return _commentID;
	}

	public void setCommentID(Long id) {
		_commentID = id;
	}

	public Date getDate() {
	return _date;
    }

    public String getText() {
	return _text;
    }

    public Ticket getTicket() {
	return _ticket;
    }

    

    public void setDate(Date date) {
	_date = date;
    }

    public void setText(String text) {
	_text = text;
    }

    public void setTicket(Ticket ticket) {
	_ticket = ticket;
    }


}
