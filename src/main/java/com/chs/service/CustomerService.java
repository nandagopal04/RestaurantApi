package com.chs.service;

import java.util.List;

import com.chs.dto.CustomerDto;
import com.chs.exception.InvalidEntityDetailsException;

public interface CustomerService {

	CustomerDto saveCustomer(CustomerDto customerDto);

	CustomerDto findCustomerById(Long id) throws InvalidEntityDetailsException;

	List<CustomerDto> findAllCustomers();

	CustomerDto editCustomer(CustomerDto customer) throws InvalidEntityDetailsException;

	CustomerDto deleteCustomer(Long id) throws InvalidEntityDetailsException;

}
