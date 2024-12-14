package mk.ukim.finki.aud.repository.jpa;

import mk.ukim.finki.aud.model.ShoppingCart;
import mk.ukim.finki.aud.model.User;
import mk.ukim.finki.aud.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
