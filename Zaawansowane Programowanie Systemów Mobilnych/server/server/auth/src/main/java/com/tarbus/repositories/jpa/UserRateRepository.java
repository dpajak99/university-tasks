package com.tarbus.repositories.jpa;

import com.tarbus.payload.entity.FileObject;
import com.tarbus.payload.entity.UserRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRateRepository extends CrudRepository<UserRate, String> {
  List<UserRate> findByGivenToId(String userId);
}
