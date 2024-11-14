package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.SavedBooking;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public Optional<Event> save(String name, String description, Double popularityScore, Location id) {
        return eventRepository.save(name, description, popularityScore, id);
    }
    @Override
    public List<Event> findByName(String name) {
        return eventRepository.findByName(name);
    }

    @Override
    public List<Event> findByMinRating(Double rating) {
        return eventRepository.findByMinRating(rating);
    }

    @Override
    public List<Event> findByNameAndMinRating(String name, Double rating) {
        return eventRepository.findByNameAndMinRating(name, rating);
    }


}