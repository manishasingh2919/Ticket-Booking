package TicketBooking.Entities;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Ticket {
    private String ticketId;
    private String userID;
    private String source;
    private String destination;
    private String dateOFTravel;
    private Train train;

    public Ticket(){

    }

    public Ticket(String ticketId, String userID, String source, String destination, String dateOFTravel, Train train) {
        this.ticketId = ticketId;
        this.userID = userID;
        this.source = source;
        this.destination = destination;
        this.dateOFTravel = dateOFTravel;
        this.train = train;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateOFTravel() {
        return dateOFTravel;
    }

    public void setDateOFTravel(String dateOFTravel) {
        this.dateOFTravel = dateOFTravel;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

}
