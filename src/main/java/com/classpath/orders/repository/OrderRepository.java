package com.classpath.orders.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.classpath.orders.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

}
