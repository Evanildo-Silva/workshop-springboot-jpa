package tech.evanildodeveloper.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.evanildodeveloper.course.entities.Order;
import tech.evanildodeveloper.course.repositories.OrderRepository;

@Service // Annotation to register as a spring component
public class OrderService {

    @Autowired // Annotation to register UserService as spring component for dependency
               // injection
    private OrderRepository repository; // UserRepository dependency

    // Service method to run findAll on the repository
    public List<Order> findAll() {
        return repository.findAll();
    }

    // Service method to run findById on the repository
    public Order findById(Long id) {
        Optional<Order> order = repository.findById(id);
        return order.get();
    }
}
