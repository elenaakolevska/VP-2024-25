package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.model.SavedBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name="eventBookingServlet", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final EventBookingService bookingService;
    private final EventService eventService;


    public EventBookingServlet(SpringTemplateEngine springTemplateEngine, EventBookingService bookingService, EventService eventService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookingService = bookingService;
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newString = req.getParameter("bookingSearch");

        List<SavedBooking> bookingsToSend = eventService.getSavedBookings()
                .stream()
                .filter(booking -> booking.getEventName().toLowerCase().contains(newString.toLowerCase()))
                .collect(Collectors.toList());

        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("savedBookingList", bookingsToSend);
        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);

        //se zema od listEvents.html
        String eventName = req.getParameter("eventName");
        String attendeeName = req.getParameter("attendeeName");
        String attendeeAddress = req.getRemoteAddr();
        int numOfTickets = Integer.parseInt(req.getParameter("numOfTickets"));

        EventBooking booking = bookingService.placeBooking(eventName, attendeeName, attendeeAddress, numOfTickets);
        context.setVariable("booking", booking);

        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());

    }
}
