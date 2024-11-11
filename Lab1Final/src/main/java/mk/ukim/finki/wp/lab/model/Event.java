package mk.ukim.finki.wp.lab.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data

public class Event {
   private  String name;
   private String description;
   private double popularityScore;
   private int ticketCount;
   private Long id;
   private Location location;

   public Event(String name, String description, double popularityScore, int ticketCount, Location location) {
      this.name = name;
      this.description = description;
      this.popularityScore = popularityScore;
      this.ticketCount = ticketCount;
      Random random = new Random();
      this.id = random.nextLong();
      this.location = location;
   }

   public Event(String name, String description, double popularityScore,  Location location) {
      this.name = name;
      this.description = description;
      this.popularityScore = popularityScore;
      Random random = new Random();
      this.id = random.nextLong();
      this.location = location;
   }
}