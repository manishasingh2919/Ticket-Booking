package TicketBooking.Service;

import TicketBooking.Entities.Train;
import TicketBooking.Entities.User;
import TicketBooking.Util.UserServiceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBookingService {
    private User user;
    private List<User> userList;
    private static final String USERS_PATH = "/Users/manishasingh/Downloads/SpringDemo/src/main/java/TicketBooking/LocalDB/users.JSON";
    private static ObjectMapper objectMapper = new ObjectMapper();
    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        loadUsers();
    }

    public UserBookingService() throws IOException {
        loadUsers();
    }

    public List<User> loadUsers() throws IOException {
        File users = new File(USERS_PATH);
        return userList = objectMapper.readValue(users,new TypeReference<List<User>>(){});
    }
    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equalsIgnoreCase(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException{
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }
    //Object --> JSON (Serialize )
    //JSON -- > Object(User)  Deserialize

    public void fetchBookings(){
        System.out.println(" fetching ");
        Optional<User> userFetched = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        if(userFetched.isPresent()){
            userFetched.get().printTickets();
        }
    }

    public Boolean cancelBooking(String ticketID) throws IOException {
        //To do
        user.removeTickets(ticketID);
        saveUserListToFile();
        return Boolean.TRUE;
    }

    public List<Train> getTrains(String source, String destination){
        try {
            System.out.println(" fetching trains ");
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        }catch(IOException e){
            System.out.println(" something wrong while searching the trains ");
            return new ArrayList<>();
        }
    }
    public List<List<Integer>> fetchSeats(Train train){
        System.out.println(" booking a seat ");
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }
}
