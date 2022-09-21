package com.tarbus.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tarbus.payload.entity.Role;
import com.tarbus.payload.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);

	List<User> getUsersByRolesContains(Role role);

	@Query("select u from User u where u.firstName like %?1% or u.lastName like %?1% or u.email like %?1%")
	List<User> findByName(String pattern);
}
