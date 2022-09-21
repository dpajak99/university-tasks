package com.tarbus.repositories.jpa;

import com.tarbus.payload.entity.CompanyUsers;
import com.tarbus.payload.entity.CompanyUsersId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyUsersRepository extends CrudRepository<CompanyUsers, CompanyUsersId> {}
