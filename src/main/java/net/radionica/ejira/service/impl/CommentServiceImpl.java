package net.radionica.ejira.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.radionica.ejira.dao.CommentRepository;
import net.radionica.ejira.model.Comment;
import net.radionica.ejira.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository _commentRepository;
    

    public CommentRepository getCommentRepository() {
	return _commentRepository;
    }


    @Override
    public boolean createComment(Comment comment) {
	// TODO Auto-generated method stub
	Comment savedComment = getCommentRepository().save(comment);
	if(savedComment != null) 
	    return true;
	else 
	    return false;
    }

    public Collection<Comment> getCommentsByTicket(Long ticketID) {
	return getCommentRepository().getCommentsByTicket(ticketID);
    }
}
