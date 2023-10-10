package tech.evanildodeveloper.course.services;

import java.util.List;

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
}
