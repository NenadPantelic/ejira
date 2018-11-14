package net.radionica.ejira.service;

import java.util.Collection;

import net.radionica.ejira.model.Comment;

public interface CommentService {

    boolean createComment(Comment comment);
    
    Collection<Comment> getCommentsByTicket(Long ticketID);
}
