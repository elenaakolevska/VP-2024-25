package mk.ukim.finki.aud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="manufacturers")
public class Manufacturer {
    @Id
    private Long id;
    private String name;

    @Column(name = "manufacturer_address")
    private String address;

    public Manufacturer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Manufacturer() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
