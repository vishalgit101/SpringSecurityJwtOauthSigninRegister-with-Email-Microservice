package service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import entity.Users;
import model.UserPrincipal;
import repository.UserRepo;

@Service
public class MyUserDetailService implements UserDetailsService {
	
	private final UserRepo userRepo;
	
	public MyUserDetailService(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = this.userRepo.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new UserPrincipal(user);
	}
	
}
