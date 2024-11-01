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
    public List<Event> searchEvents(String text,double popularity) {
        return eventRepository.searchEvents(text,popularity);
    }



    @Override
    public void addBooking(String eventName, String attendeeName, int tickets) {
        try {
            eventRepository.addBooking(eventName, attendeeName, tickets);
        } catch (IllegalArgumentException e) {

            throw new RuntimeException("Booking failed: " + e.getMessage(), e);
        }
    }

}