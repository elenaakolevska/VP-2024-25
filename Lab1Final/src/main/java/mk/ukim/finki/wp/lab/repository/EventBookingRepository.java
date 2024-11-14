package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventBookingRepository{
    public List<EventBooking> bookings=new ArrayList<>();
    public List<EventBooking> findAll(){
        return bookings;
    }

}