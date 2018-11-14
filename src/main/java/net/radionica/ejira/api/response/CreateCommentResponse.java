package net.radionica.ejira.api.response;

import java.util.Date;

import net.radionica.ejira.model.Comment;

public class CreateCommentResponse {

    private Long _commentID;
    private Long _ticketID;
    private String _text;
    private Date _date;

    public CreateCommentResponse(Comment comment) {
	_commentID = comment.getCommentID();
	_ticketID = comment.getTicket().getTicketID();
	_text = comment.getText();
	_date = comment.getDate();
    }

    public CreateCommentResponse() {
	// TODO Auto-generated constructor stub
    }

    public Long getCommentID() {
	return _commentID;
    }

    public void setCommentID(Long commentID) {
	_commentID = commentID;
    }

    public Long getTicketID() {
	return _ticketID;
    }

    public void setTicketID(Long ticketID) {
	_ticketID = ticketID;
    }

    public String getText() {
	return _text;
    }

    public void setText(String text) {
	_text = text;
    }

    public Date getDate() {
	return _date;
    }

    public void setDate(Date date) {
	_date = date;
    }

}
