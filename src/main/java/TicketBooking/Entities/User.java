package TicketBooking.Entities;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
public class User {
    private String name;
    private String password;
    private String hashedPassword;
    private List<Ticket> bookedTickets;
    private String userId;
    @JsonCreator
    public User(@JsonProperty("name") String name, @JsonProperty("password") String password, @JsonProperty("hashedPassword") String hashedPassword,
                @JsonProperty("bookedTickets")List<Ticket> bookedTickets, @JsonProperty("userId")String userId) {
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.bookedTickets = bookedTickets;
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String password) {
        this.hashedPassword = password;
    }

    public List<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(List<Ticket> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void printTickets(){
        System.out.println(" printing  ");
        for(int i =0;i<getBookedTickets().size();i++){
            System.out.println("Printing Tickets  " + bookedTickets.get(i));
        }
    }

    public void removeTickets(String ticketId){
        bookedTickets.stream().filter(ticket -> !Objects.equals(ticket.getTicketId(), ticketId)).collect(Collectors.toList());

    }
}
