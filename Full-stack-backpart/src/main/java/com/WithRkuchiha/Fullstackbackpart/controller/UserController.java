package com.WithRkuchiha.Fullstackbackpart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.WithRkuchiha.Fullstackbackpart.exception.UserNotFoundException;
import com.WithRkuchiha.Fullstackbackpart.model.User;
import com.WithRkuchiha.Fullstackbackpart.repository.UserRepository;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping("/User")
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}
	
	@GetMapping("/User")
	List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/User/{id}")
	User getUserByid(@PathVariable Long id){
		return userRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));
	}
	
	@PutMapping("/User/{id}")
	User updaUser(@RequestBody User newUser,@PathVariable Long id) {
		return userRepository.findById(id)
				.map(user->{
					user.setUsername(newUser.getUsername());
					user.setName(newUser.getName());
					user.setEmail(newUser.getEmail());
					return userRepository.save(user);
				}).orElseThrow(()->new UserNotFoundException(id));
	}
	
	@DeleteMapping("/User/{id}")
	String deleteUser(@PathVariable long id) {
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		
		userRepository.deleteById(id);
		return "User With Id "+id+" has been deleted successfully.";
	}

}
