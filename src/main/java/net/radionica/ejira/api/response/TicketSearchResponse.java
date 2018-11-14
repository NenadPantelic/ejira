package net.radionica.ejira.api.response;

public class TicketSearchResponse {
    private long _ticketID;
    
    public long getTicketID() {
        return _ticketID;
    }

    public void setTicketID(long ticketID) {
        _ticketID = ticketID;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    private String _name;

    private String _description;
}
