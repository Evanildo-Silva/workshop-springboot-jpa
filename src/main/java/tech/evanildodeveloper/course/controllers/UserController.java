package tech.evanildodeveloper.course.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tech.evanildodeveloper.course.entities.User;
import tech.evanildodeveloper.course.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	// UserService dependency injection
	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> listOfAllUsers = service.findAll();

		// eturn HTTP method success message → ResponseEntity.ok()
		// And the list of users in the body .body(listOfAllUsers)
		return ResponseEntity.ok().body(listOfAllUsers);
	}

	@GetMapping(value = "/{id}") // Indicates that the request receives an {id} as a parameter
	// The @PathVariable annotation for spring to understand that it receives a
	// parameter in the request
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User user = service.findById(id);

		// eturn HTTP method success message → ResponseEntity.ok()
		// And the user in the body .body(user)
		return ResponseEntity.ok().body(user);
	}

	@PostMapping // @RequestBody Indicates that the request receives an objeto
	public ResponseEntity<User> create(@RequestBody User user) {
		User userCreated = service.create(user);

		// Adds location header
		// https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Headers/Location
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}").buildAndExpand(userCreated.getId()).toUri();

		return ResponseEntity.created(uri).body(userCreated);
	}
}
