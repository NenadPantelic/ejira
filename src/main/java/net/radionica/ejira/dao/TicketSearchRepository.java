//package net.radionica.ejira.dao;
//
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
////import org.springframework.data.solr.repository.Query;
////import org.springframework.data.solr.repository.SolrCrudRepository;
//
//import net.radionica.ejira.model.Ticket;
//
//public interface TicketSearchRepository extends SolrCrudRepository<Ticket, String> {
// 
//    public List<Ticket> findByName(String name);
//    public List<Ticket> findByDescription(String desc);
// 
//    @Query("id:*?0* OR name:*?0* OR description:*?0*")
//    public Page<Ticket> findByCustomQuery(String searchTerm, Pageable pageable);
// 
//    @Query(name = "Ticket.findByNamedQuery")
//    public Page<Ticket> findByNamedQuery(String searchTerm, Pageable pageable);
// 
//}
//
//
