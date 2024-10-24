package mk.ukim.finki.aud.service;
import mk.ukim.finki.aud.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    Category create(String name, String description);
    Category update(String name, String description);
    void delete(String name);
    List<Category> listCategories();
    List<Category> searchCategories(String searchText);
}
