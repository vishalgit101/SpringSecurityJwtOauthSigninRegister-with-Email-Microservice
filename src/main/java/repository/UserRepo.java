package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
	
	Users findByUsername(String username);
}
