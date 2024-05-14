package TicketBooking.Service;

import TicketBooking.Entities.Train;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainService {
    private List<Train> trainList;
    private static final String TRAINS_PATH = "/Users/manishasingh/Downloads/SpringDemo/src/main/java/TicketBooking/LocalDB/trains.json";
    private static ObjectMapper objectMapper = new ObjectMapper();
    public TrainService() throws IOException {
        loadTrains();
    }

    public List<Train> loadTrains() throws IOException {
        File trains = new File(TRAINS_PATH);
        return trainList = objectMapper.readValue(trains,new TypeReference<List<Train>>(){});
    }

    public void addTrain(Train newTrain) throws IOException {
        // Check if a train with the same trainId already exists
        Optional<Train> existingTrain = trainList.stream()
                .filter(train -> train.getTrainId().equalsIgnoreCase(newTrain.getTrainId()))
                .findFirst();

        if (existingTrain.isPresent()) {
            // If a train with the same trainId exists, update it instead of adding a new one
            updateTrain(newTrain);
        } else {
            // Otherwise, add the new train to the list
            trainList.add(newTrain);
            saveTrainListToFile();
        }
    }

    public void updateTrain(Train updatedTrain) throws IOException {
        // Find the index of the train with the same trainId
        OptionalInt index = IntStream.range(0, trainList.size())
                .filter(i -> trainList.get(i).getTrainId().equalsIgnoreCase(updatedTrain.getTrainId()))
                .findFirst();

        if (index.isPresent()) {
            // If found, replace the existing train with the updated one
            trainList.set(index.getAsInt(), updatedTrain);
            saveTrainListToFile();
        } else {
            // If not found, treat it as adding a new train
            addTrain(updatedTrain);
        }
    }
    private void saveTrainListToFile() throws IOException{
        try {
            objectMapper.writeValue(new File(TRAINS_PATH), trainList);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception based on your application's requirements
        }
    }

    public List<Train> searchTrains(String source, String destination){
        return trainList.stream().filter(train -> validTrains(train, source, destination)).collect(Collectors.toList());
    }

    public Boolean validTrains(Train train, String source , String destination){
        List<String> stations = train.getStations();
        int indexOfSource = stations.indexOf(source.toLowerCase());
        int indexOfDestination = stations.indexOf(destination.toLowerCase());

        return indexOfSource!=-1 && indexOfDestination!=-1 && indexOfSource < indexOfDestination;
    }
}
