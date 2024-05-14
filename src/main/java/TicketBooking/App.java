package TicketBooking;

import TicketBooking.Entities.Train;
import TicketBooking.Entities.User;
import TicketBooking.Service.UserBookingService;
import TicketBooking.Util.UserServiceUtil;

import java.io.IOException;
import java.util.*;

public class App {
    public static void main(String args[]){
        System.out.println(" Running Ticket Booking System ");
        Scanner sc = new Scanner(System.in);
        UserBookingService userBookingService;
        int option = 0;
        try{
            userBookingService = new UserBookingService();
        }catch(IOException e){
            System.out.println(" there is something wrong " + e);
            return;
        }

        while(option!=6){
            System.out.println(" Choose options");
            System.out.println(" 1. Sign Up");
            System.out.println(" 2. Login");
            System.out.println(" 3. Fetch Bookings");
            System.out.println(" 4. Search Trains");
            System.out.println(" 5. Book a Seat");
            System.out.println(" 6. Exit ");
            option = sc.nextInt();
            Train trainSelected = new Train();
            switch(option){
                case 1:
                    System.out.println(" Enter the name for signUp");
                    String nameForSignUP = sc.next();
                    System.out.println("Enter the password for SignUp");
                    String passwordForSignUP = sc.next();
                    User userForSignUp = new User(nameForSignUP, passwordForSignUP, UserServiceUtil.hashPassword(passwordForSignUP), new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUp(userForSignUp);
                    break;
                case 2:
                    System.out.println(" Enter the name for Login");
                    String nameForLogin = sc.next();
                    System.out.println("Enter the password for Login ");
                    String passwordForLogin= sc.next();
                    User userForLogin = new User(nameForLogin, nameForLogin, UserServiceUtil.hashPassword(passwordForLogin), new ArrayList<>(), UUID.randomUUID().toString());
                    try{
                       userBookingService = new UserBookingService(userForLogin);
                    }catch(IOException e){
                        System.out.println(" Something wrong while loging in");
                        return;
                    }
                    break;
                case 3:
                    System.out.println(" Fetch the bookings");
                    userBookingService.fetchBookings();
                    break;
                case 4:
                    System.out.println(" Type the source station ");
                    String sourceStation = sc.next();
                    System.out.println(" Type the destination  station ");
                    String destinationStation = sc.next();
                    List<Train> trains = userBookingService.getTrains(sourceStation, destinationStation);
                    int index = 1;
                    for(Train t : trains) {
                        System.out.println(index + " train id " + t.getTrainId());
                        for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
                            System.out.println(" station " + entry.getKey() + " Time " + entry.getValue());
                        }
                        index++;
                    }
                    System.out.println(" Select one train for booking by typing 1, 2, 3....");
                    int idx = sc.nextInt();
                    trainSelected = trains.get(idx);
                    System.out.println(" train selected ");
                    break;
                case 5:
                    System.out.println("Select a seat out of these seats");
                    List<List<Integer>> seats = userBookingService.fetchSeats(trainSelected);
                    for (List<Integer> row: seats){
                        for (Integer val: row){
                            System.out.print(val+" ");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the seat by typing the row and column");
                    System.out.println("Enter the row");
                    int row = sc.nextInt();
                    System.out.println("Enter the column");
                    int col = sc.nextInt();
                    System.out.println("Booking your seat....");
                    Boolean booked = userBookingService.bookTrainSeat(trainSelected, row, col);
                    if(booked.equals(Boolean.TRUE)){
                        System.out.println("Booked! Enjoy your journey");
                    }else{
                        System.out.println("Can't book this seat");
                    }
                    break;
                default:
                    System.out.println(" Visit again !!");
                    break;
            }

        }
        }
}
