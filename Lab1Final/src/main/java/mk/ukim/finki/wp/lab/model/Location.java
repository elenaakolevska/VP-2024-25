package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@Data
public class Location {

    private Long id;
    private String name;
    private String address;
    private String capacity;
    private String description;

    public Location(String name, String address, String capacity, String description) {

        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.description = description;

        Random random = new Random();
        this.id = random.nextLong();
    }
}
