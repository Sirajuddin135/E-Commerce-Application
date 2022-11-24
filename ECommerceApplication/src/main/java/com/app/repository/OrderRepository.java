package com.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	@Query("select o from Order o where o.orderDate=?1")
	public List<Order> findByOrderDate(LocalDate date);
	
	@Query("select o from Order o where o.address IN (select a from Address a where a.city=?1)")
	public List<Order> getAllOrdersByLocation(String loc);
	
//	@Query("select o from Order o where o.customer = (select c from Customer c where c.userId=?1)")
//	public List<Order> getAllOrdersByUserId(String userId);
}
