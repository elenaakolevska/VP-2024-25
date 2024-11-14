package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.EventBookingRepository;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {

    private final EventService eventService;
    private final EventBookingRepository eventBookingRepository;
    private final EventBookingService eventBookingService;

    public EventBookingController(EventService eventService, EventBookingRepository eventBookingRepository, EventBookingService eventBookingService) {
        this.eventService = eventService;
        this.eventBookingRepository = eventBookingRepository;
        this.eventBookingService = eventBookingService;
    }

    @PostMapping
    public String processBooking(@RequestParam String eventName,
                                 @RequestParam String nameAttendee,
                                 @RequestParam String clientIpAddress,
                                 @RequestParam int numTickets,
                                 @RequestParam(required = false) String error,
                                 Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
            return "listEvents";
        }

        System.out.println("Booking Details:");
        System.out.println("Event Name: " + eventName);
        System.out.println("Attendee Name: " + nameAttendee);
        System.out.println("Attendee Address: " + clientIpAddress);
        System.out.println("Number of Tickets: " + numTickets);

        EventBooking eventBooking = eventBookingService.placeBooking(eventName, nameAttendee, clientIpAddress, numTickets);
        eventBookingRepository.bookings.add(eventBooking);

        List<EventBooking> allBookings = eventBookingService.filterBookings(nameAttendee);
        model.addAttribute("booking", eventBooking);
        model.addAttribute("allBookings", allBookings);

        return "bookingConfirmation";
    }

}
