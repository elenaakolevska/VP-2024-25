package mk.ukim.finki.aud.model;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import mk.ukim.finki.aud.model.enumerations.ShoppingCartStatus;

@Data
public class ShoppingCart {
    private Long id;
    private LocalDateTime dateCreated;
    private User user;
    private List<Product> products;
    private ShoppingCartStatus status;
    public ShoppingCart(){

    }

    public ShoppingCart(User user) {
        this.id = (long) (Math.random()*1000);
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.products = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }
}
