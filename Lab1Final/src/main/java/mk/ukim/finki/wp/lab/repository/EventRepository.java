package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.SavedBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {

    //direktno komunicira so datata na podatoci
    //imame nekoja logika odnosno prebaruvanje po nesto


    private List<SavedBooking> savedBookings = new ArrayList<>();
    public List<Event> findAll() {
        return DataHolder.eventList;
    }

    public List<Event> searchEvents(String text, double popularityScore){
        return DataHolder.eventList
                .stream()
                .filter(event -> (text == null || text.isEmpty() || event.getName().toLowerCase().contains(text.toLowerCase()) ||
                        event.getDescription().toLowerCase().contains(text.toLowerCase())) &&
                        event.getPopularityScore() >= popularityScore)
                .collect(Collectors.toList());
    }

    public List<SavedBooking> getSavedBookings(){
        return savedBookings;
    }

    public void addBooking(String eventName, String numTickets) {
        boolean bookingExists = false;

        for (SavedBooking booking : savedBookings) {
            if(booking.getEventName().equals(eventName)){
                booking.setNumberOfTickets(booking.getNumberOfTickets() + numTickets);
                bookingExists=true;
                break;
            }
        }

        if(!bookingExists){
            savedBookings.add(new SavedBooking(eventName, numTickets));
        }

    }
}
