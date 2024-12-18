package mk.ukim.finki.wp.lab.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Entity
public class Event {
   private  String name;
   private String description;
   private double popularityScore;
   private int ticketCount;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @ManyToOne
   private Location location;
  // private List<String> comments;


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
      this.location = location;
    //  this.comments = new ArrayList<>();

   }

   public Event() {

   }

//   public void addComment(String comment) {
//      this.comments.add(comment);
//   }
//
//
//   public List<String> getComments() {
//      return comments;
//   }
}