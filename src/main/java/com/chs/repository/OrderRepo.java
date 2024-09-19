package com.chs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chs.entity.Customer;
import com.chs.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

	List<Order> findAllByCustomer(Customer customer);

}
