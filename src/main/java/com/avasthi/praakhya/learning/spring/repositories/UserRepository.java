package com.avasthi.praakhya.learning.spring.repositories;

import com.avasthi.praakhya.learning.spring.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> { // <Classname, primary key type>

}
