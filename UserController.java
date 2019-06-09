package com.cg.rest;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.bean.Post;
import com.cg.bean.User;
import com.cg.repo.UserRepo;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepo userRepo;

	@PostMapping(path = "", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return new ResponseEntity<User>(userRepo.save(user), HttpStatus.OK);
	}

	@PutMapping(path = "", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		return new ResponseEntity<User>(userRepo.save(user), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id) {
		return new ResponseEntity<User>(userRepo.findById(id).get(), HttpStatus.OK);
	}

	@GetMapping(path = "/posts/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<Post>> getPosts(@PathVariable int id) {
		User user = userRepo.findById(id).get();
		return new ResponseEntity<List<Post>>(user.getPosts(), HttpStatus.OK);
	}

	@GetMapping(path = "/{username}/{password}")
	public ResponseEntity<?> validateCustomer(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		if (username.matches("[0-9]{10}")) {
			try {
				User user = userRepo.findByMobileAndPassword(username, password);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} catch (NoSuchElementException e) {
				return new ResponseEntity<String>("Sorry. No Such Customer Found", HttpStatus.NOT_FOUND);
			}
		} else {
			try {
				User user = userRepo.findByEmailAndPassword(username, password);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} catch (NoSuchElementException e) {
				return new ResponseEntity<String>("Sorry. No Such Customer Found", HttpStatus.NOT_FOUND);
			}
		}
	}

	@GetMapping(path = "/email/{email}")
	public ResponseEntity<Boolean> validateEmail(@PathVariable("email") String email) {
		User user = userRepo.findByEmail(email);
		if (user != null)
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	@GetMapping(path = "/mobile/{mobile}")
	public ResponseEntity<Boolean> validateMobile(@PathVariable("mobile") String mobile) {
		User user = userRepo.findByMobile(mobile);
		if (user != null)
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
}
