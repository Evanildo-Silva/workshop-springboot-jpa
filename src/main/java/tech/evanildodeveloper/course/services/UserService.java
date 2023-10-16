package tech.evanildodeveloper.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import tech.evanildodeveloper.course.entities.User;
import tech.evanildodeveloper.course.repositories.UserRepository;
import tech.evanildodeveloper.course.services.exceptions.DatabaseException;
import tech.evanildodeveloper.course.services.exceptions.ResourceNotFoundException;
import tech.evanildodeveloper.course.services.exceptions.ResourcesAlreadyRegisteredException;
import tech.evanildodeveloper.course.services.records.UserUpdateRecord;
import tech.evanildodeveloper.course.utils.Utils;

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
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Service to check if the user exists
    public void findByEmail(String email) {
        User userExists = repository.findByEmail(email);
        if (userExists != null) {
            throw new ResourcesAlreadyRegisteredException();
        }
    }

    // Service to user create method
    public User create(User user) {
        return repository.save(user);
    }

    // Service to user delete method
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    // Service to user delete method
    public User update(Long id, UserUpdateRecord user) {
        try {
            User entity = repository.getReferenceById(id); // getReferenceById: Causes JPA to monitor this object
            Utils.copyNonNullproperties(user, entity);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
