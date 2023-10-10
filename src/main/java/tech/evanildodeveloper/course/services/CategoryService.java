package tech.evanildodeveloper.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.evanildodeveloper.course.entities.Category;
import tech.evanildodeveloper.course.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired // Annotation to register UserService as spring component for dependency
               // injection
    private CategoryRepository repository; // CategoryRepository dependency

    // Service method to run findAll on the repository
    public List<Category> findAll() {
        return repository.findAll();
    }

    // Service method to run findById on the repository
    public Category findById(Long id) {
        Optional<Category> cat = repository.findById(id);
        return cat.get();
    }
}
