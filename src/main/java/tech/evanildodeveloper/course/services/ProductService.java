package tech.evanildodeveloper.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.evanildodeveloper.course.entities.Product;
import tech.evanildodeveloper.course.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired // Annotation to register ProductService as spring component for dependency
               // injection
    private ProductRepository repository; // ProductRepository dependency

    // Service method to run findAll on the repository
    public List<Product> findAll() {
        return repository.findAll();
    }

    // Service method to run findById on the repository
    public Product findById(Long id) {
        Optional<Product> prod = repository.findById(id);
        return prod.get();
    }
}
