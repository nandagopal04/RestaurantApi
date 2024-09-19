package com.chs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chs.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{
	
	
	
}
