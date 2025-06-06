package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import entity.Users;
import repository.UserRepo;

@Service
public class UserService {
	// DI Repo
	private final UserRepo userRepo;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
	private AuthenticationManager authManger;
	private JwtService jwtService;
	
	@Autowired
	public UserService(UserRepo userRepo, AuthenticationManager authManger, JwtService jwtService) {
		super();
		this.userRepo = userRepo;
		this.authManger = authManger;
		this.jwtService = jwtService;
	}


	// save user
	public Users saveUser(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return this.userRepo.save(user);
	}
	
	//user by username
	public Users findByUsername(String username) {
		return this.userRepo.findByUsername(username);
	}
	
	public List<Users> allUsers() {
		return this.userRepo.findAll();
	}

	public String verify(Users user) {
		// take control of auth manager
		Authentication authentication = this.authManger.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			System.out.println("User Successfully Authenticated");
			System.out.println(this.jwtService.generateToken(user.getUsername()));
			this.jwtService.generateToken(user.getUsername());
		}
		
		System.out.println("Failed");
		return "failed";
		
	}
}
