package net.radionica.ejira.dao;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.radionica.ejira.model.Comment;
import net.radionica.ejira.model.Ticket;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{

    Comment findBy_commentID(Long commentID);
    Collection<Comment> findBy_ticket(Ticket ticket);
    Comment findBy_date(Date date);
    Comment findBy_text(String text);
    
    @Query(value = "SELECT c._commentID, c._text FROM Comment c WHERE c._ticket._ticketID = :ticketID")
    Collection<Comment> getCommentsByTicket(@Param("ticketID") Long ticketID);
}
