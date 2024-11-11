package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {

    private final EventBookingService bookingService;
    private final EventService eventService;

    public EventBookingController(EventBookingService bookingService, EventService eventService) {
        this.bookingService = bookingService;
        this.eventService = eventService;
    }

    @GetMapping
    public String showEventBookingForm(Model model) {
        // Fetch the events to display them in the form
        model.addAttribute("events", eventService.listAll());
        // Add a placeholder for client IP
        model.addAttribute("clientIpAddress", "192.168.0.1"); // Just an example, in reality, you fetch it dynamically
        return "eventBooking"; // The name of your Thymeleaf template (eventBooking.html)
    }

    @PostMapping
    public String processBooking(@RequestParam String eventName,
                                 @RequestParam int numTickets,
                                 @RequestParam String nameAttendee,
                                 @RequestParam String attendeeIpAddress,
                                 Model model) {
        // Process the booking
        EventBooking booking = bookingService.placeBooking(eventName, nameAttendee, attendeeIpAddress, numTickets);

        // Pass the booking to the confirmation page
        model.addAttribute("booking", booking);

        return "bookingConfirmation"; // The name of the confirmation page (bookingConfirmation.html)
    }
}
