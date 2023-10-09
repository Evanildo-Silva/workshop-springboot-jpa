package tech.evanildodeveloper.course.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.evanildodeveloper.course.entities.User;

@RestController
@RequestMapping("/users")
public class UserController {

	@GetMapping
	public ResponseEntity<User> findAll() {
		User u = new User(1L, "Maria", "Maria@gmail.com", "99999999", "12345");
		return ResponseEntity.ok().body(u);
	}
}
