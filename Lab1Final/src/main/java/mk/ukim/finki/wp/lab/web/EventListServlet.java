package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.EventService;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

import static mk.ukim.finki.wp.lab.bootstrap.DataHolder.eventList;

@WebServlet(name = "EventListServlet", urlPatterns = "/*")

public class EventListServlet extends HttpServlet {

    //proess thymeleaf template
    private final SpringTemplateEngine springTemplateEngine;
    private final   EventService eventService;

    public EventListServlet(SpringTemplateEngine springTemplateEngine, EventService eventService) {
        this.springTemplateEngine = springTemplateEngine;
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext webContext = new WebContext(iWebExchange);

        List<Event> events;

      //  events= DataHolder.eventList;

        String searchName = req.getParameter("searchName");
        String minRating = req.getParameter("minRating");

        if((searchName == null || searchName.isEmpty()) &&
                ( minRating == null || minRating.isEmpty())){
            events = eventService.listAll();
        }else {
            double minRatingValue = 0.0;
            if (minRating != null && !minRating.isEmpty()) {
                minRatingValue = Double.parseDouble(minRating);
            }
            events = eventService.searchEvents(searchName, minRatingValue);
        }



        String clientIpAddress = req.getRemoteAddr();
        webContext.setVariable("clientIpAddress", clientIpAddress);
        webContext.setVariable("events", events);
        springTemplateEngine.process("listEvents.html", webContext, resp.getWriter());



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
