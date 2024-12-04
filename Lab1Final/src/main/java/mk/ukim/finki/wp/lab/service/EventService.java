package mk.ukim.finki.wp.lab.service;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.SavedBooking;

import java.util.List;
import java.util.Optional;


public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text,double popularity);
    void addBooking(String eventName,String attendeeName,int tickets);
    void  deleteById(Long id);
    Optional<Event> findById(Long id);
    Optional<Event> save(String name, String description, Double popularityScore, Location location);
     List<Event> findByName(String name);
     List<Event> findByMinRating(Double rating);
     List<Event> findByNameAndMinRating(String name, Double rating);
    public void addCommentToEvent(Long eventId, String username, String commentContent);
    List<String> getCommentsByEventId(Long eventId);
    }