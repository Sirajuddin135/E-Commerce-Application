package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	@Query("select a.Customer from Address a where a.city=?1")
	List<Customer> viewAllCustomer(String location);
	
	Customer findByEmail(String email);
	
}
