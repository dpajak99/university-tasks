package com.tarbus.repositories.jpa;

import com.tarbus.payload.entity.FileObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends CrudRepository<FileObject, String> {

}
