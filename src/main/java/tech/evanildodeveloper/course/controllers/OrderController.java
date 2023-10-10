package tech.evanildodeveloper.course.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.evanildodeveloper.course.entities.Order;
import tech.evanildodeveloper.course.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	// UserService dependency injection
	@Autowired
	private OrderService service;

	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> listOfAllOrders = service.findAll();

		// eturn HTTP method success message → ResponseEntity.ok()
		// And the list of users in the body .body(listOfAllUsers)
		return ResponseEntity.ok().body(listOfAllOrders);
	}

	@GetMapping(value = "/{id}") // Indicates that the request receives an {id} as a parameter
	// The @PathVariable annotation for spring to understand that it receives a
	// parameter in the request
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order order = service.findById(id);

		// eturn HTTP method success message → ResponseEntity.ok()
		// And the user in the body .body(user)
		return ResponseEntity.ok().body(order);
	}
}
