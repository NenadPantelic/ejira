package net.radionica.ejira.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.radionica.ejira.api.CreateCommentRequest;
import net.radionica.ejira.api.response.CreateCommentResponse;
import net.radionica.ejira.controller.builder.CreateCommentBuilder;
import net.radionica.ejira.model.Ticket;
import net.radionica.ejira.service.CommentService;
import net.radionica.ejira.service.TicketService;

@RestController
public class CommentController {

    @Autowired
    private CommentService _commentService;

    @Autowired
    private TicketService _ticketService;

    public TicketService getTicketService() {
	return _ticketService;
    }

    public CommentService getCommentService() {
	return _commentService;
    }

    @RequestMapping(value = "/comment/create", method = RequestMethod.POST)
    public boolean createComment(@Valid @RequestBody CreateCommentRequest commentRequest) throws NoSuchAlgorithmException {
	Ticket ticket = getTicketService().getTicketByID(commentRequest.getTicketID());
	
	return CreateCommentBuilder.builder().text(commentRequest.getText()).date(new Date()).ticket(ticket)
		.execute(getCommentService());
    }

}
