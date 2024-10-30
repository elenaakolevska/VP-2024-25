package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.SavedBooking;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text,double popularityScore) {
        return eventRepository.searchEvents(text, popularityScore);
    }

    @Override
    public List<SavedBooking> getSavedBookings() {
        return eventRepository.getSavedBookings();
    }

    @Override
    public void addBooking(String eventName, String numTickets) {
        eventRepository.addBooking(eventName, numTickets);
    }
}
