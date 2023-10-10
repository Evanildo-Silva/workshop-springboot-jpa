package tech.evanildodeveloper.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tech.evanildodeveloper.course.entities.User;
import tech.evanildodeveloper.course.repositories.UserRepository;

public class UserService {

    @Autowired // Annotation to perform dependency injection
    private UserRepository repository; // UserRepository dependency

    // Service method to run findAll on the repository
    public List<User> findAll() {
        return repository.findAll();
    }
}
