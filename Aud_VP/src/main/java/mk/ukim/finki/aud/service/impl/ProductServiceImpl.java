package mk.ukim.finki.aud.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.aud.model.Category;
import mk.ukim.finki.aud.model.Manufacturer;
import mk.ukim.finki.aud.model.Product;
import mk.ukim.finki.aud.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.aud.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.aud.repository.impl.InMemoryCategoryRepository;
import mk.ukim.finki.aud.repository.impl.InMemoryManufacturerRepository;
import mk.ukim.finki.aud.repository.impl.InMemoryProductRepository;
import mk.ukim.finki.aud.repository.jpa.CategoryRepository;
import mk.ukim.finki.aud.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.aud.repository.jpa.ProductRepository;
import mk.ukim.finki.aud.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        Product product = new Product(name, price, quantity, category, manufacturer);
        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(product));

    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
