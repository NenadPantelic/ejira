package net.radionica.ejira.controller.builder;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import net.radionica.ejira.model.Comment;
import net.radionica.ejira.model.Ticket;
import net.radionica.ejira.service.CommentService;

public class CreateCommentBuilder {

    private Comment _comment;

    public static CreateCommentBuilder builder() {
	return new CreateCommentBuilder();
    }

    private CreateCommentBuilder() {
	_comment = new Comment();
    }

    public Comment getComment() {
	return _comment;
    }

    public CreateCommentBuilder commentID(Long commentID) {
	getComment().setCommentID(commentID);
	return this;
    }
    
    public CreateCommentBuilder text(String text) {
	getComment().setText(text);
	return this;
    }

    public CreateCommentBuilder ticket(Ticket ticket) {
	getComment().setTicket(ticket);
	return this;
    }
    
    public CreateCommentBuilder date(Date date) {
	getComment().setDate(date);
	return this;
    }

    public boolean execute(CommentService service) throws NoSuchAlgorithmException {
	return service.createComment(getComment());
    }
}
