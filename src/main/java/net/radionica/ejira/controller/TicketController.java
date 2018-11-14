package net.radionica.ejira.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.radionica.ejira.api.ApiError;
import net.radionica.ejira.api.CreateTicketRequest;
import net.radionica.ejira.api.ProjectIDRequest;
import net.radionica.ejira.api.TicketIDRequest;
import net.radionica.ejira.api.TicketSearchRequest;
import net.radionica.ejira.api.UpdateTicketRequest;
import net.radionica.ejira.api.response.TicketResponse;
import net.radionica.ejira.api.response.TicketSearchResponse;
import net.radionica.ejira.controller.builder.TicketBuilder;
import net.radionica.ejira.controller.interceptor.LogInterceptor;
import net.radionica.ejira.exception.InvalidCredentialsException;
import net.radionica.ejira.exception.NonExistentResourceException;
import net.radionica.ejira.exception.UnauthorizedException;
import net.radionica.ejira.model.Comment;
import net.radionica.ejira.model.Project;
import net.radionica.ejira.model.Role;
import net.radionica.ejira.model.Ticket;
import net.radionica.ejira.model.User;
import net.radionica.ejira.service.CommentService;
import net.radionica.ejira.service.ProjectService;
import net.radionica.ejira.service.TicketService;
import net.radionica.ejira.service.UserService;
import net.radionica.ejira.service.impl.SolrService;
import net.radionica.ejira.service.impl.UserServiceImpl;

@RestController
public class TicketController {

    @Autowired
    private TicketService _ticketService;

    @Autowired
    private SolrService _solrService;

    public SolrService getSolrService() {
	return _solrService;
    }

    @Autowired
    private CommentService _commentService;
    
    public CommentService getCommentService() {
        return _commentService;
    }

    @Autowired
    private UserServiceImpl _userService;

    public UserServiceImpl getUserService() {
        return _userService;
    }

    public void setUserService(UserServiceImpl userService) {
        _userService = userService;
    }

    @Autowired
    private ProjectService _projectService;

    public TicketService getTicketService() {
	return _ticketService;
    }

    @RequestMapping(value = "/ticket/create", method = RequestMethod.POST)
    public long createTicket(@Valid @RequestBody CreateTicketRequest ticketRequest) throws NonExistentResourceException, InvalidCredentialsException, SolrServerException, IOException {
    	Long issuerUserID = LogInterceptor.context.get().getUserId();
    	User assignee = _userService.getUserByUserID(ticketRequest.getAssigneeID());
    	if(ticketRequest.getAssigneeID()!=null) {
    		if(assignee==null) {
        		throw new NonExistentResourceException("Assignee is not existent!");
        	}
    	}
    	Project project = _projectService.getProjectByProjectId(ticketRequest.getProjectID());
    	if(project==null) {
    		throw new NonExistentResourceException("Project is not existent!");
    	}
    	TicketResponse resp = new TicketResponse();
    	
    	resp.setTicketID( TicketBuilder.builder()
    		.name(ticketRequest.getName())
    		.description(ticketRequest.getDescription())
    		.issuer(_userService.getUserByUserID(issuerUserID))
    		.assignee(assignee)
    		.type(ticketRequest.getType())
    		.status(ticketRequest.getStatus())
    		.weight(ticketRequest.getWeight())
    		.project(project)
    		.createdOn(new Date())
    		.save(_ticketService));
    	return resp.getTicketID();
    }
    
    @RequestMapping(value = "/ticket/update", method = RequestMethod.POST)
    public boolean updateTicket(@Valid @RequestBody UpdateTicketRequest ticketRequest) throws NonExistentResourceException {
    	User assignee = _userService.getUserByUserID(ticketRequest.getAssigneeID());
    	if(ticketRequest.getAssigneeID()!=null) {
    		if(assignee==null) {
        		throw new NonExistentResourceException("Assignee is not existent!");
        	}
    	}
    	Project project = _projectService.getProjectByProjectId(ticketRequest.getProjectID());
    	if(project==null) {
    		throw new NonExistentResourceException("Project is not existent!");
    	}
    	
    	Long userID = LogInterceptor.context.get().getUserId();
    	User user = _userService.getUserByUserID(userID);
    	Ticket originalTicket = _ticketService.getTicketByID(ticketRequest.getTicketID());
    	if(!(userID==originalTicket.getIssuer().getUserID()||userID==originalTicket.getAssignee().getUserID()||user.getRole()==Role.PROJECT_MANAGER)) {
    		throw new UnauthorizedException("You are not authorized to change ticket!");
    	}
    	
    	return TicketBuilder.builder()
    		.id(ticketRequest.getTicketID())
    		.name(ticketRequest.getName())
    		.description(ticketRequest.getDescription())
    		.issuer(originalTicket.getIssuer())
    		.assignee(assignee)
    		.type(ticketRequest.getType())
    		.status(ticketRequest.getStatus())
    		.weight(ticketRequest.getWeight())
    		.project(project)
    		.createdOn(originalTicket.getCreatedOn())
    		.closedOn(ticketRequest.getClosedOn())
    		.comments(originalTicket.getComments())
    		.saveB(_ticketService);
    }

    @RequestMapping(value = "/project/mytickets", method = RequestMethod.POST)
    public Collection<TicketResponse> getAsignedTicketsByProject(
	    @Valid @RequestBody ProjectIDRequest projectIDRequest) {
	Long userID = LogInterceptor.context.get().getUserId();
	Long projectID = projectIDRequest.getProjectID();
	Collection<TicketResponse> ticketsResponse = new HashSet<TicketResponse>();
	Collection<Ticket> tickets = _ticketService.getTickets(userID, projectID);
	if (tickets != null) {
	    for (Ticket ticket : tickets) {
		TicketResponse response = new TicketResponse(ticket);
		ticketsResponse.add(response);
	    }
	}
	return ticketsResponse;
    }

    @RequestMapping(value = "/mytickets", method = RequestMethod.POST)
    public Collection<TicketResponse> getAsignedTickets() {
	Long userID = LogInterceptor.context.get().getUserId();
	Collection<TicketResponse> ticketsResponse = new HashSet<TicketResponse>();
	Collection<Ticket> tickets = _ticketService.getTicketsByUser(userID);
	if (tickets != null) {
	    for (Ticket ticket : tickets) {
		TicketResponse response = new TicketResponse(ticket);
		ticketsResponse.add(response);
	    }
	}
	return ticketsResponse;
    }

    @RequestMapping(value = "/ticket", method = RequestMethod.POST)
    public TicketResponse getTicketByID(@Valid @RequestBody TicketIDRequest ticketIDRequest) {
	Ticket ticket = _ticketService.getTicketByID(ticketIDRequest.getTicketID());
	if (ticket != null) {
	    return new TicketResponse(ticket);
	} else {
	    return null;
	}
    }

    @RequestMapping(value = "/ticket/search", method = RequestMethod.POST)
    public List<TicketResponse> searchTicket(@Valid @RequestBody TicketSearchRequest ticketSearchRequest)
	    throws SolrServerException, IOException {
	List<TicketResponse> tickets = getSolrService().getMatchedTickets(ticketSearchRequest.getText());
	
	return tickets;
    }

    @RequestMapping(value = "/project/tickets", method = RequestMethod.POST)
    public Collection<TicketResponse> getTicketsByProject(@Valid @RequestBody ProjectIDRequest projectIDRequest) {
	Long projectID = projectIDRequest.getProjectID();
	Collection<TicketResponse> ticketsResponse = new HashSet<TicketResponse>();
	Collection<Ticket> tickets = _ticketService.getTicketsByProject(projectID);
	if (tickets != null) {
	    for (Ticket ticket : tickets) {
		TicketResponse response = new TicketResponse(ticket);
		ticketsResponse.add(response);
	    }
	}
	return ticketsResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<List<ApiError>> validationError(MethodArgumentNotValidException ex) {
	BindingResult result = ex.getBindingResult();
	final List<FieldError> fieldErrors = result.getFieldErrors();
	final List<ObjectError> objectErrors = result.getGlobalErrors();
	List<ApiError> errors = new ArrayList<ApiError>();
	for (FieldError fieldError : fieldErrors) {
	    String message = "Error - field: " + fieldError.getField() + " - " + fieldError.getDefaultMessage();
	    ApiError apiError = new ApiError(message);
	    errors.add(apiError);
	}
	for (ObjectError objectError : objectErrors) {
	    String message = "Error - object: " + objectError.getObjectName() + " - " + objectError.getDefaultMessage();
	    ApiError apiError = new ApiError(message);
	    errors.add(apiError);
	}
	return new ResponseEntity<List<ApiError>>(errors, HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(value = "/ticket/comments/{ticketID}", method = RequestMethod.GET)
    public Collection<Comment> getCommentsByTicket(@PathVariable("ticketID") Long ticketID) {
	return getCommentService().getCommentsByTicket(ticketID);
    }
}
