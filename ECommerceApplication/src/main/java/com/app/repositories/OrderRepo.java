package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

}
