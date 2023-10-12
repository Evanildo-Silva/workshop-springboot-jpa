package tech.evanildodeveloper.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.evanildodeveloper.course.entities.User;
import tech.evanildodeveloper.course.repositories.UserRepository;

@Service // Annotation to register as a spring component
public class UserService {

    @Autowired // Annotation to register UserService as spring component for dependency
               // injection
    private UserRepository repository; // UserRepository dependency

    // Service method to run findAll on the repository
    public List<User> findAll() {
        return repository.findAll();
    }

    // Service method to run findById on the repository
    public User findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.get();
    }

    // Service method to create user
    public User create(User user) {
        return repository.save(user);
    }
}
