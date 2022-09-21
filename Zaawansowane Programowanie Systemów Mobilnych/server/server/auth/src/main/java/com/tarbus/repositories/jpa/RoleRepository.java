package com.tarbus.repositories.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tarbus.payload.enums.ERole;
import com.tarbus.payload.entity.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, ERole> {
	@Query("SELECT r FROM Role r WHERE r.name = ?1")
	Optional<Role> findByName(ERole name);

	@Query("SELECT r FROM Role r WHERE r.name = ?1 AND r.objectId = ?2")
	Optional<Role> findByNameAndObjectId(ERole name, String objectId);

	@Query("SELECT u.roles FROM User u WHERE u.id = :userId")
	List<Role> findUserRoles(@Param("userId") String userId);
}
