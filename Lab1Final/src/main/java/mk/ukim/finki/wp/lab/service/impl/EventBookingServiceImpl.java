package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.EventBookingRepository;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final EventRepository eventRepository;
    private final EventBookingRepository bookingRepository;

    public EventBookingServiceImpl(EventRepository eventRepository, EventBookingRepository bookingRepository) {
        this.eventRepository = eventRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        eventRepository.addBooking(eventName,attendeeName, numberOfTickets);
        return new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
    }

    @Override
    public List<EventBooking> filterBookings(String name) {
        return bookingRepository.findAll().stream()
                .filter(booking -> booking.getAttendeeName().equals(name))
                .toList();
    }

}