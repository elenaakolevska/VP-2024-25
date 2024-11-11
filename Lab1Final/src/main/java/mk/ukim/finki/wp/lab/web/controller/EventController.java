package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model, HttpServletRequest req) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Event> events = this.eventService.listAll();
        model.addAttribute("events", events);
        model.addAttribute("clientIpAddress",req.getRemoteAddr());
        return "listEvents";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            model.addAttribute("event", event);
            model.addAttribute("locations", locationService.findAll());
            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";
    }

    @GetMapping("/add-event")
    public String addEvent(Model model) {
        List<Event> events = this.eventService.listAll();
        model.addAttribute("events", events);
        model.addAttribute("locations", locationService.findAll());
        return "add-event";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Double popularityScore,
                            @RequestParam Long locationId) {
        Location location = locationService.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid location ID"));
        eventService.save(name, description, popularityScore, location);
        return "redirect:/events";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.eventService.deleteById(id);
        return "redirect:/events";
    }

}
