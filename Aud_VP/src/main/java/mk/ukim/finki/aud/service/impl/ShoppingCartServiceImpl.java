package mk.ukim.finki.aud.service.impl;

import mk.ukim.finki.aud.bootstrap.DataHolder;
import mk.ukim.finki.aud.model.Product;
import mk.ukim.finki.aud.model.ShoppingCart;
import mk.ukim.finki.aud.model.User;
import mk.ukim.finki.aud.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.aud.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.aud.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.aud.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.aud.model.exceptions.UserNotFoundException;
import mk.ukim.finki.aud.repository.InMemoryShoppingCartRepository;
import mk.ukim.finki.aud.repository.InMemoryUserRepository;
import mk.ukim.finki.aud.service.ProductService;
import mk.ukim.finki.aud.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

   private  final InMemoryShoppingCartRepository inMemoryShoppingCartRepository;
   private final InMemoryUserRepository userRepository;
   private final ProductService productService;
    public ShoppingCartServiceImpl(InMemoryShoppingCartRepository inMemoryShoppingCartRepository, InMemoryUserRepository userRepository, ProductService productService) {
        this.inMemoryShoppingCartRepository = inMemoryShoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(this.inMemoryShoppingCartRepository.findById(cartId).isEmpty()){
            throw  new ShoppingCartNotFoundException(cartId);
        }
        return this.inMemoryShoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return this.inMemoryShoppingCartRepository
                .findByUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    User user = this.userRepository.findByUsername(username).
                            orElseThrow(() -> new UserNotFoundException(username));
                   ShoppingCart shoppingCart = new ShoppingCart(user);

                    return inMemoryShoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = this.productService.
                findById(productId).
                orElseThrow(() -> new ProductNotFoundException(productId));

        if (shoppingCart.getProducts().
                stream().
                filter(i-> i.getId().equals(productId)).
                collect(Collectors.toList()).size() > 0){
            throw new ProductAlreadyInShoppingCartException(productId, username);
        }

        shoppingCart.getProducts().add(product);
        return this.inMemoryShoppingCartRepository.save(shoppingCart);
    }
}
