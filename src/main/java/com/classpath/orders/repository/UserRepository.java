package com.classpath.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classpath.orders.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
