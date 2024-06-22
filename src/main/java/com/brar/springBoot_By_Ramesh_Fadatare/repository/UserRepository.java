package com.brar.springBoot_By_Ramesh_Fadatare.repository;

import com.brar.springBoot_By_Ramesh_Fadatare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository is automatically inherited from JPA repository
public interface UserRepository extends JpaRepository<User, Long> {
}
