package mk.ukim.finki.aud.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.aud.model.Category;
import mk.ukim.finki.aud.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    @PostConstruct  //vednas stom se pristapuva klasata se povikuva ovoj metod
    public  void init(){
        categories.add(new Category("Movies", "Movies Category"));
        categories.add(new Category("Books", "Book Category"));

        users.add(new User("elena.kolevska", "ek", "Elena", "Kolevska"));
        users.add(new User("ljubica.jovanoska", "lj", "Ljubica", "Jovanoska"));

    }
}
