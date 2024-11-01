package mk.ukim.finki.wp.lab.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SavedBooking {
    private String eventName;
    private String attendeeName;
    private int numberOfTickets;
}