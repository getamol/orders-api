package com.classpath.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classpath.orders.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
