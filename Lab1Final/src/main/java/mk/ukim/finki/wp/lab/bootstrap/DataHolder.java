package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public  static List<Event> eventList = new ArrayList<>();

    @PostConstruct
    public void init(){
        eventList.add(new Event("Startup Pitch Night", "Presentations by innovative startups to investors", 18.00, 10));
        eventList.add(new Event("Cooking Workshop", "Hands-on cooking class with a professional chef", 11.30, 7));
        eventList.add(new Event("Photography Walk", "Guided tour for photographers around scenic spots", 9.00, 6));
        eventList.add(new Event("Astronomy Night", "Stargazing event with telescopes and experts", 20.00, 9));
        eventList.add(new Event("Yoga in the Park", "Outdoor yoga session for all skill levels", 7.30, 15));
        eventList.add(new Event("Local Theater Play", "Live performance by the community theater group", 19.00, 20));
        eventList.add(new Event("Wine Tasting", "Sampling of wines from local vineyards", 16.00, 7));
        eventList.add(new Event("Meditation Retreat", "Day-long retreat focused on mindfulness and relaxation", 8.00, 13));
        eventList.add(new Event("Coding Hackathon", "24-hour coding event for tech enthusiasts", 9.00, 7));
        eventList.add(new Event("Charity Art Auction", "Auction of art pieces to raise funds for charity", 17.00, 8));

    }
}
