package tech.evanildodeveloper.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.evanildodeveloper.course.entities.User;
import tech.evanildodeveloper.course.repositories.UserRepository;
import tech.evanildodeveloper.course.services.exceptions.ResourceNotFoundException;

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

    // Service to user create method
    public User create(User user) {
        return repository.save(user);
    }

    // Service to user delete method
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Service to user delete method
    public User update(Long id, User user) {
        User entity = repository.getReferenceById(id); // getReferenceById: Faz com que o JPA monitore esse objeto
        updateData(entity, user);
        return repository.save(entity);
    }

    private void updateData(User entity, User user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
    }
}
