package com.chs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chs.entity.Customer;
import com.chs.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

	List<Order> findAllByCustomer(Customer customer);

}
