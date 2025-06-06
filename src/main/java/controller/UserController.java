package controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.Users;
import service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	//DI Service
	private final UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/hello")
	public String greetings() {
		return "Hello";
	}
	
	@PostMapping("/register")
	public Users registerUser(@RequestBody Users user) {
		// generate token in service layer
		return this.userService.saveUser(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		System.out.println(user);
		
		// verify token
		return this.userService.verify(user);
	}
	
	@GetMapping("/users")
	public List<Users> getAll() {
		return this.userService.allUsers();
	}
}
