package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/users")
public class DemoApplication {

	// Simulated in-memory database
	private final Map<Long, User> users = new HashMap<>();
	private long nextUserId = 1;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// Create a new user
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		user.setId(nextUserId++);
		users.put(user.getId(), user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	// Get all users
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = new ArrayList<>(users.values());
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	// Get user by ID
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		User user = users.get(id);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Update user information
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User updatedUser) {
		User user = users.get(id);
		if (user != null) {
			// Update user information
			user.setUsername(updatedUser.getUsername());
			user.setEmail(updatedUser.getEmail());
			user.setPhoneNumber(updatedUser.getPhoneNumber());
			user.setAddress(updatedUser.getAddress());
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Delete user by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
		if (users.containsKey(id)) {
			users.remove(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

