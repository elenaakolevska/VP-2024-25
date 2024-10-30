package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.SavedBooking;

import java.util.List;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text, double popularityScore);
    List<SavedBooking> getSavedBookings();
    void addBooking(String eventName, String numTickets);
}
